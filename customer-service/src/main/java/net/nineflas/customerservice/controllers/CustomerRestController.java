package net.nineflas.customerservice.controllers;


import net.nineflas.customerservice.entities.Customer;
import net.nineflas.customerservice.repositories.CustomerRepository;
import net.nineflas.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRestController {


    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @GetMapping("/customers")
    public List<Customer> gelAllCustomers(){
        return customerService.gelAllCustomers();
    }


    @GetMapping("/customers/{id}")
    public Customer findCustomerById(@PathVariable  Long id ) {
        return customerService.findCustomerById(id);
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody  Customer customer){
        return customerService.saveCustomer(customer);
    }
}
