package ua.shalimov.institutemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.shalimov.institutemanager.entity.SortType;
import ua.shalimov.institutemanager.service.parser.JsonParser;
import ua.shalimov.institutemanager.controller.dto.transformer.DtoTransformer;
import ua.shalimov.institutemanager.entity.Student;
import ua.shalimov.institutemanager.service.GroupService;
import ua.shalimov.institutemanager.service.StudentService;

import java.util.List;

@Controller
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @RequestMapping("/students")
    public String showAllStudents(@RequestParam(value = "sortType", required = false) SortType sortType, ModelMap model) {
        LOGGER.info("Start obtaining all students");
        long startTime = System.currentTimeMillis();
        List<Student> students = studentService.getAll();
        model.addAttribute("students", DtoTransformer.convert(studentService.sort(sortType, students)));
        model.addAttribute("allGroups", groupService.getAll());
        LOGGER.info("Students were obtained. Their amount = {}. It took {} ms", students.size(), System.currentTimeMillis() - startTime);
        return "students";
    }

    @RequestMapping(value = "/students/group/{id}")
    public String showAllStudentsFromGroup(@RequestParam(value = "sortType", required = false) SortType sortType, @PathVariable int id, ModelMap model) {
        LOGGER.info("Start obtaining students from group with id={}", id);
        long startTime = System.currentTimeMillis();
        List<Student> students = studentService.getByGroupId(id);
        model.addAttribute("students", DtoTransformer.convert(studentService.sort(sortType, students)));
        model.addAttribute("allGroups", groupService.getAll());
        LOGGER.info("Students for group with id={} were obtained. Their amount = {}. It took {} ms", id, students.size(), System.currentTimeMillis() - startTime);
        return "students";
    }

    @RequestMapping(value = "/students/search")
    public String searchStudentByLastName(@RequestParam(value = "sortType", required = false) SortType sortType, @RequestParam("name") String lastName, ModelMap model) {
        LOGGER.info("Start searching for students with name={}", lastName);
        long startTime = System.currentTimeMillis();
        List<Student> students = studentService.getByName(lastName);
        model.addAttribute("students", DtoTransformer.convert(students));
        model.addAttribute("allGroups", groupService.getAll());
        LOGGER.info("Students with name={} were obtained. Their amount = {}. It took {} ms", lastName, students.size(), System.currentTimeMillis() - startTime);
        return "students";
    }

    @RequestMapping("/student/add")
    public String addNewStudent(ModelMap model) {
        LOGGER.info("Start loading page for adding new student");
        long startTime = System.currentTimeMillis();
        model.addAttribute("allGroups", groupService.getAll());
        LOGGER.info("Page for adding new student is loaded. It took {} ms", System.currentTimeMillis() - startTime);
        return "addStudent";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public ResponseEntity addNewStudent(@RequestBody String json) {
        LOGGER.info("Start adding new student");
        long startTime = System.currentTimeMillis();
        Student student = new JsonParser().jsonToStudent(json);
        if (studentService.validate(student)) {
            studentService.add(student);
            LOGGER.info("New student was added. It took {} ms", System.currentTimeMillis() - startTime);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOGGER.info("FirstName or lastName not passed validation");
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }

    }

    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable("id") int id) {
        LOGGER.info("Start deleting student with id={}", id);
        long startTime = System.currentTimeMillis();
        studentService.delete(id);
        LOGGER.info("Student was deleted. It took {} ms", System.currentTimeMillis() - startTime);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping("/student/{id}")
    public String editStudent(@PathVariable("id") int id, ModelMap model) {
        LOGGER.info("Start loading page for edit student");
        long startTime = System.currentTimeMillis();
        model.addAttribute("student", studentService.getById(id));
        model.addAttribute("allGroups", groupService.getAll());
        LOGGER.info("Page for edit student is loaded. It took {} ms", System.currentTimeMillis() - startTime);
        return "editStudent";
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editStudent(@RequestBody String json, @PathVariable("id") int id) {
        LOGGER.info("Start edit student with id={}", id);
        long startTime = System.currentTimeMillis();
        Student student = new JsonParser().jsonToStudent(json);
        if (studentService.validate(student)) {
            student.setId(id);
            studentService.edit(student);
            LOGGER.info("Student was edited. It took {} ms", System.currentTimeMillis() - startTime);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOGGER.info("FirstName or lastName not passed validation");
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }
    }
}