package service.demo.store.shoppingservice.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.demo.store.shoppingservice.domain.entity.InvoiceEntity;

import java.util.List;

@Repository
public interface JpaInvoiceRepository extends JpaRepository<InvoiceEntity,String> {
    public List<InvoiceEntity> findByCustomerId(String customerId );
    public InvoiceEntity findByNumberInvoice(String numberInvoice);


}
