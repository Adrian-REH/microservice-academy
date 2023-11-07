package service.demo.store.shoppingservice.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import service.demo.store.shoppingservice.domain.entity.InvoiceItemEntity;

public interface JpaInvoiceItemsRepository extends JpaRepository<InvoiceItemEntity, String> {
}
