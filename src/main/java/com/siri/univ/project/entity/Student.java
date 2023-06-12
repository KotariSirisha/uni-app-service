package com.siri.univ.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Student")
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "STU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "UNIV_ID", referencedColumnName = "UNIV_ID", nullable = false)
    private University university;

    @Column(name = "STU_FIRST_NAME")
    private String firstName;

    @Column(name = "STU_LAST_NAME")
    private String lastName;

    @Column(name = "STU_EMAIL")
    private String email;

    @Column(name = "STU_AGE")
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
