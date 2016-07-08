package ua.shalimov.institutemanager.dao.mapper;

import org.junit.Test;
import org.mockito.Mockito;
import ua.shalimov.institutemanager.entity.Group;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GroupRowMapperTest {

    @Test
    public void testMapRow() throws Exception {
        //prepare
        GroupRowMapper groupRowMapper = new GroupRowMapper();
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("group_id")).thenReturn(1);
        when(resultSet.getString("title")).thenReturn("Title");
        //when
        Group group = groupRowMapper.mapRow(resultSet, 1);
        //then
        assertEquals(1, group.getId());
        assertEquals("Title", group.getTitle());
    }
}
