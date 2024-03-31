package com.java.dev.shopping.app.Repository;

import com.java.dev.shopping.app.Entity.Order;
import com.java.dev.shopping.app.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
