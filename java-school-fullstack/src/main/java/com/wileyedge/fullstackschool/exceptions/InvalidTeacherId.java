package com.wileyedge.fullstackschool.exceptions;

public class InvalidTeacherId extends RuntimeException{
    @Override
    public String getMessage() {
        return "Invalid teacher id given.";
    }
}
