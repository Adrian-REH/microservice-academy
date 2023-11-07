package service.demo.store.shoppingservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.shoppingservice.domain.model.Invoice;
import service.demo.store.shoppingservice.domain.port.InvoiceService;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class OneInvoice {

    private final InvoiceService invoiceService;
    public Invoice execute(String id)  {
        return invoiceService.getInvoice(id);
    }
}
