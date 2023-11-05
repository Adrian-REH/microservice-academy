package service.demo.store.customerservice.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import service.demo.store.productservice.domain.entity.CategoryEntity;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.repository.jpa.JpaProductRepository;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Desactiva el reemplazo de la base de datos
public class ProductEntityRepositoryMockTest {

    @Autowired
    private JpaProductRepository jpaProductRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        ProductEntity productEntity01 = ProductEntity.builder()
                .id("product01")
                .name("computer")
                .category(CategoryEntity.builder().id("1").build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date()).build();
        jpaProductRepository.save(productEntity01);

       List<ProductEntity> founds= jpaProductRepository.findByCategoryId(productEntity01.getCategory().getId());

        Assertions.assertThat(founds.size()).isEqualTo(3);


    }
}
