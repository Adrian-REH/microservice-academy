package service.demo.store.customerservice.domain.port;

import service.demo.store.customerservice.domain.entity.CustomerEntity;
import service.demo.store.customerservice.domain.model.Customer;
import service.demo.store.customerservice.domain.model.Region;

import java.util.List;

public interface CustomerService {
    public List<Customer> findAllCustomer();
    public List<Customer> findCustomersByRegion(String regionId);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer custome );
    public Customer deleteCustomer(final String id);
    public  Customer getCustomer(final String id);

    public CustomerEntity verifiedId(final String id);

}
