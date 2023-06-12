package com.siri.univ.project.service;

import com.siri.univ.project.entity.Student;
import com.siri.univ.project.entity.University;
import com.siri.univ.project.exception.UniversityNotFoundException;
import com.siri.univ.project.model.StudentDto;
import com.siri.univ.project.repository.StudentRepository;
import com.siri.univ.project.repository.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {


    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public StudentDto createStudent(final StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setAge(studentDto.getAge());

        University university = universityRepository.findByName(studentDto.getUniversityName());
        if (university == null) {
            throw new UniversityNotFoundException("University not found with name => " + studentDto.getUniversityName());
        }
        student.setUniversity(university);
        studentRepository.save(student);
        logger.info("Student is created in DB with name => {} ", studentDto.getFirstName());
        return studentDto;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            studentDto.setFirstName(student.getFirstName());
            studentDto.setLastName(student.getLastName());
            studentDto.setAge(student.getAge());
            studentDto.setEmail(student.getEmail());
            studentDto.setUniversityName(student.getUniversity().getName());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    public List<StudentDto> getAllStudentsByUnivName(String univName) {
        List<Student> students = studentRepository.findByUniversityName(univName);
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            StudentDto studentDto = new StudentDto();
            studentDto.setFirstName(student.getFirstName());
            studentDto.setLastName(student.getLastName());
            studentDto.setAge(student.getAge());
            studentDto.setEmail(student.getEmail());
            studentDto.setUniversityName(student.getUniversity().getName());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }

    public Student updateStudent(String email, StudentDto studentDto) {
        Optional<Student> student1 = studentRepository.findByEmail(email);

        if (student1.isPresent()) {

            student1.get().setFirstName(studentDto.getFirstName());
            student1.get().setLastName(studentDto.getLastName());
            University university = student1.get().getUniversity();
            if (university.getName() != studentDto.getUniversityName()) {
                University newUniversity = universityRepository.findByName(studentDto.getUniversityName());
                if (newUniversity == null) {
                    throw new UniversityNotFoundException("University not found with name => " + studentDto.getUniversityName());
                }
                student1.get().setUniversity(newUniversity);
            }
            studentRepository.save(student1.get());

        } else {
            throw new StudentNotFoundException("student not found with email==>" + email);
        }
        return student1.get();
    }

    public void deleteStudent(String email) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            studentRepository.deleteStudent(email);
            logger.info("Student deleted");

        } else {
            throw new StudentNotFoundException("student  not found with email==>" + email);
        }


    }
}


