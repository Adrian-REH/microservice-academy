package service.demo.store.productservice.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.demo.store.productservice.domain.entity.ProductEntity;

import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, String> {

    public List<ProductEntity> findByCategoryId(String categoryId);
}
