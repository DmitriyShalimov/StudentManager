package ua.shalimov.institutemanager.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.shalimov.institutemanager.entity.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("id"));
        group.setTitle(resultSet.getString("title"));
        return group;
    }
}
