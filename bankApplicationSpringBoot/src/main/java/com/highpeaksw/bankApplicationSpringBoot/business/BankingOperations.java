package com.highpeaksw.bankApplicationSpringBoot.business;

import com.highpeaksw.bankApplicationSpringBoot.data.BankAccount;
import com.highpeaksw.bankApplicationSpringBoot.data.BankCustomer;
import com.highpeaksw.bankApplicationSpringBoot.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

public class BankingOperations {

//    @Autowired
//    private BankAccountController bankAccountController;
//
//    @Autowired
//    private BankCustomerController bankCustomerController;

    private void initLogin(BankCustomer customerInstance) {
        byte retryCount = 1;
        boolean logout = false;
        System.out.println("PLEASE LOGIN");
        BankAccount temp;
        while (true) {
            if ((temp = tryLogin(customerInstance)) != null) {
                initBankOperations(temp);
                logout = true;
            }
            if (!logout) {
                if (++retryCount < 3) {
                    System.out.println("INVALID CREDENTIALS: PLEASE TRY LOGGING IN AGAIN");
                } else {
                    System.out.println("REACHED MAX RETRIES");
                    break;
                }
            } else break;
        }
    }

    private void initBankOperations(BankAccount accountInstance) {
        Scanner sf = new Scanner(System.in);
        boolean logout = false;
        char choice;
        while (!logout) {
            System.out.println("PLEASE SELECT THE KIND OF OPERATION YOU WANT TO PERFORM");
            System.out.println("*****   A) DEPOSIT     B) WITHDRAW    C) BALANCE ENQUIRY     D) LOGOUT    ******");
            choice = Character.toUpperCase(sf.next().charAt(0));
            switch (choice) {

                case 'A': {
                    if (accountInstance.getAccountType().equals("SAVINGS")) {
                        if (accountInstance.isAccountActive()) {
                            accountInstance.setAccountBalance(Utilities.scanDepositAmount() + accountInstance.getAccountBalance());
                          //  bankAccountController.updateAccountTable(accountInstance);
                            System.out.println("DEPOSIT SUCCESSFULL!!");
                        }else {
                            System.out.println("ACCOUNT IS INACTIVE");
                        }
                    } else if (accountInstance.getAccountType().equals("CURRENT")) {
                        if (accountInstance.isAccountActive()) {
                            accountInstance.setAccountBalance(Utilities.scanDepositAmount() + accountInstance.getAccountBalance());
                      //      bankAccountController.updateAccountTable(accountInstance);
                            System.out.println("DEPOSIT SUCCESSFULL!!");
                        }else {
                            System.out.println("ACCOUNT IS INACTIVE");
                        }
                    } else if (accountInstance.getAccountType().equals("FIXED DEPOSIT")) {
                        if (accountInstance.isAccountActive()) {
                            System.out.println("FD ACCOUNT!! ONLY ONE TIME DEPOSIT");
                        }else {
                            System.out.println("ACCOUNT IS INACTIVE");
                        }
                    } else if (accountInstance.getAccountType().equals("RECURRING DEPOSIT")) {
                        if (accountInstance.isAccountActive()) {
                            if (accountInstance.getRdCount() < accountInstance.getTenure()) {
                                System.out.println("PRESS Y/y to make your monthly deposit ? ");
                                choice = sf.next().charAt(0);
                                if (choice == 'Y' || choice == 'y') {
                                    accountInstance.setAccountBalance(accountInstance.getAccountBalance() + accountInstance.getRecurringDepositAmt());
                                    int rdCount = accountInstance.getRdCount();
                                    accountInstance.setRdCount(++rdCount);
                              //      bankAccountController.updateAccountTable(accountInstance);
                                    System.out.println("Monthly DEPOSIT Successfull !!");
                                }
                            } else {
                                System.out.println("YOUR RD Amount has MATURED. PLEASE WITHDRAW");
                            }
                        }else {
                            System.out.println("INACTIVE ACCOUNT! NO operations can be performed!!");
                            System.out.println("ACCOUNT HAS MATURED AND MONEY IS WITHDRAWN");
                        }
                    } else System.out.println("INVALID ACCOUNT TYPE!!");
                }
                break;

                case 'B': {

                    if (accountInstance.getAccountType().equals("SAVINGS")) {
                        if (accountInstance.isAccountActive()) {
                            double withdrawAmount = Utilities.scanWithdrawAmount();
                            if (accountInstance.getAccountBalance() >= withdrawAmount) {
                                accountInstance.setAccountBalance(accountInstance.getAccountBalance() - withdrawAmount);
                           //     bankAccountController.updateAccountTable(accountInstance);
                            } else System.out.println("INSUFFICIENT BALANCE");
                        }else {
                            System.out.println("ACCOUNT IS INACTIVE");
                        }
                    } else if (accountInstance.getAccountType().equals("CURRENT")) {
                        if (accountInstance.isAccountActive()) {
                            double withdrawAmount = Utilities.scanWithdrawAmount();
                            if (accountInstance.getAccountBalance() >= withdrawAmount) {
                                accountInstance.setAccountBalance(accountInstance.getAccountBalance() - withdrawAmount);
                          //      bankAccountController.updateAccountTable(accountInstance);
                            } else System.out.println("INSUFFICIENT BALANCE");
                        }else {
                            System.out.println("ACCOUNT IS INACTIVE");
                        }
                    } else if (accountInstance.getAccountType().equals("FIXED DEPOSIT")) {
                        if (accountInstance.isAccountActive()) {
                            System.out.println("CANNOT WITHDRAW UNTIL FD DEPOSIT TENURE EXPIRES");
                        }else {
                            System.out.println("ACCOUNT IS INACTIVE");
                        }
                    } else if (accountInstance.getAccountType().equals("RECURRING DEPOSIT")) {
                        if (accountInstance.isAccountActive()) {
                            if (accountInstance.getRdCount() == accountInstance.getTenure()) {
                                System.out.println("YOUR Account has matured");
                                System.out.println("WITHDRAW Amount: " + accountInstance.getAccountBalance());
                                System.out.println("Withdraw SUCCESSFULL!!");
                                accountInstance.setAccountActive(false);
                                accountInstance.setRecurringDepositAmt(0);
                         //       bankAccountController.updateAccountTable(accountInstance);
                            } else {
                                System.out.println("CANNOT WITHDRAW UNTIL ACCOUNT MATURITY");
                            }
                        }else {
                            System.out.println("INACTIVE ACCOUNT! NO operations can be performed!!");
                            System.out.println("ACCOUNT HAS MATURED AND MONEY IS WITHDRAWN");
                        }
                    } else System.out.println("INVALID ACCOUNT TYPE!! DEBUG CODE");

                }
                break;

                case 'C': {
                    if (accountInstance.isAccountActive()) {
                        System.out.println("YOUR BALANCE IS : " + accountInstance.getAccountBalance());
                    }else {
                        System.out.println("INACTIVE ACCOUNT!!");
                    }
                }
                break;

                case 'D': {
                    logout = true;
                }
                break;

                default:
                    System.out.println("INVALID OPTION!! NO OPERATION ASSOCIATED WITH IT");
                    break;
            }

        }
    }

