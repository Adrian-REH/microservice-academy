package service.demo.store.shoppingservice.infrastructure.adapter.postgresql;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.demo.store.shoppingservice.domain.entity.InvoiceEntity;
import service.demo.store.shoppingservice.domain.model.Customer;
import service.demo.store.shoppingservice.domain.model.Invoice;
import service.demo.store.shoppingservice.domain.model.InvoiceItem;
import service.demo.store.shoppingservice.domain.model.Product;
import service.demo.store.shoppingservice.domain.port.InvoiceService;
import service.demo.store.shoppingservice.domain.repository.feign.CustomerClient;
import service.demo.store.shoppingservice.domain.repository.feign.ProductClient;
import service.demo.store.shoppingservice.domain.repository.jpa.JpaInvoiceRepository;
import service.demo.store.shoppingservice.exception.ServiceException;
import service.demo.store.shoppingservice.utils.MakeHeaderAuth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PSQLInvoiceImpl implements InvoiceService {
    @Autowired
    JpaInvoiceRepository invoiceRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    CustomerClient customerClient;
    @Autowired
    ProductClient productClient;


    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRepository.findAll().stream().map(invoice -> mapper.map(invoice, Invoice.class)).collect(Collectors.toList());
    }

    @Override
    public Invoice getInvoice(String id) {
        Invoice invoice = mapper.map(verifiedId(id), Invoice.class);
        Customer customer = customerClient.getCustomer(invoice.getCustomerId(), MakeHeaderAuth.makeProductRequest()).getBody();
        invoice.setCustomer(customer);

        List<InvoiceItem> listItems = invoice.getItems().stream().map(invoiceItem -> {

            Product product = productClient.getProduct(invoiceItem.getProductId(),MakeHeaderAuth.makeProductRequest()).getBody();
            invoiceItem.setProduct(product);
            return invoiceItem;

        }).collect(Collectors.toList());
        invoice.setItems(listItems);
        return invoice;


    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Optional<InvoiceEntity> optInvoice = invoiceRepository.findById(invoice.getId());
        if (optInvoice.isPresent()) {
            return invoice;//TODO o lanzar una exception que diga que ya existe
        }
        invoice.setState("CREATED");

        invoiceRepository.save(mapper.map(invoice, InvoiceEntity.class));

        invoice.getItems().forEach(item ->{
            productClient.updateStockProduct(item.getProductId(),
                    item.getQuantity()*-1,"Basic Y2xpZW50OmNsaWVudA==");
        });

        return invoice;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        verifiedId(invoice.getId());

        invoiceRepository.save(mapper.map(invoice, InvoiceEntity.class));
        return invoice;
    }

    @Override
    public Invoice deleteInvoice(String id) {
        InvoiceEntity invoice = verifiedId(id);
        invoice.setState("DELETED");
        invoiceRepository.save(invoice);
        return mapper.map(invoice, Invoice.class);
    }

    @Override
    public InvoiceEntity verifiedId(String id) {
        Optional<InvoiceEntity> optCustomer = invoiceRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new ServiceException(600);
        }
        return optCustomer.get();

    }
}
