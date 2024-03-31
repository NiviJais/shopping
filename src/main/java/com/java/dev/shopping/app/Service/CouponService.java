package com.java.dev.shopping.app.Service;

import com.java.dev.shopping.app.Entity.Coupon;
import com.java.dev.shopping.app.Repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;

    public Coupon addCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Map<String, Integer> fetchCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        Map<String, Integer> couponMap = new HashMap<>();
        for (Coupon coupon : coupons) {
            couponMap.put(coupon.getCode(), coupon.getDiscountPercentage());
        }
        return couponMap;
    }

    // Other methods
}
