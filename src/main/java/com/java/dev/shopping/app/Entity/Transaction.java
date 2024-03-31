package com.java.dev.shopping.app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private String status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
