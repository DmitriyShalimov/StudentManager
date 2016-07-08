package ua.shalimov.institutemanager.dao;

import ua.shalimov.institutemanager.entity.Group;

import java.util.List;

public interface GroupDao {
    List<Group> getAll();

    void delete(int groupId);

    List<Group> getAllStudentGroups(int id);

    Group getById(int groupId);

    void edit(Group group);

    void add(Group group);
}
