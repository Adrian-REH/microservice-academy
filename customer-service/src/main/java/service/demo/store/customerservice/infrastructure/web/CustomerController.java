package service.demo.store.customerservice.infrastructure.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.demo.store.customerservice.application.*;
import service.demo.store.customerservice.domain.model.Customer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {


    private AllCustomer allCustomer;
    private AllCustomerByRegion allCustomerByRegion;
    private OneCustomer oneCustomer;
    private CreateCustomer createCustomer;
    private UpdateCustomer updateCustomer;
    private DeleteCustomer deleteCustomer;

    @GetMapping
    public ResponseEntity<List<Customer>> listCustomer(@RequestParam(name="regionId", required = false) String regionId){

        List<Customer> customers = new ArrayList<Customer>();

        if (regionId==null){
            customers= allCustomerByRegion.execute(regionId);
        }
        else{
            log.info("Fetching Customers with RegionId {}", regionId);

            customers =allCustomer.execute();
        }

        if (customers.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id){
        log.info("Fetching Customer with id {}", id);

        return  ResponseEntity.ok(oneCustomer.execute(id));
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        log.info("Creating Customer : {}", customer);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return  ResponseEntity.status( HttpStatus.CREATED).body(createCustomer.execute(customer));

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        log.info("Updating Customer with id {}", id);
        customer.setId(id);
        return  ResponseEntity.ok(updateCustomer.execute(customer));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") String id) {
        log.info("Fetching & Deleting Customer with id {}", id);
        return  ResponseEntity.ok(deleteCustomer.execute(id));
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
