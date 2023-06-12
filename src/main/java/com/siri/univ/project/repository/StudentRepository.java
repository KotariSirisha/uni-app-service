package com.siri.univ.project.repository;

import com.siri.univ.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    List<Student> findByUniversityName(String univName);
    Optional<Student> findByEmail(String email);

    @Modifying
    @Query("delete from Student s where s.email=:email")
    void deleteStudent(@Param("email") String email);



}
