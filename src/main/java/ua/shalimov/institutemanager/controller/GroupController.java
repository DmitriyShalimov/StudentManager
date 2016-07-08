package ua.shalimov.institutemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.shalimov.institutemanager.service.parser.JsonParser;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.service.GroupService;

@Controller
public class GroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @RequestMapping("/groups")
    public String showAllGroup(ModelMap model) {
        LOGGER.info("Start obtaining all groups");
        long startTime = System.currentTimeMillis();
        model.addAttribute("allGroups", groupService.getAll());
        LOGGER.info("Groups were obtained. It took {} ms", System.currentTimeMillis() - startTime);
        return "groups";
    }

    @RequestMapping("/group/add")
    public String addNewGroup() {
        LOGGER.info("Page for adding new group");
        return "addGroup";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public ResponseEntity addNewGroup(@RequestBody String json) {
        LOGGER.info("Start adding new group");
        long startTime = System.currentTimeMillis();
        Group group = new JsonParser().jsonToGroup(json);
        if (groupService.validate(group)) {
            groupService.add(group);
            LOGGER.info("New group was added. It took {} ms", System.currentTimeMillis() - startTime);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOGGER.info("Title not passed validation");
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @RequestMapping(value = "/group/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGroup(@PathVariable("id") int id) {
        LOGGER.info("Start deleting group with id={}", id);
        long startTime = System.currentTimeMillis();
        groupService.delete(id);
        LOGGER.info("Group was deleted. It took {} ms", System.currentTimeMillis() - startTime);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/group/{id}")
    public String editGroup(@PathVariable("id") int id, ModelMap model) {
        LOGGER.info("Start loading page for edit group");
        long startTime = System.currentTimeMillis();
        model.addAttribute("group", groupService.getGroupById(id));
        LOGGER.info("Page for edit group is loaded. It took {} ms", System.currentTimeMillis() - startTime);
        return "editGroup";
    }

    @RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
    public ResponseEntity editGroup(@RequestBody String json, @PathVariable("id") int id) {
        LOGGER.info("Start edit group with id={}", id);
        long startTime = System.currentTimeMillis();
        Group group = new JsonParser().jsonToGroup(json);
        if (groupService.validate(group)) {
            group.setId(id);
            groupService.edit(group);
            LOGGER.info("Group was edited. It took {} ms", System.currentTimeMillis() - startTime);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOGGER.info("Title not passed validation");
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
