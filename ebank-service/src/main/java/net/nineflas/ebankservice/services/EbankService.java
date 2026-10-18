
package net.nineflas.ebankservice.services;

import net.nineflas.ebankservice.entities.BankAccount;
import net.nineflas.ebankservice.reposetotries.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {

    private final BankAccountRepository bankAccountRepository;


    public EbankService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }


    public BankAccount findById(String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Bank account not found with id: " + id)
                );
    }


    public BankAccount save(BankAccount bankAccount) {
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccountRepository.save(bankAccount);
    }
}
