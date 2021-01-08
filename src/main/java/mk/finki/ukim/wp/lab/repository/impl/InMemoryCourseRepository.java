package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.model.Course;
import mk.finki.ukim.wp.lab.model.Student;
import mk.finki.ukim.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InMemoryCourseRepository {
    public static List<Course> courseList=new ArrayList<>();

    public InMemoryCourseRepository() {
//        List<Student> students = new ArrayList<>();
//        Course c1 = new Course( "Internet programiranje", "izi mani", students);
//        Course c2 = new Course("Veb programiranje", "potreben2", students);
//        Course c3 = new Course("Strukturno programiranje", "izi mani2 ", students);
//        Course c4 = new Course("Objektno-orientirano programiranje", "potreben", students);
//        Course c5 = new Course( "Napredno programiranje", "no izi mani", students);
//
//        c1.setTip(Type.SUMMER);
//        c2.setTip(Type.WINTER);
//        c3.setTip(Type.MANDATORY);
//        c4.setTip(Type.ELECTIVE);
//        c5.setTip(Type.SUMMER);
//
//        courseList.add(c1);
//        courseList.add(c2);
//        courseList.add(c3);
//        courseList.add(c4);
//        courseList.add(c5);
    }
    public List<Course> findAllCourses() {
        return courseList;
    }

    public Course findById(Long courseId){
        for(Course c:courseList){
            if(c.getCourseId()==courseId) {
                return c;
            }
        }
        return null;
    }

    public List<Student> findAllStudentsByCourse(long courseId){
        return findById(courseId).getStudents();
    }


    public Course addStudentToCourse(Student student, Course course) {
        List<Student> students=findAllStudentsByCourse(course.getCourseId());
        students.add(student);
        course.setStudents(students);
        return course;
    }


    public void save(String name, String description, Teacher teacher) {
        Optional<Course> optionalCourse = courseList.stream().filter(x->x.getName().equals(name)).findAny();
        Course course=null;
        if(optionalCourse.isPresent()) {
            course = optionalCourse.get();
            course.setTeacher(teacher);
            course.setName(name);
            course.setDescription(description);
        }
        else {
//            course = new Course(name, description, new ArrayList<>());
//            course.setTeacher(teacher);
//            courseList.add(course);
        }
    }
}
