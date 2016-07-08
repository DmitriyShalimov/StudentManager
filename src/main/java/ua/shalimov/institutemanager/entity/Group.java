package ua.shalimov.institutemanager.entity;

public class Group {
    private int id;
    private String title;

    public Group() {
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}