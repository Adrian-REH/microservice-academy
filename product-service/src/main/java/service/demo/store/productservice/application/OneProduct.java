package service.demo.store.productservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.port.ProductService;

@AllArgsConstructor
@Slf4j
@Service
public class OneProduct {

    private final ProductService productService;
    public ProductEntity execute(String id)  {


        return productService.getProduct(id);
    }
}
