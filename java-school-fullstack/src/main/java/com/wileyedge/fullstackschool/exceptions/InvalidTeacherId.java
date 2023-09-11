package com.wileyedge.fullstackschool.exceptions;

public class InvalidTeacherId extends RuntimeException{
    @Override
    public String getMessage() {
        return "Teacher not found. Invalid teacher id given.";
    }
}
