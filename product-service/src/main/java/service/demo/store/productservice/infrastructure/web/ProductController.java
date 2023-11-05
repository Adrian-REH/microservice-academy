package service.demo.store.productservice.infrastructure.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.demo.store.productservice.application.*;
import service.demo.store.productservice.application.*;
import service.demo.store.productservice.domain.entity.ProductEntity;
import service.demo.store.productservice.domain.port.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService ;
    private UpdateStock updateStock ;
    private UpdateProduct updateProduct ;
    private DeleteProduct deleteProduct ;
    private OneProduct oneProduct ;
    private CreateProduct createProduct ;
    private ListAllProduct listAllProduct ;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> listProduct(@RequestParam(name = "categoryId", required = false) String categoryId){


        List<ProductEntity> productEntities = listAllProduct.execute(categoryId);



        return productEntities.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(productEntities);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductEntity productEntity, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createProduct.execute(productEntity));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable("id") String id) {
        ProductEntity productEntity =  oneProduct.execute(id);

        return null== productEntity?ResponseEntity.notFound().build(): ResponseEntity.ok(productEntity);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> deleteProduct(@PathVariable("id") String id){
        ProductEntity productEntityDelete = deleteProduct.execute(id);

        return productEntityDelete == null?ResponseEntity.notFound().build(): ResponseEntity.ok(productEntityDelete);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") String id, @RequestBody ProductEntity productEntity){
        ProductEntity productEntityDB =  updateProduct.execute(id,productEntity);
        return productEntityDB == null? ResponseEntity.notFound().build():ResponseEntity.ok(productEntityDB);
    }

    @PutMapping (value = "/{id}/stock")
    public ResponseEntity<ProductEntity> updateStockProduct(@PathVariable  String id , @RequestParam(name = "quantity", required = true) Double quantity){
        ProductEntity productEntity = updateStock.execute(id, quantity);
        return productEntity == null ? ResponseEntity.notFound().build():ResponseEntity.ok(productEntity);
    }


    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
