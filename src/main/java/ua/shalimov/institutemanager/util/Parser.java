package ua.shalimov.institutemanager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Logger logger = LoggerFactory.getLogger(Parser.class);

    public List<Student> getStudentList(ResultSet resultSet, Connection connection) {
        logger.info("start method getStudentList");
        ArrayList<Student> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("student_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("get_all_students_group"));
                    preparedStatement.setInt(1, student.getId());
                    preparedStatement.execute();
                    ResultSet tempResultSet = preparedStatement.getResultSet();
                    List<Group> listGroup = new ArrayList<>();
                    while (tempResultSet.next()) {
                        String title = tempResultSet.getString("title");
                        int id = tempResultSet.getInt("group_id");
                        Group group = new Group(id, title);
                        listGroup.add(group);
                    }
                    student.setListGroup(listGroup);

                } catch (SQLException e) {
                    logger.error("method getStudentList:", e);
                }
                list.add(student);
            }
        } catch (SQLException e) {
            logger.error("method getStudentList:", e);
        }
        return list;
    }

    public List<Group> getGroupList(ResultSet resultSet) {
        logger.info("start method getGroupList");
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
