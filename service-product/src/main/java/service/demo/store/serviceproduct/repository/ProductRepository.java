package service.demo.store.serviceproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.demo.store.serviceproduct.entity.Category;
import service.demo.store.serviceproduct.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {

    public List<Product> findByCategoryId(String categoryId);
}
