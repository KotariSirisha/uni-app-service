package com.siri.univ.project.service;

import com.siri.univ.project.entity.University;
import com.siri.univ.project.model.UniversityDto;
import com.siri.univ.project.repository.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityService {

    private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);

    @Autowired
    private UniversityRepository universityRepository;

    public UniversityDto createUniversity(final UniversityDto universityDto){
        University university = new University();
        university.setName(universityDto.getName());
        university.setLocation(universityDto.getLocation());
        universityRepository.save(university);
        logger.info("University is created in DB with name => {} ", universityDto.getName());
        return universityDto;
    }

    public List<UniversityDto> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        List<UniversityDto> universitiDtos = new ArrayList<>();
        for(University university : universities){
            UniversityDto universityDto = new UniversityDto();
            universityDto.setName(university.getName());
            universityDto.setLocation(university.getLocation());
            universitiDtos.add(universityDto);
        }
        return universitiDtos;
    }
}
