package service.demo.store.productservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.productservice.domain.entity.CategoryEntity;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.port.ProductService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ListAllProduct {

    private final ProductService productService;
    public List<ProductEntity> execute(String categoryId)  {

        List<ProductEntity> productEntities = new ArrayList<>() ;
        productEntities = (null ==  categoryId)?
             productService.listAllProduct():
             productService.findByCategory(CategoryEntity.builder().id(categoryId).build());

        return productEntities;
    }
}
