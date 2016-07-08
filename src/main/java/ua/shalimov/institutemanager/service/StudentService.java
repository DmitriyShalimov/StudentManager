package ua.shalimov.institutemanager.service;

import ua.shalimov.institutemanager.entity.SortType;
import ua.shalimov.institutemanager.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();

    void delete(int id);

    List<Student> getByGroupId(int groupId);

    Student getById(int id);

    List<Student> getByName(String lastName);

    List<Student> sort(SortType sortType, List<Student> students);

    void add(Student student);

    void edit(Student student);

    boolean validate(Student student);
}
