package com.wileyedge.fullstackschool.exceptions;

public class StudentIdsNonMatch extends RuntimeException{
    @Override
    public String getMessage() {
        return "The student id given doesn't match the url pathway. Student not updated.";
    }

}
