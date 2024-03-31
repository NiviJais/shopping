package com.java.dev.shopping.app.Service;

import com.java.dev.shopping.app.Entity.Order;
import com.java.dev.shopping.app.Entity.Transaction;
import com.java.dev.shopping.app.Exception.InvalidOrderException;
import com.java.dev.shopping.app.Exception.OrderAlreadyPaidException;
import com.java.dev.shopping.app.Exception.PaymentFailedException;
import com.java.dev.shopping.app.Exception.PaymentServerException;
import com.java.dev.shopping.app.Repository.OrderRepository;
import com.java.dev.shopping.app.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction processPayment(Long orderId, double amount) throws InvalidOrderException, PaymentFailedException, OrderAlreadyPaidException, PaymentServerException {
        // Check if order exists
        Order order = getOrderById(orderId);
        if (order == null) {
            throw new InvalidOrderException("Invalid order id");
        }

        // Check if order is already paid
        if (order.isPaid()) {
            throw new OrderAlreadyPaidException("Order is already paid for");
        }

        // Mock payment API response
        int statusCode = (int) (Math.random() * 5);
        switch (statusCode) {
            case 0:
                throw new PaymentFailedException("Payment Failed as amount is invalid");
            case 1:
                throw new PaymentFailedException("Payment Failed from bank");
            case 2:
                throw new PaymentFailedException("Payment Failed due to invalid order id");
            case 3:
                throw new PaymentServerException("No response from payment server");
            default:
                // Payment successful
                Transaction transaction = new Transaction();
                transaction.setOrder(order);
                transaction.setTransactionId("tran" + orderId);
                transaction.setStatus("successful");
                transactionRepository.save(transaction);
                return transaction;
        }
    }

    // Other methods (getOrderById, etc.)
    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId).orElse(null);
    }
}
