package service.demo.store.shoppingservice.utils;

import org.springframework.http.*;
import service.demo.store.shoppingservice.domain.model.Product;

import java.util.Base64;

public class MakeHeaderAuth {
    public static String makeProductRequest() {
        String username = "client";
        String password = "client";

        // Construye la cadena de autorización en formato "username:password"
        String credentials = username + ":" + password;

        // Codifica la cadena de autorización en Base64
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        return "Basic " + base64Credentials;

    }

}
