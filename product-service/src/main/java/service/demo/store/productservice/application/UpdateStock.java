package service.demo.store.productservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.port.ProductService;

@AllArgsConstructor
@Slf4j
@Service
public class UpdateStock {

    private final ProductService productService;

    public ProductEntity execute(String id, Double quantity)  {
        ProductEntity productEntityDB = productService.getVerifyProductEntity(id);

        if (productEntityDB == null) return null;
        Double stock =  productEntityDB.getStock() + quantity;
        productEntityDB.setStock(stock);

        return productService.updateStock(productEntityDB);
    }
}
