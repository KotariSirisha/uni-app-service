package com.siri.univ.project.service;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String msg) {
        super(msg);
    }
}
