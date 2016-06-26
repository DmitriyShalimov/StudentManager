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
    private InstituteCache instituteCache;
    @Autowired
    private DataAccess dataAccess;

    public List<Student> getSortAllStudents(String id) {
        return instituteCache.sortStudent(id);
    }

    public List<Group> getSortAllGroup(String id) {
        return instituteCache.sortGroup(id);
    }

    public List<Student> getAllStudents() {
        return instituteCache.getAllStudents();
    }

    public List<Student> getAllStudentsFromGroup(String title) {
        return instituteCache.getAllStudentsFromGroup(title);
    }

    public List<Group> getAllGroup() {
        return instituteCache.getAllGroup();
    }

    public List<Student> findStudentById(int id) {
        return instituteCache.findById(id);
    }

    public List<Student> findByNameLastName(String firstName, String lastName) {
        return instituteCache.findByNameLastName(firstName, lastName);
    }

    public List<Student> findByName(String firstName) {
        return instituteCache.findByName(firstName);
    }

    public List<Student> findByLastName(String lastName) {
        return instituteCache.findByLastName(lastName);
    }

    public List<Group> findGroupById(int id) {
        return instituteCache.findGroupById(id);
    }

    public void addNewStudent(String firstName, String lastName) {
        dataAccess.addNewStudent(firstName, lastName);
        instituteCache.init();
    }

    public void addNewGroup(String title) {
        dataAccess.addNewGroup(title);
        instituteCache.init();
    }


    public void deleteStudent(int studentId) {
        dataAccess.deleteStudent(studentId);
        instituteCache.init();
    }

    public void deleteGroup(int group_id) {
        dataAccess.deleteGroup(group_id);
        instituteCache.init();
    }

    public void editStudent(Student student) {
        dataAccess.editStudent(student);
        instituteCache.init();
    }

    public void editGroup(Group group) {
        dataAccess.editGroup(group);
        instituteCache.init();
    }

    public void changeStudentsGroups(String[] groupTitle, int studentId) {
        dataAccess.changeStudentsGroups(groupTitle, studentId);
        instituteCache.init();
    }
}
