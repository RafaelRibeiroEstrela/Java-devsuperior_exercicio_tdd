package com.devsuperior.bds02.resources;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityResource {

    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageRequest));
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
