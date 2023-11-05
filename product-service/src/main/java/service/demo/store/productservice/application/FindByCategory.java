package service.demo.store.productservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.port.ProductService;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class FindByCategory {

    private final ProductService productService;
    public List<ProductEntity> execute() throws Exception {
        return productService.listAllProduct();
    }
}
