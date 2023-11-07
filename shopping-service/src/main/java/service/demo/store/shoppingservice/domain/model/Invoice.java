package service.demo.store.shoppingservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Invoice {
    private String id;
    private String numberInvoice;
    private String description;
    private String customerId;
    private Date createAt;
    @NotNull
    private List<InvoiceItem> items;
    private String state;
    private Customer customer;


}
