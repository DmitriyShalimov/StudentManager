package ua.shalimov.institutemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.shalimov.institutemanager.dao.DataAccess;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.util.List;

@Service
public class InstituteServiceImplementation implements InstituteService {

    @Autowired
    private DataAccess dataAccess;

    public List<Student> getAllStudents() {
        return dataAccess.getAllStudents();
    }

    public List<Student> getAllStudentsFromGroup(String title) {
        return dataAccess.getAllStudentsFromGroup(title);
    }

    public List<Group> getAllGroup() {
        return dataAccess.getAllGroup();
    }

    public void addNewStudent(String firstName, String lastName) {
        dataAccess.addNewStudent(firstName, lastName);
    }

    public void addNewGroup(String title) {
        dataAccess.addNewGroup(title);
    }

    public List<Student> findStudentById(int id) {
        return dataAccess.findById(id);
    }

    public List<Group> findGroupById(int id) {
        return dataAccess.findGroupById(id);
    }

    public List<Student> findByNameLastName(String firstName, String lastName) {
        return dataAccess.findByNameLastName(firstName, lastName);
    }

    public List<Student> findByName(String firstName) {
        return dataAccess.findByName(firstName);
    }

    public List<Student> findByLastName(String lastName) {
        return dataAccess.findByLastName(lastName);
    }

    public void deleteStudent(int studentId) {
        dataAccess.deleteStudent(studentId);
    }

    public void deleteGroup(int group_id) {
        dataAccess.deleteGroup(group_id);
    }

    public void editStudent(Student student) {
        dataAccess.editStudent(student);
    }

    public void editGroup(Group group) {
        dataAccess.editGroup(group);
    }

    public void changeStudentsGroups(String[] groupTitle, int studentId) {
        dataAccess.changeStudentsGroups(groupTitle, studentId);
    }
}
