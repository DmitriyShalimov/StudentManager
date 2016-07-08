package ua.shalimov.institutemanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shalimov.institutemanager.entity.SortType;
import ua.shalimov.institutemanager.dao.StudentDao;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;
import ua.shalimov.institutemanager.service.GroupService;
import ua.shalimov.institutemanager.service.StudentService;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private GroupService groupService;

    @Override
    public List<Student> getAll() {
        List<Student> students = studentDao.getAll();
        addGroupsForStudent(students);
        return students;
    }

    @Override
    public void delete(int id) {
        studentDao.delete(id);
    }

    @Override
    public List<Student> getByGroupId(int groupId) {
        List<Student> students = studentDao.getAllFromGroup(groupId);
        addGroupsForStudent(students);
        return students;
    }

    @Override
    public List<Student> sort(SortType sortType, List<Student> students) {
        if (sortType != null) {
            switch (sortType) {
                case asc:
                    Collections.sort(students, (student1, student2) -> student1.getLastName().compareTo(student2.getLastName()));
                    break;
                case desc:
                    Collections.sort(students, (student1, student2) -> student2.getLastName().compareTo(student1.getLastName()));
                    break;
            }
        }
        return students;
    }


    @Override
    public void add(Student student) {
        studentDao.add(student);
    }

    @Override
    public void edit(Student student) {
        studentDao.edit(student);
    }

    @Override
    public boolean validate(Student student) {
        return (student.getFirstName() != null) && (student.getLastName() != null) && !((student.getFirstName().length() < 3) || (student.getFirstName().contains("  ")) || (student.getLastName().length() < 3) || (student.getLastName().contains("  ")));
    }

    @Override
    public Student getById(int id) {
        List<Group> groups = groupService.getAllStudentGroups(id);
        Student student = studentDao.getById(id);
        student.setGroups(groups);
        return student;
    }

    @Override
    public List<Student> getByName(String lastName) {
        List<Student> students = studentDao.getByName(lastName);
        addGroupsForStudent(students);
        return students;
    }

    private void addGroupsForStudent(List<Student> students) {
        for (Student student : students) {
            List<Group> groups = groupService.getAllStudentGroups(student.getId());
            student.setGroups(groups);
        }
    }
}
