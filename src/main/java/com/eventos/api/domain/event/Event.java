package com.eventos.api.domain.event;


import com.eventos.api.domain.address.Address;
import com.eventos.api.domain.coupon.Coupon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table (name = "events")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private Date date;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Coupon> coupons;

}
