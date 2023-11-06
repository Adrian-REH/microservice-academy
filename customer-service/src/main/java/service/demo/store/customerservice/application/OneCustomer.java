package service.demo.store.customerservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.customerservice.domain.model.Customer;
import service.demo.store.customerservice.domain.port.CustomerService;

@AllArgsConstructor
@Slf4j
@Service
public class OneCustomer {

    private final CustomerService customerService;
    public Customer execute(String id)  {
        return customerService.getCustomer(id);
    }
}
