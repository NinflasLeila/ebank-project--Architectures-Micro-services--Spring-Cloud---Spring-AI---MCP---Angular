package net.nineflas.ebankservice.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import net.nineflas.ebankservice.model.Customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name ="customerService",fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long id);




    default  Customer getDefaultCustomer(Exception e, Long id){
        return new Customer(id,"NoName","Not available");
    }
}