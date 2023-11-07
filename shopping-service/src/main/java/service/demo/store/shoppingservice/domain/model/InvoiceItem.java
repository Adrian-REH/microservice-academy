package service.demo.store.shoppingservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItem  {

    private String id;
    @Positive(message = "El stock debe ser mayor que cero")
    @NotNull
    private Double quantity;
    @NotNull
    private Double  price;
    private String productId;
    private Double subTotal;
    private Product product;

}
