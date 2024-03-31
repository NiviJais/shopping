package com.java.dev.shopping.app.Repository;

import com.java.dev.shopping.app.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,String> {
    Coupon findByCode(String code);
}
