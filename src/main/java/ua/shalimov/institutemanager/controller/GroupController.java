package ua.shalimov.institutemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.service.InstituteService;

@Controller
public class GroupController {
    private Logger logger = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private InstituteService instituteService;

    @RequestMapping(value = "/groupManager", method = RequestMethod.GET)
    public String groupManager(ModelMap model) {
        logger.info("start method groupManager");
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "GroupManager";
    }

    @RequestMapping(value = "/editGroup", method = RequestMethod.GET)
    public String editGroup(@RequestParam int id, ModelMap model) {
        logger.info("start method editGroup");
        model.addAttribute("getGroupById", instituteService.findGroupById(id));
        return "EditGroup";
    }

    @RequestMapping(value = "/editGroup", method = RequestMethod.POST)
    public String editGroup(@RequestParam int id, @RequestParam String title, ModelMap model) {
        logger.info("start method editGroup");
        if (title.equals("")) {
            model.addAttribute("getGroupById", instituteService.findGroupById(id));
            return "EditGroup";
        } else {
            Group group = new Group(id, title);
            instituteService.editGroup(group);
            model.addAttribute("allStudent", instituteService.getAllStudents());
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "GroupManager";
        }
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.GET)
    public String deleteGroup(@RequestParam int id, ModelMap model) {
        logger.info("start method deleteGroup");
        instituteService.deleteGroup(id);
        model.addAttribute("allStudent", instituteService.getAllStudents());
        model.addAttribute("allGroup", instituteService.getAllGroup());
        return "GroupManager";
    }

    @RequestMapping(value = "/addNewGroup")
    public String addNewGroup() {
        logger.info("start method addNewGroup");
        return "AddNewGroup";
    }

    @RequestMapping(value = "/addNewGroup", method = RequestMethod.POST)
    public String addNewGroup(@RequestParam String title, ModelMap model) {
        logger.info("start method addNewGroup");
        if (title.equals("")) {
            return "AddNewGroup";
        } else {
            System.out.println("here");
            System.out.println(title);
            System.out.println("title;" + title + "!");
            instituteService.addNewGroup(title);
            model.addAttribute("allStudent", instituteService.getAllStudents());
            model.addAttribute("allGroup", instituteService.getAllGroup());
            return "GroupManager";
        }
    }
}
