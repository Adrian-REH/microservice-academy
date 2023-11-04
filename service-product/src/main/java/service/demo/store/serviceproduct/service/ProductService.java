package service.demo.store.serviceproduct.service;

import service.demo.store.serviceproduct.entity.Category;
import service.demo.store.serviceproduct.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> listAllProduct();
    public Product getProduct(String id);

    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public  Product deleteProduct(String id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(String id, Double quantity);
}
