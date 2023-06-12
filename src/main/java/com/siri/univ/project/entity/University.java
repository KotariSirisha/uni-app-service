package com.siri.univ.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "University")
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class University {

    @Id
    @Column(name = "UNIV_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UNIV_NAME")
    private String name;

    @Column(name = "UNIV_LOCATION")
    private String location;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="university")
    private Set<Student> students = new HashSet<Student>();


}
