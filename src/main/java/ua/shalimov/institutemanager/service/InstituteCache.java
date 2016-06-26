package ua.shalimov.institutemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.shalimov.institutemanager.dao.DataAccess;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InstituteCache {
    @Autowired
    private DataAccess dataAccess;
    private List<Student> allStudents;
    private List<Group> allGroup;

    public List<Group> getAllGroup() {
        return allGroup;
    }

    public void setAllGroup(List<Group> allGroup) {
        this.allGroup = allGroup;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    @PostConstruct
    public void init() {
        fillStudentsList();
        fillGroupList();
    }

    public List<Student> findByName(String name) {
        List<Student> list = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.getFirstName().equals(name)) {
                list.add(student);
            }
        }
        return list;
    }

    public List<Student> findByLastName(String lastName) {
        List<Student> list = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.getLastName().equals(lastName)) {
                list.add(student);
            }
        }
        return list;
    }

    public List<Student> findByNameLastName(String name, String lastName) {
        List<Student> list = new ArrayList<>();
        for (Student student : allStudents) {
            if ((student.getLastName().equals(lastName)) && (student.getFirstName().equals(name))) {
                list.add(student);
            }
        }
        return list;
    }

    public List<Student> findById(int id) {
        List<Student> list = new ArrayList<>();
        for (Student student : allStudents) {
            if (student.getId() == id) {
                list.add(student);
            }
        }
        return list;
    }

    public List<Student> getAllStudentsFromGroup(String title) {
        List<Student> list = new ArrayList<>();
        for (Student student : allStudents) {
            for (Group group : student.getListGroup()) {
                if (group.toString().equals(title)) {
                    list.add(student);
                }
            }
        }
        return list;
    }

    public List<Group> findGroupById(int id) {
        List<Group> list = new ArrayList<>();
        for (Group group : allGroup) {
            if (group.getId() == id) {
                list.add(group);
            }
        }
        return list;
    }

    public List<Student> sortStudent(String id) {
        if (id.equals("sortByName")) {
            Collections.sort(allStudents, (student1, student2) -> student1.getFirstName().compareTo(student2.getFirstName()));
        } else {
            if (id.equals("sortByLastName")) {
                Collections.sort(allStudents, (student1, student2) -> student1.getLastName().compareTo(student2.getLastName()));
            } else {
                if (id.equals("sortById")) {
                    Collections.sort(allStudents, (student1, student2) -> student1.getId() - student2.getId());
                } else {
                    Collections.sort(allStudents, (student1, student2) -> {
                        if (student1.getLastName().equals(student2.getLastName()))
                            return student1.getFirstName().compareTo(student2.getFirstName());
                        else
                            return student1.getLastName().compareTo(student2.getLastName());
                    });
                }
            }
        }
        return allStudents;
    }

    public List<Group> sortGroup(String id) {
        if (id.equals("sortByTitle")) {
            Collections.sort(allGroup, (group1, group2) -> group1.getTitle().compareTo(group2.getTitle()));
        } else {
            Collections.sort(allGroup, (group1, group2) -> group1.getId() - group2.getId());
        }
        return allGroup;
    }

    private void fillStudentsList() {
        allStudents = dataAccess.getAllStudents();
    }

    private void fillGroupList() {
        allGroup = dataAccess.getAllGroup();
    }
}
