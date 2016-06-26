package ua.shalimov.institutemanager.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;
import ua.shalimov.institutemanager.util.Parser;
import ua.shalimov.institutemanager.util.PropertiesParser;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    private static final Logger logger = LoggerFactory.getLogger(DataAccess.class);
    private Parser parser = new Parser();

    @Autowired
    private DataSource dataSource;

    public List<Student> getAllStudents() {
        logger.info("method getAllStudents");
        List<Student> studentList;
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(PropertiesParser.getSQL("get_all_student"));
                ResultSet resultSet = statement.getResultSet();
                studentList = parser.getStudentList(resultSet);
                for (Student student : studentList) {
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
                        logger.error("get all students group:", e);
                    }
                }
                return studentList;
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method getAllStudents:", e);
        }
        return null;
    }

    public List<Group> getAllGroup() {
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(PropertiesParser.getSQL("show_all_group"));
                ResultSet resultSet = statement.getResultSet();
                return parser.getGroupList(resultSet);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method getAllGroup:", e);
        }
        return null;
    }

    public void addNewStudent(String firstName, String lastName) {
        logger.info("add student. firstName={} lastName={}", firstName, lastName);
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("add_new_student"));
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("SQL exception while adding new student. [firstName={}], [lastName={}]", firstName, lastName, e);
        }
    }

    public void addNewGroup(String title) {
        Connection connection;
        logger.info("add group. title={}", title);
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("add_new_group"));
                preparedStatement.setString(1, title);
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method addNewGroup:", e);
        }
    }

    public void deleteStudent(int studentId) {
        Connection connection;
        logger.info("delete student. id={}", studentId);
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("delete_student_group_by_student_id"));
                preparedStatement.setInt(1, studentId);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("delete_student"));
                preparedStatement.setString(1, String.valueOf(studentId));
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method deleteStudent:", e);
        }
    }

    public void deleteGroup(int group_id) {
        logger.info("delete group. id={}", group_id);
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("delete_student_group_by_group_id"));
                preparedStatement.setInt(1, group_id);
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("delete_group"));
                preparedStatement.setString(1, String.valueOf(group_id));
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void editStudent(Student student) {
        Connection connection;
        logger.info("Edit student. id={},firstName={} lastName={}", student.getId(), student.getFirstName(), student.getLastName());
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("edit_student"));
                preparedStatement.setString(1, student.getFirstName());
                preparedStatement.setString(2, student.getLastName());
                preparedStatement.setInt(3, student.getId());
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method editStudent:", e);
        }
    }

    public void editGroup(Group group) {
        logger.info("Edit group. id={},title={}", group.getId(), group.getTitle());
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("edit_group"));
                preparedStatement.setString(1, group.getTitle());
                preparedStatement.setInt(2, group.getId());
                preparedStatement.executeUpdate();
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method editGroup:", e);
        }
    }

    public void changeStudentsGroups(String[] groupTitle, int studentId) {
        logger.info("change student groups id={}", studentId);
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("delete_student_group_by_student_id"));
                preparedStatement.setInt(1, studentId);
                preparedStatement.executeUpdate();
                if (groupTitle != null) {
                    for (String title : groupTitle) {
                        preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("add_new_student_group_relations"));
                        preparedStatement.setInt(1, studentId);
                        preparedStatement.setInt(2, Integer.parseInt(title));
                        preparedStatement.executeUpdate();
                    }
                }
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method changeStudentsGroups:", e);
        }
    }
}
