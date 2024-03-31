package com.java.dev.shopping.app.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    private String code;

    private int discountPercentage;

    // One-to-many relationship with Order
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();


}
