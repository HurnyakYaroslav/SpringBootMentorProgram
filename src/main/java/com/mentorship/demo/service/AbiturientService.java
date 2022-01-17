package com.mentorship.demo.service;

import com.mentorship.demo.dao.AbiturientDao;
import com.mentorship.demo.dto.AbiturientDto;
import com.mentorship.demo.dto.DetailedAbiturientDto;
import com.mentorship.demo.model.Abiturient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AbiturientService {

    private final AbiturientDao abiturientDao;
    private final ModelMapper mapper;

    public DetailedAbiturientDto save(AbiturientDto abiturientDto) {
        log.info("Dto from service " + abiturientDto);
        Abiturient abiturient = mapper.map(abiturientDto, Abiturient.class);
        log.info("Saving from service " + abiturient);

        return mapper.map(abiturientDao.save(abiturient), DetailedAbiturientDto.class);
    }

    public DetailedAbiturientDto update(AbiturientDto abiturientDto, int id)
    {
        Abiturient abiturient = mapper.map(abiturientDto, Abiturient.class);
        abiturient.setId(id);
        return mapper.map(abiturientDao.update(abiturient), DetailedAbiturientDto.class);
    }

    public void delete(int id) {
        Abiturient abiturient = abiturientDao.getById(id);
        if (abiturient == null) {
            throw new EntityNotFoundException("AbiturientExceptionMessage" + id);
        }
        abiturientDao.delete(abiturient);
    }

    public DetailedAbiturientDto getById(int id) {
        Abiturient abiturient = abiturientDao.getById(id);
        if (abiturient == null) {
            throw new EntityNotFoundException("AbiturientExceptionMessage: id = " + id);
        }
        return mapper.map(abiturient, DetailedAbiturientDto.class);
    }

    public List<DetailedAbiturientDto> getAll() {
        return new ArrayList<>(abiturientDao
                .getAll()).stream()
                .map(abiturient -> mapper.map(abiturient, DetailedAbiturientDto.class))
                .collect(Collectors.toList());
    }

}
