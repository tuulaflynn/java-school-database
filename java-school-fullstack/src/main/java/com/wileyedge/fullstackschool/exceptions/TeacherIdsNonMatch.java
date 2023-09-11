package com.wileyedge.fullstackschool.exceptions;

public class TeacherIdsNonMatch extends RuntimeException{
        @Override
        public String getMessage() {
            return "The teacher id given doesn't match the url pathway. Teacher not updated.";
        }

}
