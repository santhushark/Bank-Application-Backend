package com.highpeaksw.bankApplicationSpringBoot.business;


import com.highpeaksw.bankApplicationSpringBoot.data.BankCustomer;

import com.highpeaksw.bankApplicationSpringBoot.service.BankCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE} ,value = "/BankCustomer")
public class BankCustomerController {

    @Autowired
    private BankCustomerService bankCustomerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<BankCustomer> getAllCustomers() {
        System.out.println("customer Controller");
        return bankCustomerService.getAllCustomer();
    }

    @RequestMapping(value = "/addCustomer",method = RequestMethod.POST)
    public BankCustomer addToBankCustomerTbl(@RequestBody BankCustomer bankCustomer) {
        System.out.println("ADDING TO CUSTOMER TABLE");
        bankCustomerService.addToBankCustomerTbl(bankCustomer);
        return bankCustomer;
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public Optional<BankCustomer> getCustomer(@PathVariable("id") int id) {
        return bankCustomerService.getCustomer(id);
    }

}
