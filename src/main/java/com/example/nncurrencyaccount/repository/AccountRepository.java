package com.example.nncurrencyaccount.repository;

import com.example.nncurrencyaccount.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> getAccountsByUserId(Long userId);
}
