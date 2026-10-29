
package net.nineflas.ebankservice.services;

import net.nineflas.ebankservice.entities.BankAccount;
import net.nineflas.ebankservice.feign.CustomerRestClient;
import net.nineflas.ebankservice.model.Customer;
import net.nineflas.ebankservice.reposetotries.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {

    private final BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public EbankService(BankAccountRepository bankAccountRepository,CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }


    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }


    public BankAccount findById(String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Bank account not found with id: " + id
                        )
                );
        Customer customer = customerRestClient.getCustomerById(
                bankAccount.getCustomerId()
        );
        bankAccount.setCustomer(customer);
        return bankAccount;
    }


    public BankAccount save(BankAccount bankAccount) {
        try{

            Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return bankAccountRepository.save(bankAccount);

        }catch (Exception e){
              throw new RuntimeException(e.getMessage());
        }
    }
}
