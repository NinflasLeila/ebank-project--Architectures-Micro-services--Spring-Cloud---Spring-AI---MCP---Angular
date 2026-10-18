
package net.nineflas.ebankservice.controllers;

import net.nineflas.ebankservice.entities.BankAccount;
import net.nineflas.ebankservice.services.EbankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankaccounts")
public class EbankRestController {

    private final EbankService ebankService;


    public EbankRestController(EbankService ebankService) {
        this.ebankService = ebankService;
    }


    @GetMapping("/accounts")
    public List<BankAccount> findAll() {
        return ebankService.findAll();
    }


    @GetMapping("/accounts/[id]")
    public BankAccount findById(@PathVariable String id) {
        return ebankService.findById(id);
    }


    @PostMapping("/accounts")
    public BankAccount save(@RequestBody BankAccount bankAccount) {
        return ebankService.save(bankAccount);
    }
}
