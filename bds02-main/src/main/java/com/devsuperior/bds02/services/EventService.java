package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public EventDTO update(Long id, EventDTO dto){
        Event event = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
        copyEntityToDto(event, dto);
        event = repository.save(event);
        return new EventDTO(event);
    }

    private void copyEntityToDto(Event entity, EventDTO dto){
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        City city = new City();
        city.setId(dto.getCityId());
        entity.setCity(city);
    }
}
