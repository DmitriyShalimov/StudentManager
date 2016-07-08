package ua.shalimov.institutemanager.service;


import ua.shalimov.institutemanager.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAll();

    void delete(int id);

    Group getGroupById(int id);

    List<Group> getAllStudentGroups(int id);

    boolean validate(Group group);

    void add(Group group);

    void edit(Group group);
}
