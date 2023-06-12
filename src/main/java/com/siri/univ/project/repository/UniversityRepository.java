package com.siri.univ.project.repository;

import com.siri.univ.project.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    University findByName(String name);
}
