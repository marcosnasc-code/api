package com.eventos.api.domain.coupon;


import com.eventos.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "coupons")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private Integer discount;
    private Date valid;

    @ManyToOne //tipo de relação entre as tabelas/classes Event e Coupon
    @JoinColumn (name = "events_id") //nome da coluna que vai ser criada na tabela de coupons para referenciar a tabela de eventos
    private Event event;


}
