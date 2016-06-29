package service;

import org.junit.Before;
import org.junit.Test;
import ua.shalimov.institutemanager.entity.Group;
import ua.shalimov.institutemanager.entity.Student;
import ua.shalimov.institutemanager.service.InstituteCache;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InstituteCacheTest {
    private InstituteCache cache;
    private Student student1 = new Student(1, "Ivan", "Ivanov");
    private Student student2 = new Student(2, "Dima", "Shalimov");
    private Group group1 = new Group(1, "group1");
    private Group group2 = new Group(2, "group2");
    private List<Student> allStudents;
    private List<Group> allGroup;

    @Before
    public void setUpInstituteCache() {
        cache = new InstituteCache();
        allStudents = new ArrayList<>();
        allGroup = new ArrayList<>();
    }

    @Test
    public void testMethodFindByName() {
        allStudents.add(student1);
        allStudents.add(student2);
        cache.setAllStudents(allStudents);
        assertThat(cache.findByName("Ivan").get(0), is(student1));
        assertThat(cache.findByName("Dima").get(0), is(student2));
    }

    @Test
    public void testMethodFindByLastName() {
        allStudents.add(student1);
        allStudents.add(student2);
        cache.setAllStudents(allStudents);
        assertThat(cache.findByLastName("Ivanov").get(0), is(student1));
        assertThat(cache.findByLastName("Shalimov").get(0), is(student2));
    }

    @Test
    public void testMethodFindByNameLastName() {
        allStudents.add(student1);
        allStudents.add(student2);
        cache.setAllStudents(allStudents);
        assertThat(cache.findByNameLastName("Ivan", "Ivanov").get(0), is(student1));
        assertThat(cache.findByNameLastName("Dima", "Shalimov").get(0), is(student2));
    }

    @Test
    public void testMethodFindById() {
        allStudents.add(student1);
        allStudents.add(student2);
        cache.setAllStudents(allStudents);
        assertThat(cache.findById(1).get(0), is(student1));
        assertThat(cache.findById(2).get(0), is(student2));
    }

    @Test
    public void testMethodGetAllStudentsFromGroup() {
        allStudents.add(student1);
        allGroup.add(group1);
        allGroup.add(group2);
        student1.setListGroup(allGroup);
        cache.setAllStudents(allStudents);
        assertThat(cache.getAllStudentsFromGroup("group1").get(0), is(student1));
        assertThat(cache.getAllStudentsFromGroup("group2").get(0), is(student1));
    }

    @Test
    public void testMethodFindGroupById() {
        allGroup.add(group1);
        allGroup.add(group2);
        cache.setAllGroup(allGroup);
        assertThat(cache.findGroupById(1).get(0), is(group1));
        assertThat(cache.findGroupById(2).get(0), is(group2));
    }
}
