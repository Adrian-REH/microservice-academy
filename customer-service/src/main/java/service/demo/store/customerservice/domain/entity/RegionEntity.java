package service.demo.store.customerservice.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_regions")
public class RegionEntity {
    @Id
    private String id;
    private String name;

}
