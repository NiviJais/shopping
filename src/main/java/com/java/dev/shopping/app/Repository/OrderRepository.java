package com.java.dev.shopping.app.Repository;

import com.java.dev.shopping.app.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Order findByUserIdAndId(Long userId, Long orderId);
}
