package service.demo.store.gatewayservice.filter.infrastructure.websocket;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import service.demo.store.gatewayservice.filter.domain.dto.TokenDto;

import java.util.Base64;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private WebClient.Builder webClient;

    public static class Config {

    }

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config){
        return ((((exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange,HttpStatus.UNAUTHORIZED);
            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String [] chunks = tokenHeader.split(" ");
            if (!tokenHeader.contains("Bearer ") && chunks.length !=2) {
                return onError(exchange, HttpStatus.BAD_REQUEST);
            }


            //todo Aqui cambiar para usar Feig en otro escenario
            return webClient.build()
                    .post()
                    .uri("lb://auth-service/auth/validate/" + chunks[1] )
                    .retrieve().bodyToMono(TokenDto.class)
                    .flatMap(t -> {

                        if (t.getToken()!=null){
                            //Luego de verificar que el token esta bien, renueva el header Auth para agregar el codigo de acceso a los microservicios.
                            String basicAuth = "client:client"; // Cambia por tus propias credenciales
                            String encodedBasicAuth = Base64.getEncoder().encodeToString(basicAuth.getBytes());
                            exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, "Basic " + encodedBasicAuth);

                            return chain.filter(exchange);

                        }
                        return onError(exchange, HttpStatus.BAD_REQUEST);

                    });

        })));
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }
}
