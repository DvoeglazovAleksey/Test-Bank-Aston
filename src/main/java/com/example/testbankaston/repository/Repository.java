package com.example.testbankaston.repository;

import com.example.testbankaston.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Account, Long> {
}
