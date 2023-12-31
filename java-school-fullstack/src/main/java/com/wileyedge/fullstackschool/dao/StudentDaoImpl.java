package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.StudentMapper;
import com.wileyedge.fullstackschool.exceptions.InvalidStudentId;
import com.wileyedge.fullstackschool.model.Student;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {


    private final StudentMapper studentMapper = new StudentMapper();

    private final RowMapper<Student> rowMapperLambdaEx = (rs, numRowsAffected) ->
            new Student(rs.getInt(1), rs.getString(2), rs.getString(3));

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE
        String query = "INSERT INTO student(FName, LName) VALUES (?, ?)";
        jdbcTemplate.update(query, student.getStudentFirstName(), student.getStudentLastName());

        // Use the H2-specific function to get the last inserted ID
        String queryForId = "SELECT IDENTITY()";
        int generatedId = jdbcTemplate.queryForObject(queryForId, Integer.class);
        student.setStudentId(generatedId);
        return student;
        //YOUR CODE ENDS HERE

    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
        String query = "SELECT * FROM student;";
        List<Student> allStudents = jdbcTemplate.query(query, rowMapperLambdaEx);
        return allStudents;
        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE

        String query = "SELECT * FROM student WHERE sid=?";
        Student fetchedStudent = null;
        try {
            fetchedStudent = jdbcTemplate.queryForObject(query, rowMapperLambdaEx, id);
        } catch (DataAccessException dae) {
            // Handle the exception or throw a custom exception as needed
            // For example, you can throw a custom exception like InvalidStudentId
            throw new InvalidStudentId();
        }
        return fetchedStudent;

        //YOUR CODE ENDS HERE
    }

    @Override
    public Student updateStudent(int id, Student student) {
        //YOUR CODE STARTS HERE
        String query = "UPDATE student SET FName = ?, LName = ? WHERE sid = ?";
        jdbcTemplate.update(query, student.getStudentFirstName(), student.getStudentLastName(), student.getStudentId());
        //YOUR CODE ENDS HERE
        return student;
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE
        String query = "DELETE FROM student WHERE sid = ?";
        jdbcTemplate.update(query, id);
        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        String query = "INSERT INTO course_student(student_id, course_id) VALUES (?, ?)";
        jdbcTemplate.update(query, studentId, courseId);
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
        String query = "DELETE FROM course_student WHERE student_id = ? AND course_id = ?";
        jdbcTemplate.update(query, studentId, courseId);
        //YOUR CODE ENDS HERE
    }
}
