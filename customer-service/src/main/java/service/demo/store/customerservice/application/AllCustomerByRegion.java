package service.demo.store.customerservice.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import service.demo.store.customerservice.domain.model.Customer;
import service.demo.store.customerservice.domain.model.Region;
import service.demo.store.customerservice.domain.port.CustomerService;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class AllCustomerByRegion {

    private final CustomerService productService;
    public List<Customer> execute(String regionId)  {
        return productService.findCustomersByRegion(regionId);
    }
}
