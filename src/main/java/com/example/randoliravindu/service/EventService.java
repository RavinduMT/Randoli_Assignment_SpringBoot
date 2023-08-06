package com.example.randoliravindu.service;

import com.example.randoliravindu.model.Event;

import com.example.randoliravindu.model.Payload;
import com.example.randoliravindu.repositroy.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(UUID id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public boolean existsEventById(UUID id) {
        return eventRepository.existsById(id);
    }

    public void deleteEventById(UUID id) {
        eventRepository.deleteById(id);
    }

    public void saveBatchData(Payload payload) {
        List<Payload.Record> records = payload.getRecords();
        for (Payload.Record record : records) {
            List<Payload.Event> events = record.getEvent();
            for (Payload.Event event : events) {
                // Create new EventEntity and set the fields from the payload
                Event eventEntity = new Event();
                eventEntity.setTransId(record.getTransId());
                eventEntity.setTransTms(record.getTransTms());
                eventEntity.setRcNum(record.getRcNum());
                eventEntity.setClientId(record.getClientId());
                eventEntity.setEventCnt(event.getEventCnt());
                eventEntity.setLocationCd(event.getLocationCd());
                eventEntity.setLocationId1(event.getLocationId1());
                eventEntity.setLocationId2(event.getLocationId2());
                eventEntity.setAddrNbr(event.getAddrNbr());

                // Save the event to the database
                eventRepository.save(eventEntity);
            }
        }
    }
}