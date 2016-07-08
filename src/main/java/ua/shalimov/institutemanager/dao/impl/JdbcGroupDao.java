package ua.shalimov.institutemanager.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.shalimov.institutemanager.dao.GroupDao;
import ua.shalimov.institutemanager.dao.mapper.GroupRowMapper;
import ua.shalimov.institutemanager.entity.Group;

import java.util.List;

@Repository
public class JdbcGroupDao implements GroupDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStudentDao.class);
    private final GroupRowMapper GROUP_ROW_MAPPER = new GroupRowMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Value("${getAllGroupsSQL}")
    private String getAllGroupsSQL;
    @Value("${deleteGroupSQL}")
    private String deleteGroupSQL;
    @Value("${addNewGroupSQL}")
    private String addNewGroupSQL;
    @Value("${editGroupSQL}")
    private String editGroupSQL;
    @Value("${getAllStudentGroupsSQL}")
    private String getAllStudentGroupsSQL;
    @Value("${getGroupByIdSQL}")
    private String getGroupByIdSQL;

    @Override
    public List<Group> getAll() {
        LOGGER.info("Start obtaining from database all groups");
        long startTime = System.currentTimeMillis();
        List<Group> groups = jdbcTemplate.query(getAllGroupsSQL, GROUP_ROW_MAPPER);
        LOGGER.info("Groups were obtained. Their amount = {}. It took {} ms", groups.size(), System.currentTimeMillis() - startTime);
        return groups;
    }

    @Override
    public void delete(int groupId) {
        LOGGER.info("Delete group with id={} from database", groupId);
        long startTime = System.currentTimeMillis();
        namedParameterJdbcTemplate.update(deleteGroupSQL, new MapSqlParameterSource("groupId", groupId));
        LOGGER.info("Group with id={} was deleted. It took {} ms", groupId, System.currentTimeMillis() - startTime);
    }

    @Override
    public List<Group> getAllStudentGroups(int id) {
        LOGGER.info("Start obtaining from database all groups in which the student with id={} is ", id);
        long startTime = System.currentTimeMillis();
        List<Group> groups = namedParameterJdbcTemplate.query(getAllStudentGroupsSQL, new MapSqlParameterSource("studentId", id), GROUP_ROW_MAPPER);
        LOGGER.info("Students were obtained. Their amount = {}. It took {} ms", groups.size(), System.currentTimeMillis() - startTime);
        return groups;
    }

    @Override
    public Group getById(int groupId) {
        LOGGER.info("Start obtaining from database group with id={}", groupId);
        long startTime = System.currentTimeMillis();
        Group group = namedParameterJdbcTemplate.queryForObject(getGroupByIdSQL, new MapSqlParameterSource("groupId", groupId), GROUP_ROW_MAPPER);
        LOGGER.info("Student was obtained.  It took {} ms", System.currentTimeMillis() - startTime);
        return group;
    }

    @Override
    public void edit(Group group) {
        LOGGER.info("Edit group with id={} from database", group.getId());
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("groupId", group.getId());
        params.addValue("title", group.getTitle());
        namedParameterJdbcTemplate.update(editGroupSQL, params);
        LOGGER.info("Group with id={} was update. It took {} ms", group.getId(), System.currentTimeMillis() - startTime);

    }

    @Override
    public void add(Group group) {
        LOGGER.info("Add new group with title={} in database", group.getTitle());
        long startTime = System.currentTimeMillis();
        namedParameterJdbcTemplate.update(addNewGroupSQL, new MapSqlParameterSource("title", group.getTitle()));
        LOGGER.info("New group  was added. It took {} ms", System.currentTimeMillis() - startTime);
    }
}



