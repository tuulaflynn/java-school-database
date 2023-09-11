package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.TeacherMapper;
import com.wileyedge.fullstackschool.exceptions.InvalidTeacherId;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    // Method one of for using the functional interface RowMapper (utilises the TeacherMapper Class)
    private final TeacherMapper teacherMapper = new TeacherMapper();
    // Method two (utilises lambdas)
    private final RowMapper<Teacher> rowMapperLambdaEx = (rs, numRowsAffected) ->
            new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

    //@Autowired        This can be commented out as there is a single constructor Spring automatically injects the dependencies for you.
    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
        String query1 = "INSERT INTO teacher(tFName, tLName, dept) VALUES (?,?,?)";
        int noRowsAffected = jdbcTemplate.update(query1, teacher.getTeacherFName(), teacher.getTeacherLName(), teacher.getDept());

        // Retrieve the last generated identity value
        // String query2 = "SELECT LAST_INSERT_ID();";      syntax for MySql
        String query2 = "SELECT SCOPE_IDENTITY()";         // syntax for h2

        int newTeacherId = jdbcTemplate.queryForObject(query2, (rs, rowsAffected) -> rs.getInt(1));
        teacher.setTeacherId(newTeacherId);
        return teacher;
        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE
        String query = "SELECT * FROM teacher;";
        List<Teacher> allTeachers = jdbcTemplate.query(query, rowMapperLambdaEx);
        return allTeachers;
        //YOUR CODE ENDS HERE
    }

    @Override
    public Teacher findTeacherById(int id) {
        //YOUR CODE STARTS HERE
        String query = "SELECT * FROM teacher WHERE tid=?";
        Teacher fetchedTeacher = null;
        try {
            fetchedTeacher = jdbcTemplate.queryForObject(query, rowMapperLambdaEx, id);
        } catch (DataAccessException dae) {
            // As spring using JdbcTemplate throws mySQLExceptions (which are checked) as DataAccessExceptions (which are unchecked)
            throw new InvalidTeacherId();
        }
        return fetchedTeacher;

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateTeacher(Teacher t) {
        //YOUR CODE STARTS HERE
        String query = "UPDATE teacher SET tFName=?, tLName=?, dept=? WHERE tid=?";
        jdbcTemplate.update(query, t.getTeacherFName(), t.getTeacherLName(), t.getDept(), t.getTeacherId());
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteTeacher(int id) {
        //YOUR CODE STARTS HERE
        String query = "DELETE FROM teacher WHERE tid=?";
        jdbcTemplate.update(query, id);
        //YOUR CODE ENDS HERE
    }
}
