package ua.shalimov.institutemanager.service.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.shalimov.institutemanager.dao.impl.JdbcStudentDao;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStudentDao.class);

    public Student jsonToStudent(String json) {
        JSONParser parser = new JSONParser();
        Student student = new Student();
        try {
            Object object = parser.parse(json);
            JSONObject jsonObject = (JSONObject) object;
            String firstName = jsonObject.get("firstName").toString();
            String lastName = jsonObject.get("lastName").toString();
            String groupIds = jsonObject.get("groupIds").toString();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            if (!"0".equals(groupIds)) {
                String[] groupList = groupIds.split(",");
                List<Group> groups = new ArrayList<>();
                for (String groupId : groupList) {
                    int i = Integer.parseInt(groupId);
                    groups.add(new Group(i));
                }
                student.setGroups(groups);
            }
        } catch (ParseException e) {
            LOGGER.error("Method jsonToStudent: ", e);
            throw new RuntimeException("Error occurred while converting json to student", e);
        }
        return student;
    }

    public Group jsonToGroup(String json) {
        JSONParser parser = new JSONParser();
        Group group = new Group();
        try {
            Object object = parser.parse(json);
            JSONObject jsonObject = (JSONObject) object;
            String title = jsonObject.get("title").toString();
            group.setTitle(title);
        } catch (ParseException e) {
            LOGGER.error("Method jsonToGroup ", e);
            throw new RuntimeException("Error occurred while converting json to group", e);
        }
        return group;
    }
}
