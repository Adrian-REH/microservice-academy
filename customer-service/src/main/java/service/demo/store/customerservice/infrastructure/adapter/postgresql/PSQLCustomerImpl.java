package service.demo.store.customerservice.infrastructure.adapter.postgresql;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.demo.store.customerservice.domain.entity.CustomerEntity;
import service.demo.store.customerservice.domain.entity.RegionEntity;
import service.demo.store.customerservice.domain.model.Customer;
import service.demo.store.customerservice.domain.model.Region;
import service.demo.store.customerservice.domain.port.CustomerService;
import service.demo.store.customerservice.domain.repository.jpa.JpaCustomerRepository;
import service.demo.store.customerservice.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PSQLCustomerImpl implements CustomerService {
    @Autowired
    JpaCustomerRepository customerRepository;
    @Autowired
    ModelMapper mapper;


    @Override
    public List<Customer> findAllCustomer() {


        List<CustomerEntity> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapper.map(customer, Customer.class)).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findCustomersByRegion(String regionId) {

        List<CustomerEntity> customers = customerRepository.findByRegionId(regionId);

        return customers.stream().map(customer -> mapper.map(customer, Customer.class)).collect(Collectors.toList());
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Optional<CustomerEntity> optCustomer = customerRepository.findById(customer.getId());
        if (optCustomer.isPresent()) {
            throw new ServiceException(600);
        }
        CustomerEntity customerDB = mapper.map(customer, CustomerEntity.class);

        customerDB.setState("CREATED");
        return mapper.map(customerRepository.save(customerDB), Customer.class);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity customerDB = verifiedId(customer.getId());


        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());


        return mapper.map(customerRepository.save(customerDB), Customer.class);


    }

    @Override
    public Customer deleteCustomer(String id) {
        CustomerEntity customerEntity = verifiedId(id);

        customerEntity.setState("DELETED");
        return mapper.map(customerRepository.save(customerEntity), Customer.class);
    }

    @Override
    public Customer getCustomer(String id) {
        return mapper.map(verifiedId(id), Customer.class);
    }

    @Override
    public CustomerEntity verifiedId(String id) {
        Optional<CustomerEntity> optCustomer = customerRepository.findById(id);
        if (optCustomer.isEmpty()) {
            throw new ServiceException(600);
        }
        return optCustomer.get();

    }


}
