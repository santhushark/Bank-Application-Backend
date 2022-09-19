package com.highpeaksw.bankApplicationSpringBoot.service;

import com.highpeaksw.bankApplicationSpringBoot.data.BankAccount;
import com.highpeaksw.bankApplicationSpringBoot.data.BankCustomer;
import com.highpeaksw.bankApplicationSpringBoot.repositories.BankAccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepositories bankAccountRepositories;

    public List<BankAccount> getAllAccounts(){
        List<BankAccount> accounts = new ArrayList<>();
        bankAccountRepositories.findAll().forEach(accounts :: add);
        return accounts;
    }

    public List<BankAccount> getAccountsByCustomerId(int customerId){
        return bankAccountRepositories.getAccountsByCustomerId(customerId);
    }

    public Optional<BankAccount> getAccountById(@PathVariable int accountId){
        return bankAccountRepositories.findById(accountId);
    }

    public void addToBankAccountTbl(BankAccount bankAccount){
        bankAccountRepositories.save(bankAccount);
    }

    public void updateAccountTable(BankAccount bankAccount){
        bankAccountRepositories.save(bankAccount);
    }

}
