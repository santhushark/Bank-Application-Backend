package com.highpeaksw.bankApplicationSpringBoot.service;

import com.highpeaksw.bankApplicationSpringBoot.data.BankCustomer;
import com.highpeaksw.bankApplicationSpringBoot.repositories.BankCustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BankCustomerService {

    @Autowired
    private BankCustomerRepositories bankCustomerRepositories;

    public List<BankCustomer> getAllCustomer() {
        List<BankCustomer> customers = new ArrayList<>();
        bankCustomerRepositories.findAll().forEach(customers::add);
        return customers;
    }

    public void addToBankCustomerTbl(BankCustomer bankCustomer) {
        bankCustomerRepositories.save(bankCustomer);
    }

    public Optional<BankCustomer> getCustomer(int id) {

        Optional<BankCustomer> byId = bankCustomerRepositories.findById(id);
        return byId;
    }


}
