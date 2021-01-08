package mk.finki.ukim.wp.lab.model;

import javax.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;
    @OneToOne
    private Student student;
    @OneToOne
    private Course course;

    public Grade(Character grade, Student student, Course course) {
        this.grade = grade;
        this.student = student;
        this.course = course;
    }

    public Grade() {
    }

    public Character getGrade() {
        return grade;
    }

    public String getUsername() {
        return student.username;
    }
}
