package ua.shalimov.institutemanager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final Logger logger = LoggerFactory.getLogger(Parser.class);

    public List<Student> getStudentList(ResultSet resultSet) {
        logger.info("get student list");
        ArrayList<Student> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                list.add(student);
            }
        } catch (SQLException e) {
            logger.error("method getStudentList:", e);
        }
        return list;
    }

    public List<Group> getGroupList(ResultSet resultSet) {
        logger.info("get group list");
        ArrayList<Group> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("group_id"));
                group.setTitle(resultSet.getString("title"));
                list.add(group);
            }
        } catch (SQLException e) {
            logger.error("method getGroupList:", e);
        }
        return list;
    }
}
