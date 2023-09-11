package com.wileyedge.fullstackschool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // One way of handling the exception is this method could be (slightly altered) and placed within the controller class.
    @ExceptionHandler({InvalidTeacherId.class})
    // How can I have it so the error messages shown is dependent on which error occurs? What difference is {InvalidTeacherId.class, TeacherIdsNonMatch.class}??
    public ResponseEntity<Object> handleInvalidTeacherId(InvalidTeacherId invalidTeacherId) {
        return new ResponseEntity<>(invalidTeacherId.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TeacherIdsNonMatch.class)
    public ResponseEntity<Object> handleTeacherIdsNonMatch(TeacherIdsNonMatch teacherIdsNonMatch) {
        return new ResponseEntity<>(teacherIdsNonMatch.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
