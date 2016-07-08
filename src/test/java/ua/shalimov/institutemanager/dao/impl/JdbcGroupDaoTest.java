package ua.shalimov.institutemanager.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.shalimov.institutemanager.entity.Group;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/root-context.xml"})
public class JdbcGroupDaoTest {

    @Autowired
    private JdbcGroupDao jdbcGroupDao;

    @Test
    public void testGetAll() throws Exception {
        //when
        List<Group> allGroups = jdbcGroupDao.getAll();
        //then
        assertTrue(allGroups.size() > 0);
        Group group = allGroups.get(0);
        assertNotNull(group.getId());
        assertNotNull(group.getTitle());
    }
}
