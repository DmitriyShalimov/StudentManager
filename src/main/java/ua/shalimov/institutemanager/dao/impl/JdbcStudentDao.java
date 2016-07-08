package ua.shalimov.institutemanager.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.shalimov.institutemanager.dao.StudentDao;
import ua.shalimov.institutemanager.dao.mapper.StudentRowMapper;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.util.List;

@Repository
public class JdbcStudentDao implements StudentDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStudentDao.class);
    private final StudentRowMapper STUDENT_ROW_MAPPER = new StudentRowMapper();
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Value("${getAllStudentsSQL}")
    private String getAllStudentsSQL;
    @Value("${deleteStudentSQL}")
    private String deleteStudentSQL;
    @Value("${addNewStudentSQL}")
    private String addNewStudentSQL;
    @Value("${editStudentSQL}")
    private String editStudentSQL;
    @Value("${getAllStudentsFromGroupSQL}")
    private String getAllStudentsFromGroupSQL;
    @Value("${getByLastNameSQL}")
    private String getByLastNameSQL;
    @Value("${addNewStudentGroupRelationsSQL}")
    private String addNewStudentGroupRelationsSQL;
    @Value("${getByIdSQL}")
    private String getByIdSQL;
    @Value("${deleteStudentGroupByStudentIdSQL}")
    private String deleteStudentGroupByStudentIdSQL;

    @Override
    public List<Student> getAll() {
        LOGGER.info("Start obtaining from database all students");
        long startTime = System.currentTimeMillis();
        List<Student> students = jdbcTemplate.query(getAllStudentsSQL, STUDENT_ROW_MAPPER);
        LOGGER.info("Students were obtained. Their amount = {}. It took {} ms", students.size(), System.currentTimeMillis() - startTime);
        return students;
    }

    @Override
    public void delete(int studentId) {
        LOGGER.info("Delete student with id={} from database", studentId);
        long startTime = System.currentTimeMillis();
        namedParameterJdbcTemplate.update(deleteStudentSQL, new MapSqlParameterSource("studentId", studentId));
        LOGGER.info("Student with id={} was deleted. It took {} ms", studentId, System.currentTimeMillis() - startTime);
    }

    @Override
    public List<Student> getAllFromGroup(int id) {
        LOGGER.info("Start obtaining from database all students from group with id={}", id);
        long startTime = System.currentTimeMillis();
        List<Student> students = namedParameterJdbcTemplate.query(getAllStudentsFromGroupSQL, new MapSqlParameterSource("groupId", id), STUDENT_ROW_MAPPER);
        LOGGER.info("Students were obtained. Their amount = {}. It took {} ms", students.size(), System.currentTimeMillis() - startTime);
        return students;
    }

    @Override
    public List<Student> getByName(String name) {
        LOGGER.info("Start obtaining from database all students with lastName={}", name);
        long startTime = System.currentTimeMillis();
        List<Student> students = namedParameterJdbcTemplate.query(getByLastNameSQL, new MapSqlParameterSource("lastName", name), STUDENT_ROW_MAPPER);
        LOGGER.info("Students were obtained. Their amount = {}. It took {} ms", students.size(), System.currentTimeMillis() - startTime);
        return students;
    }

    @Override
    public Student getById(int studentId) {
        LOGGER.info("Start obtaining from database student with id={}", studentId);
        long startTime = System.currentTimeMillis();
        Student student = namedParameterJdbcTemplate.queryForObject(getByIdSQL, new MapSqlParameterSource("studentId", studentId), STUDENT_ROW_MAPPER);
        LOGGER.info("Student was obtained.  It took {} ms", System.currentTimeMillis() - startTime);
        return student;
    }

    @Override
    public void add(Student student) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("student").usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(student);
        Number newId = simpleJdbcInsert.executeAndReturnKey(params);
        if (student.getGroups() != null) {
            MapSqlParameterSource groupParams = new MapSqlParameterSource();
            groupParams.addValue("studentId", newId.intValue());
            for (Group group : student.getGroups()) {
                groupParams.addValue("groupId", group.getId());
                namedParameterJdbcTemplate.update(addNewStudentGroupRelationsSQL, groupParams);
            }
        }
    }

    @Override
    public void edit(Student student) {
        LOGGER.info("Edit student with id={} from database", student.getId());
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("studentId", student.getId());
        params.addValue("firstName", student.getFirstName());
        params.addValue("lastName", student.getLastName());
        namedParameterJdbcTemplate.update(editStudentSQL, params);
        MapSqlParameterSource groupParams = new MapSqlParameterSource();
        groupParams.addValue("studentId", student.getId());
        namedParameterJdbcTemplate.update(deleteStudentGroupByStudentIdSQL, groupParams);
        if (student.getGroups() != null) {
            for (Group group : student.getGroups()) {
                groupParams.addValue("groupId", group.getId());
                namedParameterJdbcTemplate.update(addNewStudentGroupRelationsSQL, groupParams);
            }
        }
        LOGGER.info("Student with id={} was update. It took {} ms", student.getId(), System.currentTimeMillis() - startTime);
    }
}
