package service.demo.store.shoppingservice.domain.entity;

import lombok.Data;
import service.demo.store.shoppingservice.domain.model.Product;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Data
@Table(name = "tbl_invoice_items", schema="products")
public class InvoiceItemEntity {
    @Id
    private String id;
    @Positive(message = "El stock debe ser mayor que cero")
    private Double quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;


    @Transient
    private Double subTotal;

    @Transient
    private Product product;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity >0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }

    public InvoiceItemEntity(){
        this.quantity=(double) 0;
        this.price=(double) 0;

    }

}
