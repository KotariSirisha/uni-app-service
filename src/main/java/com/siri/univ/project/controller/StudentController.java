package com.siri.univ.project.controller;

import com.siri.univ.project.entity.Student;
import com.siri.univ.project.model.StudentDto;
import com.siri.univ.project.model.UniversityDto;
import com.siri.univ.project.service.StudentService;
import com.siri.univ.project.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public List<StudentDto> students() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{univName}")
    public List<StudentDto> studentsByUnivName(@PathVariable("univName") String univName) {
        return studentService.getAllStudentsByUnivName(univName);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentDto) {
        try {
            StudentDto studentDto1 = studentService.createStudent(studentDto);
            return new ResponseEntity<>(studentDto1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while creating student", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/students/update/{email}")
    public ResponseEntity<StudentDto> update(@PathVariable("email") String email, @RequestBody StudentDto studentDto) {
        try {
            Student student = studentService.updateStudent(email,studentDto);
            return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while creating student", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/students/delete/{email}")
    public void deleteStudent(@PathVariable("email") String email) {
        try {
            studentService.deleteStudent(email);
            logger.info("student deleted with the email=="+ email);
        } catch (Exception e) {
            logger.error("Exception occurred while activating account", e);

        }
    }
}
