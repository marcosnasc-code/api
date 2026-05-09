package com.eventos.api.service;


import com.amazonaws.services.s3.AmazonS3;
import com.eventos.api.domain.event.Event;
import com.eventos.api.domain.event.EventRequestDTO;
import com.eventos.api.domain.event.EventResponseDTO;
import com.eventos.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private EventRepository eventRepository;


    public Event createEvent(EventRequestDTO data){

        String imgUrl = null;
        if(data.image() != null){
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(data.remote());

        eventRepository.save(newEvent);

        return newEvent;
    }

    private String uploadImg(MultipartFile image){
        String imgName = UUID.randomUUID() + "-" + image.getOriginalFilename();

        try{
            File file = this.converMultipartToFile(image);
            s3Client.putObject(bucketName, imgName, file);
            file.delete();
            return s3Client.getUrl(bucketName, imgName).toString();
        }catch (Exception e){
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }

    private File converMultipartToFile(MultipartFile image) throws Exception{
        File convFile = new File(Objects.requireNonNull(image.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(image.getBytes());
        fos.close();
        return convFile;
    }

    public List<EventResponseDTO> getEvents(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = this.eventRepository.getUpcomingEvents(new Date(), pageable);
        return eventsPage.map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                "event.getCity()",
                "event.getUf()",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl()
        )).getContent();
    }

}
