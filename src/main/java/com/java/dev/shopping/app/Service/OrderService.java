package com.java.dev.shopping.app.Service;

import com.java.dev.shopping.app.Entity.Coupon;
import com.java.dev.shopping.app.Entity.Order;
import com.java.dev.shopping.app.Entity.User;
import com.java.dev.shopping.app.Exception.InvalidCouponException;
import com.java.dev.shopping.app.Exception.InvalidQuantityException;
import com.java.dev.shopping.app.Exception.OrderNotFoundException;
import com.java.dev.shopping.app.Repository.CouponRepository;
import com.java.dev.shopping.app.Repository.OrderRepository;
import com.java.dev.shopping.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }
    public Order createOrder(Long userId, int quantity, String couponCode) throws InvalidQuantityException, InvalidCouponException {
        // Retrieve user details from userId (Assuming User entity is available)
        User user = getUserById(userId);

        // Check if quantity is valid
        if (quantity < 1 || quantity > 100) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        // Fetch coupon details
        Coupon coupon = null;
        if (couponCode != null && !couponCode.isEmpty()) {
            coupon = couponRepository.findByCode(couponCode);
            if (coupon == null) {
                throw new InvalidCouponException("Invalid coupon");
            }
        }

        // Calculate amount
        double price = 100; // Assuming fixed price per item
        double discount = (coupon != null) ? (coupon.getDiscountPercentage() / 100.0) * price * quantity : 0;
        double amount = price * quantity - discount;

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setQuantity(quantity);
        order.setAmount(amount);
        order.setCouponCode((coupon != null) ? coupon.getCode() : null);
        orderRepository.save(order);

        // Update available quantity in inventory (assuming there's an inventory service)
        updateInventory(quantity);

        return order;
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrder(Long userId, Long orderId) throws OrderNotFoundException {
        Order order = orderRepository.findByUserIdAndId(userId, orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order not found for user with id: " + userId + " and order id: " + orderId);
        }
        return order;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void updateInventory(int quantity) {
        // Implement logic to update inventory
    }
}
