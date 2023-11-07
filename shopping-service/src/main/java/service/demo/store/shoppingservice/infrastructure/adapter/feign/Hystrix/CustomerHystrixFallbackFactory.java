package service.demo.store.shoppingservice.infrastructure.adapter.feign.Hystrix;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import service.demo.store.shoppingservice.domain.model.Customer;
import service.demo.store.shoppingservice.domain.model.Region;
import service.demo.store.shoppingservice.domain.repository.feign.CustomerClient;
@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {


    @Override
    public ResponseEntity<Customer> getCustomer(String id, String authorizationHeader) {
        Customer customer = Customer.builder()
                .id("none")
                .numberID("none")
                .firstName("none")
                .lastName("none")
                .email("none")
                .region(Region.builder().name("none").build())
                .state("none")
                .photoUrl("none").build();
        return ResponseEntity.ok(customer);
    }
}
