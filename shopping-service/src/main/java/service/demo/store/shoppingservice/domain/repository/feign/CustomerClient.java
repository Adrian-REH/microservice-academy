package service.demo.store.shoppingservice.domain.repository.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import service.demo.store.shoppingservice.domain.model.Customer;
import service.demo.store.shoppingservice.infrastructure.adapter.feign.Hystrix.CustomerHystrixFallbackFactory;

@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id, @RequestHeader("Authorization") String authorizationHeader);

}
