package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.CourseMapper;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;
    private final CourseMapper courseMapper = new CourseMapper();

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE
        String query1 = "INSERT INTO course(courseCode, courseDesc, teacherId) VALUES (?,?,?)";
        jdbcTemplate.update(query1, course.getCourseName(), course.getCourseDesc(), course.getTeacherId());
        String query2 = "SELECT SCOPE_IDENTITY();";
        course.setCourseId(jdbcTemplate.queryForObject(query2, (rs, numOfRows)->rs.getInt(1)));

        return course;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE
        String query = "SELECT * FROM course;";
        return jdbcTemplate.query(query, courseMapper);

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE
        String query = "SELECT * FROM course WHERE cid=" + id;
        return jdbcTemplate.queryForObject(query, courseMapper);
        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE
        String query = "UPDATE course SET courseCode=?, courseDesc=?, teacherId=?  WHERE cid=?";
        jdbcTemplate.update(query, course.getCourseName(), course.getCourseDesc(), course.getTeacherId(), course.getCourseId());
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE
        String query = "DELETE FROM course WHERE cid=" + id;
        jdbcTemplate.update(query);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE
        String query = "DELETE FROM course_student WHERE course_id=" + courseId;
        jdbcTemplate.update(query);
        //YOUR CODE ENDS HERE
    }
}
