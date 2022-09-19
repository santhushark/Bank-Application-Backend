package com.highpeaksw.bankApplicationSpringBoot.repositories;

import com.highpeaksw.bankApplicationSpringBoot.data.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepositories extends CrudRepository<BankAccount,Integer> {

    @Query("SELECT c from BankAccount c where Customer_Id = :customerId")
    List<BankAccount> getAccountsByCustomerId(@Param("customerId") int customerId);

}
