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

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private InstituteService instituteService;

    @RequestMapping(value = "/studentManager", method = RequestMethod.GET)
    public String showAll(@RequestParam(value = "id", required = false) String id, ModelMap model) {
        logger.info("start method showAll");
        if ((id == null) || id.equals("allGroup")) {
            model.addAttribute("allStudent", instituteService.getAllStudents());
            model.addAttribute("allGroup", instituteService.getAllGroup());
        } else {
            model.addAttribute("allStudent", instituteService.getAllStudentsFromGroup(id));
            model.addAttribute("allGroup", instituteService.getAllGroup());
        }
        return "StudentManager";
    }

    @RequestMapping(value = "/editStudent")
    public String editStudent(@RequestParam int id, ModelMap model) {
        logger.info("Edit student. [id={}]", id);
        model.addAttribute("getStudentById", instituteService.findStudentById(id));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "EditStudent";
    }

    @RequestMapping(value = "/editStudent", method = RequestMethod.POST)
    public String editStudent(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam(value = "groupId", required = false) String[] groupId, ModelMap model) {
        logger.info("start method editStudent");
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
        logger.info("start method deleteStudent");
        instituteService.deleteStudent(id);
        model.addAttribute("allStudent", instituteService.getAllStudents());
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }

    @RequestMapping(value = "/addNewStudent", method = RequestMethod.GET)
    public String addNewStudent(ModelMap model) {
        logger.info("start method addNewStudent");
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "AddNewStudent";
    }

    @RequestMapping(value = "/addNewStudent", method = RequestMethod.POST)
    public String addNewStudent(@RequestParam String firstName, @RequestParam String lastName, @RequestParam(value = "groupId", required = false) String[] groupId, ModelMap model) {
        logger.info("start method addNewStudent");
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
        logger.info("start method findById");
        return "FindById";
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public String findById(@RequestParam String id, ModelMap model) {
        logger.info("start method findById");
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
        logger.info("start findByNameLastName");
        return "FindByNameLastName";
    }

    @RequestMapping(value = "/findByNameLastName", method = RequestMethod.POST)
    public String findByNameLastName(@RequestParam String firstName, @RequestParam String lastName, ModelMap model) {
        logger.info("start method findByNameLastName");
        model.addAttribute("allStudent", instituteService.findByNameLastName(firstName, lastName));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }

    @RequestMapping(value = "/findByLastName")
    public String findByLastName() {
        logger.info("start method findByLastName");
        return "FindByLastName";
    }

    @RequestMapping(value = "/findByLastName", method = RequestMethod.POST)
    public String findByLastName(@RequestParam String lastName, ModelMap model) {
        logger.info("start method findByLastName ");
        model.addAttribute("allStudent", instituteService.findByLastName(lastName));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }

    @RequestMapping(value = "/findByName")
    public String findBytName() {
        logger.info("start method findBytName ");
        return "FindByName";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam String firstName, ModelMap model) {
        logger.info("start method findByName ");
        model.addAttribute("allStudent", instituteService.findByName(firstName));
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "StudentManager";
    }
}
