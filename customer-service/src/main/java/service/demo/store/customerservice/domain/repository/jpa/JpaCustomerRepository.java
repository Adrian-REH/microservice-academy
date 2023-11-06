package service.demo.store.customerservice.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import service.demo.store.customerservice.domain.entity.CustomerEntity;
import service.demo.store.customerservice.domain.entity.RegionEntity;
import service.demo.store.customerservice.domain.model.Customer;

import java.util.List;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity,String> {
    public CustomerEntity findByNumberID(String numberID);
    public List<CustomerEntity> findByLastName(String lastName);
    public List<CustomerEntity> findByRegionId(String region);

}
