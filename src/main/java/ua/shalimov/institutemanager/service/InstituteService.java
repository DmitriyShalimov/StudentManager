package ua.shalimov.institutemanager.service;

import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.util.List;

public interface InstituteService {

    public List<Student> getSortAllStudents(String id);

    public List<Group> getSortAllGroup(String id);

    public List<Student> getAllStudents();

    List<Student> getAllStudentsFromGroup(String title);

    List<Group> getAllGroup();

    void addNewStudent(String firstName, String lastName);

    void addNewGroup(String title);

    List<Student> findStudentById(int id);

    List<Group> findGroupById(int id);

    List<Student> findByNameLastName(String firstName, String lastName);

    List<Student> findByName(String firstName);

    List<Student> findByLastName(String lastName);

    void deleteStudent(int studentId);

    void deleteGroup(int group_id);

    void editStudent(Student student);

    void editGroup(Group group);

    void changeStudentsGroups(String[] groupTitle, int studentId);
}
