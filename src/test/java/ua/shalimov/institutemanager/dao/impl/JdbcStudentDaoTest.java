package ua.shalimov.institutemanager.dao.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.shalimov.institutemanager.entity.Student;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/root-context.xml"})
public class JdbcStudentDaoTest extends TestCase {

    @Autowired
    private JdbcStudentDao jdbcStudentDao;

    @Test
    public void testGetAll() throws Exception {
        //when
        List<Student> allStudent = jdbcStudentDao.getAll();
        //then
        assertTrue(allStudent.size() > 0);
        Student student = allStudent.get(0);
        assertNotNull(student.getId());
        assertNotNull(student.getFirstName());
        assertNotNull(student.getLastName());
        assertTrue(student.getGroups().size() > 0);
    }
}