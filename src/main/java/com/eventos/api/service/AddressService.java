package com.eventos.api.service;


import com.eventos.api.domain.address.Address;
import com.eventos.api.domain.event.Event;
import com.eventos.api.domain.event.EventRequestDTO;
import com.eventos.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(EventRequestDTO date, Event event){
        Address address = new Address();
        address.setCity(date.city());
        address.setUf(date.uf());
        address.setEvent(event);

        return addressRepository.save(address);

    }

}
