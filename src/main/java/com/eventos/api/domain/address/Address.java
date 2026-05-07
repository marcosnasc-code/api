package com.eventos.api.domain.address;


import com.eventos.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "address")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {


    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "events_id")
    private Event event;
}
