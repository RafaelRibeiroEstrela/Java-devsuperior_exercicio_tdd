package com.devsuperior.bds02.resources;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventResource {

    @Autowired
    private EventService service;

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
    }
}
