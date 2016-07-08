package ua.shalimov.institutemanager.controller.dto;

public class StudentWithGroupNameDto {
    private int id;
    private String firstName;
    private String lastName;
    private String groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "StudentWithGroupNameDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groups='" + groups + '\'' +
                '}';
    }
}
