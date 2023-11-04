package service.demo.store.serviceproduct.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import service.demo.store.serviceproduct.entity.Category;
import service.demo.store.serviceproduct.entity.Product;
import service.demo.store.serviceproduct.repository.ProductRepository;
import service.demo.store.serviceproduct.service.ProductService;
import service.demo.store.serviceproduct.service.ProductServiceImpl;

import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Desactiva el reemplazo de la base de datos
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService =  new ProductServiceImpl( productRepository);
        Product computer =  Product.builder()
                .id("1")
                .name("computer")
                .category(Category.builder().id("1").build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productRepository.findById("1"))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);

    }

    @Test
   public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct("1");
       Assertions.assertThat(found.getName()).isEqualTo("computer");

   }

   @Test
   public void whenValidUpdateStock_ThenReturnNewStock(){
        Product newStock = productService.updateStock("1",Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
   }
}
