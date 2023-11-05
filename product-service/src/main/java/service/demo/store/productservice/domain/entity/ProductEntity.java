package service.demo.store.productservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "tbl_products", schema = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    private String id;

    @NotEmpty(message = "El nombre no debe ser vacío")
    private String name;
    @Column
    private String description;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;
    @Column
    private Double price;
    @Column
    private String status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @NotNull(message = "La categoria no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private CategoryEntity category;
}
