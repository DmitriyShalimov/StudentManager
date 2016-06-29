package ua.shalimov.institutemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.shalimov.institutemanager.entity.Student;
import ua.shalimov.institutemanager.service.InstituteService;

@Controller
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private InstituteService instituteService;

    @RequestMapping(value = "/studentManager", method = RequestMethod.GET)
    public String showAll(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "sortId", required = false) String sortId, ModelMap model) {
        logger.info("show student from group. id={}", id);

        if ((id == null) || id.equals("allGroup")) {
            model.addAttribute("allStudent", instituteService.getAllStudents());
            model.addAttribute("allGroup", instituteService.getAllGroup());
        } else {
            if ((sortId != null) && (sortId.contains("sortBy"))) {
                model.addAttribute("allStudent", instituteService.getSortAllStudents(id, sortId));
                model.addAttribute("allGroup", instituteService.getAllGroup());
            } else {
                model.addAttribute("allStudent", instituteService.getAllStudentsFromGroup(id));
                model.addAttribute("allGroup", instituteService.getAllGroup());
            }
        }
        return "StudentManager";
    }

    @RequestMapping(value = "/editStudent")
    public String editStudent(@RequestParam int id, ModelMap model) {
        logger.info("Edit student. id={}", id);
        model.addAttribute("getStudentById", instituteService.findStudentById(id));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "EditStudent";
    }

    @RequestMapping(value = "/editStudent", method = RequestMethod.POST)
    public String editStudent(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam(value = "groupId", required = false) String[] groupId, ModelMap model) {
        logger.info("Edit student. id={},firstName={} lastName={}", id, firstName, lastName);
        if (firstName.equals("") || (lastName.equals(""))) {
            model.addAttribute("getStudentById", instituteService.findStudentById(id));
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "EditStudent";
        } else {
            Student student = new Student(id, firstName, lastName);
            instituteService.editStudent(student);
            instituteService.changeStudentsGroups(groupId, id);
            model.addAttribute("allStudent", instituteService.getAllStudents());
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "StudentManager";
        }
    }

    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam int id, ModelMap model) {
        logger.info("delete student. id={}", id);
        instituteService.deleteStudent(id);
        model.addAttribute("allStudent", instituteService.getAllStudents());
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }

    @RequestMapping(value = "/addNewStudent", method = RequestMethod.GET)
    public String addNewStudent(ModelMap model) {
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "AddNewStudent";
    }

    @RequestMapping(value = "/addNewStudent", method = RequestMethod.POST)
    public String addNewStudent(@RequestParam String firstName, @RequestParam String
            lastName, @RequestParam(value = "groupId", required = false) String[] groupId, ModelMap model) {
        logger.info("add student. firstName={} lastName={}", firstName, lastName);
        if (firstName.equals("") || (lastName.equals(""))) {
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "AddNewStudent";
        } else {
            instituteService.addNewStudent(firstName, lastName);
            int id = instituteService.findByNameLastName(firstName, lastName).get(0).getId();
            instituteService.changeStudentsGroups(groupId, id);
            model.addAttribute("allStudent", instituteService.getAllStudents());
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "StudentManager";
        }
    }

    @RequestMapping(value = "/findById")
    public String findById() {
        return "FindById";
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public String findById(@RequestParam String id, ModelMap model) {
        logger.info("find student. id={}", id);
        try {
            model.addAttribute("allStudent", instituteService.findStudentById(Integer.parseInt(id)));
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "StudentManager";
        } catch (NumberFormatException exception) {
            return "FindById";
        }
    }

    @RequestMapping(value = "/findByNameLastName")
    public String findByNameLastName() {
        return "FindByNameLastName";
    }

    @RequestMapping(value = "/findByNameLastName", method = RequestMethod.POST)
    public String findByNameLastName(@RequestParam String firstName, @RequestParam String lastName, ModelMap model) {
        logger.info("find student. firstName={} lastName={}", firstName, lastName);
        model.addAttribute("allStudent", instituteService.findByNameLastName(firstName, lastName));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }

    @RequestMapping(value = "/findByLastName")
    public String findByLastName() {
        return "FindByLastName";
    }

    @RequestMapping(value = "/findByLastName", method = RequestMethod.POST)
    public String findByLastName(@RequestParam String lastName, ModelMap model) {
        logger.info("find student. lastName={}", lastName);
        model.addAttribute("allStudent", instituteService.findByLastName(lastName));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }

    @RequestMapping(value = "/findByName")
    public String findBytName() {
        return "FindByName";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam String firstName, ModelMap model) {
        logger.info("find student. firstName={}", firstName);
        model.addAttribute("allStudent", instituteService.findByName(firstName));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }
}
