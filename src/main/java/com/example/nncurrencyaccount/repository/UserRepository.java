package com.example.nncurrencyaccount.repository;

import com.example.nncurrencyaccount.entity.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AccountUser, Long> {
    List<AccountUser> getUserById(Long id);
}
