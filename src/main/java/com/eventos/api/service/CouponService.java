package com.eventos.api.service;


import com.amazonaws.services.s3.AmazonS3;
import com.eventos.api.domain.coupon.Coupon;
import com.eventos.api.domain.coupon.CouponRequestDTO;
import com.eventos.api.domain.event.Event;
import com.eventos.api.repositories.CouponRepository;
import com.eventos.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon(UUID eventId, CouponRequestDTO data){
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon newCoupon = new Coupon();
        newCoupon.setCode(data.code());
        newCoupon.setDiscount(data.discount());
        newCoupon.setValid(new Date(data.valid()));
        newCoupon.setEvent(event);

        return couponRepository.save(newCoupon);

    }
}
