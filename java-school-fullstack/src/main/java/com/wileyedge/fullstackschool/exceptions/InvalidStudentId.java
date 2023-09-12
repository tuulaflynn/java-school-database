package com.wileyedge.fullstackschool.exceptions;

public class InvalidStudentId extends RuntimeException{
    @Override
    public String getMessage() {
        return "Student not found. Invalid student id given.";
    }
}