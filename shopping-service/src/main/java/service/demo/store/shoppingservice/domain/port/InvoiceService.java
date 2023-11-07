package service.demo.store.shoppingservice.domain.port;

import service.demo.store.shoppingservice.domain.entity.InvoiceEntity;
import service.demo.store.shoppingservice.domain.model.Invoice;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> findAllInvoice();
    public  Invoice getInvoice(final String id);
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice );
    public Invoice deleteInvoice(final String id);
    public InvoiceEntity verifiedId(final String id);

}
