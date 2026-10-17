package net.nineflas.customerservice.services;


import net.nineflas.customerservice.entities.Customer;
import net.nineflas.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> gelAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id ){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
    }


    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
