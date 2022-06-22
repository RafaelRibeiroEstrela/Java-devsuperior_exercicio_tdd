package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exceptions.ApiException;
import com.devsuperior.bds02.repositories.CityRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest).stream()
                .map(city -> new CityDTO(city)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO dto){
        City entity = new City();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        try{
            repository.findById(id);
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }catch (DataIntegrityViolationException e){
            throw new ApiException();
        }catch (IllegalArgumentException e){
            throw new ApiException();
        }catch (ConstraintViolationException e){
            throw new ApiException();
        }

    }

    private void copyDtoToEntity(City entity, CityDTO dto){
        entity.setName(dto.getName());
    }


}
