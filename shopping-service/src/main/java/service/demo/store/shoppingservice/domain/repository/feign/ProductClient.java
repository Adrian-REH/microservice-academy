package service.demo.store.shoppingservice.domain.repository.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.demo.store.shoppingservice.domain.model.Product;
import service.demo.store.shoppingservice.infrastructure.adapter.feign.Hystrix.CustomerHystrixFallbackFactory;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id,
                                              @RequestHeader("Authorization") String authorizationHeader);

    @PutMapping(value = "/products/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable String id, @RequestParam(name = "quantity", required = true) Double quantity,
                                                      @RequestHeader("Authorization") String authorizationHeader);

}