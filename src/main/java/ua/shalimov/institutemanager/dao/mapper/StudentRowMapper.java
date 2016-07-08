package ua.shalimov.institutemanager.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.shalimov.institutemanager.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        return student;
    }
}
