package com.highpeaksw.bankApplicationSpringBoot.business;

import com.highpeaksw.bankApplicationSpringBoot.data.BankAccount;
import com.highpeaksw.bankApplicationSpringBoot.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/BankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<BankAccount> getAllAccounts() {
        System.out.println("customer Controller");
        return bankAccountService.getAllAccounts();
    }

    @RequestMapping(value = "/accountByCustomerID/{customerId}", method = RequestMethod.GET)
    public List<BankAccount> getAccountsByCustomerId(@PathVariable int customerId) {
        return bankAccountService.getAccountsByCustomerId(customerId);
    }

    @RequestMapping(value = "/accountByAccountNumber/{accountId}",method = RequestMethod.GET)
    public Optional<BankAccount> getAccountById(@PathVariable int accountId){
        return bankAccountService.getAccountById(accountId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addBankAccount")
    public boolean addToBankAccountTbl(@RequestBody BankAccount bankAccount) {
        bankAccountService.addToBankAccountTbl(bankAccount);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateBankAccount")
    public void updateAccountTable(@RequestBody BankAccount bankAccount) {
        bankAccountService.updateAccountTable(bankAccount);
    }

}
