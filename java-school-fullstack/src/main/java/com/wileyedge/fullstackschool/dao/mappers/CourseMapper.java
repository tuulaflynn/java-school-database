package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Course;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
        Course course = new Course();
        course.setCourseId(rs.getInt(1));
        course.setCourseName(rs.getString(2));
        course.setCourseDesc(rs.getString(3));
        course.setTeacherId(rs.getInt(4));
        return course;
        //YOUR CODE ENDS HERE
    }
}
