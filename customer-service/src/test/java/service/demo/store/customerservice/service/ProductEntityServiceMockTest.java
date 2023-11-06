package service.demo.store.customerservice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Desactiva el reemplazo de la base de datos
public class ProductEntityServiceMockTest {

    @Mock
    private JpaProductRepository jpaProductRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService =  new PSQLProductImpl(jpaProductRepository);
        ProductEntity computer =  ProductEntity.builder()
                .id("1")
                .name("computer")
                .category(CategoryEntity.builder().id("1").build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(jpaProductRepository.findById("1"))
                .thenReturn(Optional.of(computer));
        Mockito.when(jpaProductRepository.save(computer)).thenReturn(computer);

    }

    @Test
   public void whenValidGetID_ThenReturnProduct(){
        ProductEntity found = productService.getProduct("1");
       Assertions.assertThat(found.getName()).isEqualTo("computer");

   }

   @Test
   public void whenValidUpdateStock_ThenReturnNewStock(){
        //ProductEntity newStock = productService.updateStock("1",Double.parseDouble("8"));
        //Assertions.assertThat(newStock.getStock()).isEqualTo(13);
   }
}
