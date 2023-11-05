package service.demo.store.productservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
        private String id;
        private String name;
        private String description;
        private Double stock;
        private Double price;
        private String status;
        private Date createAt;
        private Category category;
}