    public void initCreateAccount(BankCustomer currentInstance) {
        currentInstance = checkForExistingCustomer();
        if (currentInstance.getPanNumber() != null) {
            System.out.println("You are an EXISTING customer.Please provide BELOW DETAILS to continue creating account.");
            BankAccount accountCreationInstance = scanUserDetails(currentInstance);
            if (accountCreationInstance != null) {
               // if (bankAccountController.addToBankAccountTbl(accountCreationInstance)) {
                    System.out.println("Account creation successful.Continue banking.");
                    System.out.println("YOUR ACCOUNT:- ");
                    System.out.println("A/c Number: "+ accountCreationInstance.getAccountNumber()+"  A/c Type: "+ accountCreationInstance.getAccountType());
                //} else {
                    System.out.println("ACCOUNT CREATION FAILED!! Retry");
                //}
            } else {
                System.out.println("ACCOUNT CREATION FAILED");
            }
        }else {
            System.out.println("NEW CUSTOMER!! PLEASE REGISTER");
            if (customerRegistration(currentInstance)){
                System.out.println("customer registration SUCCESSFULL");
                System.out.println("Please provide BELOW DETAILS to continue creating account.");
                BankAccount accountCreationInstance = scanUserDetails(currentInstance);
                if (accountCreationInstance != null) {
                   // if (bankAccountController.addToBankAccountTbl(accountCreationInstance)) {
                        System.out.println("Account creation successful.Continue banking.");
                        System.out.println("YOUR ACCOUNT:- ");
                        System.out.println("A/c Number: " + accountCreationInstance.getAccountNumber() + "  A/c Type: " + accountCreationInstance.getAccountType());
                    //} else {
                        System.out.println("ACCOUNT CREATION FAILED!! Retry");
                    //}
                }
            }
        }
    }




