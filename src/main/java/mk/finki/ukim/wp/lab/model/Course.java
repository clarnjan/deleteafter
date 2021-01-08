package mk.finki.ukim.wp.lab.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long courseId;
    String name;
    String description;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    List<Student> students;
    @ManyToOne
    Teacher teacher;
    Type tip;

    public Course(String name, String description, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        students=new ArrayList<>();
    }

    public Course() {
        students=new ArrayList<>();
    }

    public Long getCourseId() {
        return courseId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Type getTip() {
        return tip;
    }

    public void setTip(Type tip) {
        this.tip = tip;
    }
}
