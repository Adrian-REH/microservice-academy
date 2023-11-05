package service.demo.store.productservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categories", schema = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {
    @Id
    @Column
    private String id;
    @Column
    private String name;



}
