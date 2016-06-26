package util;

import org.junit.Test;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;
import ua.shalimov.institutemanager.util.Parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParserTest {

    @Test
    public void testGetGroupList() {
        Parser parser = new Parser();
        ResultSet resultSet = mock(ResultSet.class);
        List<Group> groupList = null;
        try {
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getString("title"))
                    .thenReturn("Football")
                    .thenReturn("Math")
                    .thenReturn("Basketball");
            when(resultSet.getInt("group_id")).thenReturn(1).thenReturn(2).thenReturn(3);
            groupList = parser.getGroupList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(groupList.size(), is(3));
        assertThat(groupList.get(0).getTitle(), is("Football"));
        assertThat(groupList.get(1).getTitle(), is("Math"));
        assertThat(groupList.get(2).getTitle(), is("Basketball"));
    }

    @Test
    public void testGetStudentList() {
        Parser parser = new Parser();
        ResultSet resultSet = mock(ResultSet.class);
        List<Student> studentList = null;
        try {
            when(resultSet.next())
                    .thenReturn(true)
                    .thenReturn(true)
                    .thenReturn(false);
            when(resultSet.getString("first_name"))
                    .thenReturn("Ivan")
                    .thenReturn("Dima");
            when(resultSet.getString("last_name"))
                    .thenReturn("Ivanov")
                    .thenReturn("Shalimov");
            when(resultSet.getInt("student_id"))
                    .thenReturn(1)
                    .thenReturn(2);
            studentList = parser.getStudentList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(studentList.size(), is(2));
        assertThat(studentList.get(0).getFirstName(), is("Ivan"));
        assertThat(studentList.get(0).getLastName(), is("Ivanov"));
        assertThat(studentList.get(1).getFirstName(), is("Dima"));
        assertThat(studentList.get(1).getLastName(), is("Shalimov"));
    }
}

