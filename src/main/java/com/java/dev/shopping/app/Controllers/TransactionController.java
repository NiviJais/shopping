package com.java.dev.shopping.app.Controllers;

import com.java.dev.shopping.app.Entity.Transaction;
import com.java.dev.shopping.app.Exception.InvalidOrderException;
import com.java.dev.shopping.app.Exception.OrderAlreadyPaidException;
import com.java.dev.shopping.app.Exception.PaymentFailedException;
import com.java.dev.shopping.app.Exception.PaymentServerException;
import com.java.dev.shopping.app.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.addTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<Transaction> processPayment(@PathVariable Long userId, @PathVariable Long orderId, @RequestParam double amount) {
        try {
            Transaction transaction = transactionService.processPayment(orderId, amount);
            return ResponseEntity.ok(transaction);
        } catch (InvalidOrderException | PaymentFailedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (OrderAlreadyPaidException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(null);
        } catch (PaymentServerException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(null);
        }
    }
}

