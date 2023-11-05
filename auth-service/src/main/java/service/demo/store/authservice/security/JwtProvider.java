package service.demo.store.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import service.demo.store.authservice.entity.AuthUser;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class JwtProvider {

    @Value("${jwt.signing.key}")
    private String secret;


    @PostConstruct
    protected void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims = Jwts.claims().setSubject(authUser.getId());

        claims.put("id",authUser.getId());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getUserNameFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return "bad Token";
        }
    }
}
