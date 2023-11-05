package service.demo.store.productservice.infrastructure.adapter.postgresql;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import service.demo.store.productservice.domain.entity.CategoryEntity;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.port.ProductService;
import service.demo.store.productservice.domain.repository.jpa.JpaProductRepository;

import java.util.Date;
import java.util.List;

@Qualifier("PSQLProductImpl")
@Component
@RequiredArgsConstructor
public class PSQLProductImpl implements ProductService {


    private final JpaProductRepository jpaProductRepository;

    @Override
    public List<ProductEntity> listAllProduct() {
        return jpaProductRepository.findAll();
    }

    @Override
    public ProductEntity getProduct(String id) {
        return jpaProductRepository.findById(id).orElse(null);
    }

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        productEntity.setStatus("CREATED");
        productEntity.setCreateAt(new Date());

        return jpaProductRepository.save(productEntity);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {
        return jpaProductRepository.save(productEntity);
    }

    @Override
    public ProductEntity deleteProduct(ProductEntity productEntityDB) {
        return jpaProductRepository.save(productEntityDB);
    }


    @Override
    public List<ProductEntity> findByCategory(CategoryEntity categoryEntity) {
        return jpaProductRepository.findByCategoryId(categoryEntity.getId());
    }

    @Override
    public ProductEntity updateStock(ProductEntity productEntity) {
        return jpaProductRepository.save(productEntity);
    }

    @Override
    public ProductEntity getVerifyProductEntity(String id) {
        ProductEntity productEntityDB = getProduct(id);
        if (null == productEntityDB)return null;

        return productEntityDB;
    }
}
