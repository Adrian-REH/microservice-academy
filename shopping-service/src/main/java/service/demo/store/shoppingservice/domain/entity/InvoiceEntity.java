package service.demo.store.shoppingservice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.demo.store.shoppingservice.domain.model.Customer;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tlb_invoices", schema="products")
public class InvoiceEntity {
    @Id
    private String id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;



    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItemEntity> items=new ArrayList<>();

    private String state;

    @Transient
    private Customer customer;


    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

}
