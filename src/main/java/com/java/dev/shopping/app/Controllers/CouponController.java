package com.java.dev.shopping.app.Controllers;

import com.java.dev.shopping.app.Entity.Coupon;
import com.java.dev.shopping.app.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/addCoupon")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon) {
        Coupon newCoupon = couponService.addCoupon(coupon);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCoupon);
    }

    @GetMapping("/fetchCoupons")
    public ResponseEntity<Map<String, Integer>> fetchCoupons() {
        Map<String, Integer> coupons = couponService.fetchCoupons();
        return ResponseEntity.ok(coupons);
    }
}

