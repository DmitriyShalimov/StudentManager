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

    public List<Student> sortStudent(String id, String sortId) {
        List<Student> list;
        if ((id == null) || id.equals("allGroup")) {
            list = allStudents;
        } else {
            list = getAllStudentsFromGroup(id);
        }
        if (sortId.equals("sortByName")) {
            Collections.sort(list, (student1, student2) -> student1.getFirstName().compareTo(student2.getFirstName()));
        } else {
            if (sortId.equals("sortByLastName")) {
                Collections.sort(list, (student1, student2) -> student1.getLastName().compareTo(student2.getLastName()));
            } else {
                if (sortId.equals("sortById")) {
                    Collections.sort(list, (student1, student2) -> student1.getId() - student2.getId());
                } else {
                    Collections.sort(list, (student1, student2) -> {
                        if (student1.getLastName().equals(student2.getLastName()))
                            return student1.getFirstName().compareTo(student2.getFirstName());
                        else
                            return student1.getLastName().compareTo(student2.getLastName());
                    });
                }
            }
        }
        return list;
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
