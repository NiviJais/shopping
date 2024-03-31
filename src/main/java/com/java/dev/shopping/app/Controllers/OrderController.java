package com.java.dev.shopping.app.Controllers;

import com.java.dev.shopping.app.Entity.Order;
import com.java.dev.shopping.app.Exception.InvalidCouponException;
import com.java.dev.shopping.app.Exception.InvalidQuantityException;
import com.java.dev.shopping.app.Exception.OrderNotFoundException;
import com.java.dev.shopping.app.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order newOrder = orderService.placeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @PostMapping("/{userId}/order")
    public ResponseEntity<Order> placeOrder(@PathVariable Long userId, @RequestParam int quantity, @RequestParam String couponCode){

        try{
            Order order = orderService.createOrder(userId, quantity, couponCode);
            return ResponseEntity.ok(order);
        }catch(InvalidQuantityException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch(InvalidCouponException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("{userId}/orders")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId){
        List<Order> orderList = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        try {
            Order order = orderService.getOrder(userId, orderId);
            return ResponseEntity.ok(order);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
