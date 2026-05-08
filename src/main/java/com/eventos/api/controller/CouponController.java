package com.eventos.api.controller;


import com.eventos.api.domain.coupon.Coupon;
import com.eventos.api.domain.coupon.CouponRequestDTO;
import com.eventos.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> addCouponsToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO date){
        Coupon coupons = couponService.createCoupon(eventId, date);
        return ResponseEntity.ok(coupons);
    }


}
