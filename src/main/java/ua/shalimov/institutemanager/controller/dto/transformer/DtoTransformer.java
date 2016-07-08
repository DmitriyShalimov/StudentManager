package ua.shalimov.institutemanager.controller.dto.transformer;

import ua.shalimov.institutemanager.controller.dto.StudentWithGroupNameDto;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class DtoTransformer {
    public static List<StudentWithGroupNameDto> convert(Iterable<Student> iterable) {
        List<StudentWithGroupNameDto> result = new ArrayList<>();
        for (Student student : iterable) {
            result.add(convert(student));
        }
        return result;
    }

    public static StudentWithGroupNameDto convert(Student student) {
        StudentWithGroupNameDto studentDto = new StudentWithGroupNameDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Group group : student.getGroups()) {
            stringJoiner.add(group.getTitle());
        }
        studentDto.setGroups(stringJoiner.toString());
        return studentDto;
    }
}
