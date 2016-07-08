package ua.shalimov.institutemanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shalimov.institutemanager.dao.GroupDao;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Group> getAll() {
        return groupDao.getAll();
    }

    @Override
    public void delete(int id) {
        groupDao.delete(id);
    }

    @Override
    public Group getGroupById(int groupId) {
        return groupDao.getById(groupId);
    }

    @Override
    public List<Group> getAllStudentGroups(int id) {
        return groupDao.getAllStudentGroups(id);
    }

    @Override
    public boolean validate(Group group) {
        if (group.getTitle() != null) {
            if ((group.getTitle().length() < 3) || (group.getTitle().contains("  "))) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Group group) {
        groupDao.add(group);
    }

    @Override
    public void edit(Group group) {
        groupDao.edit(group);
    }
}
