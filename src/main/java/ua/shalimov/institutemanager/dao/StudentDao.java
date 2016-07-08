package ua.shalimov.institutemanager.dao;

import ua.shalimov.institutemanager.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAll();

    void delete(int studentId);

    List<Student> getAllFromGroup(int id);

    List<Student> getByName(String name);

    Student getById(int id);

    void add(Student student);

    void edit(Student student);
}
