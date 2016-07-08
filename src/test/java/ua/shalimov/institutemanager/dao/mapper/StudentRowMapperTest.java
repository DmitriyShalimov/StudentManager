package ua.shalimov.institutemanager.dao.mapper;

import junit.framework.TestCase;
import org.mockito.Mockito;
import ua.shalimov.institutemanager.entity.Student;

import java.sql.ResultSet;

import static org.mockito.Mockito.when;

public class StudentRowMapperTest extends TestCase {
    public void testMapRow() throws Exception {
        //prepare
        StudentRowMapper studentRowMapper = new StudentRowMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("student_id")).thenReturn(1);
        when(resultSet.getString("first_name")).thenReturn("Dima");
        when(resultSet.getString("last_name")).thenReturn("Shalimov");
        //when
        Student student = studentRowMapper.mapRow(resultSet, 1);
        //then
        assertEquals(1, student.getId());
        assertEquals("Dima", student.getFirstName());
        assertEquals("Shalimov", student.getLastName());
    }
}

