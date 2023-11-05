package service.demo.store.productservice.domain.port;

import service.demo.store.productservice.domain.entity.CategoryEntity;
import service.demo.store.productservice.domain.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public ProductEntity getVerifyProductEntity(String id);
    public List<ProductEntity> listAllProduct();
    public ProductEntity getProduct(String id);

    public ProductEntity createProduct(ProductEntity productEntity);
    public ProductEntity updateProduct(ProductEntity productEntity);
    public ProductEntity deleteProduct(ProductEntity productEntity);
    public List<ProductEntity> findByCategory(CategoryEntity categoryEntity);
    public ProductEntity updateStock(ProductEntity productEntity);
}
