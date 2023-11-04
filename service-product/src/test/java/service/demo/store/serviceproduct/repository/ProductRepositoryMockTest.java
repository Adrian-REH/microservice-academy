package service.demo.store.serviceproduct.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import service.demo.store.serviceproduct.entity.Category;
import service.demo.store.serviceproduct.entity.Product;
import service.demo.store.serviceproduct.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Desactiva el reemplazo de la base de datos
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .id("product01")
                .name("computer")
                .category(Category.builder().id("1").build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date()).build();
        productRepository.save(product01);

       List<Product> founds= productRepository.findByCategoryId(product01.getCategory().getId());

        Assertions.assertThat(founds.size()).isEqualTo(3);


    }
}