    private BankAccount scanUserDetails(BankCustomer customerInstance) {
        BankAccount accountCreationInstance = new BankAccount();

        accountCreationInstance.setUserName(Utilities.scanUserName());
        if (accountCreationInstance.getUserName() == null)
            return null;

        accountCreationInstance.setPassword(Utilities.scanPassword());
        if (accountCreationInstance.getPassword() == null)
            return null;

        accountCreationInstance.setAccountNumber(Utilities.generateAccountNumber());
        accountCreationInstance.setCustomer(customerInstance);
        char choice;
        Scanner sf = new Scanner(System.in);
        System.out.println("SELECT THE TYPE OF ACCOUNT YOU WANT TO CREATE");
        System.out.println("**** A) SAVINGS ACCOUNT    B) CURRENT ACCOUNT    C) FD ACCOUNT    D) RD ACCOUNT");
        choice = Character.toUpperCase(sf.next().charAt(0));
        switch (choice){
            case 'A' : {
                if (!isSavingCurrentAccountExist(customerInstance.getId(), "SAVINGS")) {
                    accountCreationInstance.setAccountType("SAVINGS");
                    accountCreationInstance.setRateOfInterest(5);
                    accountCreationInstance.setAccountActive(true);
                }else {
                    accountCreationInstance = null;
                    System.out.println("Only one SAVINGS ACCOUNT per customer");
                }
            }break;

            case 'B' : {
                if (!isSavingCurrentAccountExist(customerInstance.getId(), "CURRENT")) {
                    accountCreationInstance.setAccountType("CURRENT");
                    accountCreationInstance.setRateOfInterest(0);
                    accountCreationInstance.setAccountActive(true);
                }else{
                    accountCreationInstance = null;
                    System.out.println("Only one CURRENT ACCOUNT per customer");
                }
            }break;

            case 'C' : {
                accountCreationInstance.setAccountType("FIXED DEPOSIT");
                accountCreationInstance.setRateOfInterest(10);
                accountCreationInstance.setAccountBalance(Utilities.scanDepositAmount());
                accountCreationInstance.setTenure(Utilities.scanTenure("FIXED DEPOSIT"));
                accountCreationInstance.setAccountActive(true);
            }break;

            case 'D' : {
                accountCreationInstance.setAccountType("RECURRING DEPOSIT");
                accountCreationInstance.setRateOfInterest(10);
                System.out.println("MONTHLY DEPOSIT AMOUNT");
                accountCreationInstance.setRecurringDepositAmt(Utilities.scanDepositAmount());
                accountCreationInstance.setTenure(Utilities.scanTenure("RECURRING DEPOSIT"));
                accountCreationInstance.setAccountActive(true);
            }break;

            default: {
                System.out.println("INVALID CHOICE!!");
                break;
            }
        }
        return accountCreationInstance;
    }



    private BankCustomer checkForExistingCustomer(){
        String aadharNumber = Utilities.scanAadharNumber();
        BankCustomer currentInstance;
        if((currentInstance = dbCheckForExistingCustomer(aadharNumber)) == null){
            currentInstance = new BankCustomer();
            currentInstance.setAadharNumber(aadharNumber);
            return currentInstance;
        }else
            return currentInstance;
    }

    private boolean customerRegistration(BankCustomer currentInstance){
        System.out.println("You are a new customer.Please enter details to register with the bank before account creation.");
        currentInstance.setName(Utilities.scanCustomerName());
        if (currentInstance.getName() == null)return false;

        currentInstance.setPanNumber(Utilities.scanPanNumber());
        if (currentInstance.getPanNumber() == null)return false;

       // if(bankCustomerController.addToBankCustomerTbl(currentInstance))
            return true;
        //else return false;
    }

    public void initNetBanking(String aadharNumber){
        if (aadharNumber == null) {
            aadharNumber = Utilities.scanAadharNumber();
        }
        BankCustomer temp = dbCheckForExistingCustomer(aadharNumber);
        if (temp.getPanNumber() != null){
            int id = temp.getId();
            if(accountList(id)){
                initLogin(temp);
            }else System.out.println("NO Accounts Linked to the CUSTOMER");
        }else System.out.println("INVALID CUSTOMER");
    }

    private BankAccount tryLogin(BankCustomer customerInstance){
        String accountNumber = Utilities.scanAccountNumber();
        String userName = Utilities.scanUserName();
        String password = Utilities.scanPassword();
     //   List<BankAccount> accounts = bankAccountController.getAccountsByCustomerId(customerInstance.getId());
       // for (BankAccount obj: accounts){
         //   if ((obj.getAccountNumber() == Integer.parseInt(accountNumber)) && (obj.getUserName().equals(userName)) && (obj.getPassword().equals(password))) {
           //     return obj;
            //}
        //}
        return null;
    }

    private BankCustomer dbCheckForExistingCustomer(String aadharNumber){
      //  Iterable<BankCustomer> temp = bankCustomerController.getAllCustomers();
       // for (BankCustomer customer : temp) {
         //   if (customer.getAadharNumber().equals(aadharNumber)) {
           //     return customer;
            //}
        //}
        return null;
    }

    private Boolean isSavingCurrentAccountExist(int customerId, String accountType){
      //  List<BankAccount> accounts = bankAccountController.getAccountsByCustomerId(customerId);
       // for (BankAccount obj: accounts){
         //   if (obj.getAccountType().equals(accountType)){
           //     return true;
            //}
        //}
        return false;
    }

    public Boolean accountList(int customerId){
//        List<BankAccount> accounts = bankAccountController.getAccountsByCustomerId(customerId);
//        for (BankAccount obj: accounts){
//            System.out.print("*****   ACCOUNT TYPE : " + obj.getAccountType());
//            System.out.println("  *****   ACCOUNT NUMBER " + obj.getAccountNumber());
//        }
        return true;
    }


}