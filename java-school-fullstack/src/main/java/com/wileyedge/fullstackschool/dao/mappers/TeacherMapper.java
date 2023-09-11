package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
        return new Teacher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        //YOUR CODE ENDS HERE
    }
}
