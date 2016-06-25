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
import java.util.List;

public class DataAccess {

    private Logger logger = LoggerFactory.getLogger(DataAccess.class);
    private Parser parser = new Parser();

    @Autowired
    private DataSource dataSource;

    public List<Student> getAllStudents() {
        logger.info("start method getAllStudents");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                Statement statement = connection.createStatement();
                statement.execute(PropertiesParser.getSQL("get_all_student"));
                ResultSet resultSet = statement.getResultSet();
                return parser.getStudentList(resultSet, connection);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method getAllStudents:", e);
        }
        return null;
    }

    public List<Student> getAllStudentsFromGroup(String title) {
        logger.info("start method getAllStudentsFromGroup");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("get_all_students_from_group"));
                preparedStatement.setString(1, title);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                return parser.getStudentList(resultSet, connection);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method getAllStudentsFromGroup:", e);
        }
        return null;
    }

    public List<Group> getAllGroup() {
        logger.info("start method getAllGroup");
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
        logger.info("start method addNewStudent");
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
        logger.info("start method addNewGroup");
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

    public List<Student> findById(int id) {
        logger.info("start method findById");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("find_by_id"));
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                return parser.getStudentList(resultSet, connection);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method findById:", e);
        }
        return null;
    }

    public List<Group> findGroupById(int id) {
        logger.info("findGroupById");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("find_group_by_id"));
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                return parser.getGroupList(resultSet);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method findGroupById:", e);
        }
        return null;
    }

    public List<Student> findByNameLastName(String firstName, String lastName) {
        logger.info("start method findByNameLastName");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("find_by_name_last_name"));
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                return parser.getStudentList(resultSet, connection);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method findByNameLastName:", e);
        }
        return null;
    }

    public List<Student> findByName(String firstName) {
        logger.info("start method findByName");
        Connection connection;

        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("find_by_name"));
                preparedStatement.setString(1, firstName);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                return parser.getStudentList(resultSet, connection);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method findByNameLastName:", e);
        }
        return null;
    }

    public List<Student> findByLastName(String lastName) {
        logger.info("start method findByName");
        Connection connection;
        try {
            connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(PropertiesParser.getSQL("find_by_last_name"));
                preparedStatement.setString(1, lastName);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                return parser.getStudentList(resultSet, connection);
            } else {
                logger.error("Failed to connect");
            }
        } catch (SQLException e) {
            logger.error("method findByLastName:", e);
        }
        return null;
    }

    public void deleteStudent(int studentId) {
        Connection connection;
        logger.info("start method deleteStudent");
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
        logger.info("start method deleteGroup");
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
        logger.info("start method editStudent");
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
        logger.info("start method editGroup");
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
        logger.info("start changeStudentsGroups");
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
