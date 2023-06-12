package com.siri.univ.project.controller;

import com.siri.univ.project.model.UniversityDto;
import com.siri.univ.project.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UniversityController {

    private static final Logger logger = LoggerFactory.getLogger(UniversityController.class);

    @Autowired
    private UniversityService universityService;

    @GetMapping("/universities")
    public List<UniversityDto> universities() {
        return universityService.getAllUniversities();
    }

    @PostMapping("/universities")
    public ResponseEntity<UniversityDto> create(@RequestBody UniversityDto universityDto) {
        try {
            UniversityDto universityDto1 = universityService.createUniversity(universityDto);
            return new ResponseEntity<>(universityDto1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while creating university", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }












}
