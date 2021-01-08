package mk.finki.ukim.wp.lab.service;


import mk.finki.ukim.wp.lab.model.Course;
import mk.finki.ukim.wp.lab.model.Grade;
import mk.finki.ukim.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> listAll();
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);


    Optional<Course> findById(Long id);

    void save(String courseId, String name, String description, Long teacherId);

    List<Grade> getStudentGrades(Long courseId);
}
