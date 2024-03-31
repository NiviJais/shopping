package com.java.dev.shopping.app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double amount;
    private String couponCode;
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_code", referencedColumnName = "code")
    private Coupon coupon;

    // One-to-one relationship with Payment
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Transaction transaction;

}
