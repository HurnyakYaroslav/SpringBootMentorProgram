package com.mentorship.demo.controller;

import com.mentorship.demo.dto.AbiturientDto;
import com.mentorship.demo.dto.DetailedAbiturientDto;
import com.mentorship.demo.service.AbiturientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/abiturients")
public class AbiturientController {

    private final AbiturientService abiturientService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailedAbiturientDto getById(@PathVariable int id) {
        return abiturientService.getById(id);
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public DetailedAbiturientDto createAbiturient(@RequestBody AbiturientDto abiturientDto) {
        log.error(abiturientDto.toString());
        return abiturientService.save(abiturientDto);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
  public DetailedAbiturientDto updateAbiturient(@RequestBody AbiturientDto abiturientDto, @RequestParam int id) {
      return abiturientService.update(abiturientDto, id);
  }


    @DeleteMapping("/{id}")
    public void deleteAbiturient(@PathVariable int id) {
        abiturientService.delete(id);
    }

    @GetMapping("/")
    public List<DetailedAbiturientDto> getAll() {
        return abiturientService.getAll();
    }
}
