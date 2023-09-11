package com.wileyedge.fullstackschool.controller;

import com.wileyedge.fullstackschool.exceptions.InvalidTeacherId;
import com.wileyedge.fullstackschool.model.Teacher;
import com.wileyedge.fullstackschool.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        //YOUR CODE STARTS HERE
        return new ResponseEntity<List<Teacher>>(teacherServiceImpl.getAllTeachers(), HttpStatus.OK);
        //YOUR CODE ENDS HERE
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        return new ResponseEntity<Teacher>(teacherServiceImpl.getTeacherById(id), HttpStatus.OK);
        //YOUR CODE ENDS HERE
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        //YOUR CODE STARTS HERE
        return new ResponseEntity<Teacher>(teacherServiceImpl.addNewTeacher(teacher), HttpStatus.OK);
        //YOUR CODE ENDS HERE
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        //YOUR CODE STARTS HERE
        return teacherServiceImpl.updateTeacherData(id, teacher);
        //YOUR CODE ENDS HERE
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        //YOUR CODE STARTS HERE
        teacherServiceImpl.deleteTeacherById(id);
        //YOUR CODE ENDS HERE
    }





}
