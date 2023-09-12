package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.TeacherDao;
import com.wileyedge.fullstackschool.exceptions.InvalidTeacherId;
import com.wileyedge.fullstackschool.exceptions.TeacherIdsNonMatch;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }


    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE

        return teacherDao.findTeacherById(id);

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        if (teacher.getTeacherFName().equals("")) {
            teacher.setTeacherFName("First Name blank, teacher NOT added");
            if (teacher.getTeacherLName().equals("") ) {
                teacher.setTeacherLName("Last Name blank, teacher NOT added");
            }
            return teacher;
        }
        if (teacher.getTeacherLName().equals("") ) {
            teacher.setTeacherLName("Last Name blank, teacher NOT added");
            if (teacher.getTeacherFName().equals("")) {
                teacher.setTeacherFName("First Name blank, teacher NOT added");
            }
            return teacher;
        }

        return teacherDao.createNewTeacher(teacher);

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE
        // Not sure how helpful this is - the user can enter an incorrect id in the json body and no error will show. The database acts correctly and is not changed.
        if (id == teacher.getTeacherId()) {     // This checks whether the id given in the json body equals the id given in the url
            teacherDao.updateTeacher(teacher);
            return teacher;
        }
        throw new TeacherIdsNonMatch();
        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE
        teacherDao.deleteTeacher(id);
        //YOUR CODE ENDS HERE
    }
}
