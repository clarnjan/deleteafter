package mk.finki.ukim.wp.lab.service.Impl;

import mk.finki.ukim.wp.lab.model.Course;
import mk.finki.ukim.wp.lab.model.Grade;
import mk.finki.ukim.wp.lab.model.Student;
import mk.finki.ukim.wp.lab.model.Teacher;
import mk.finki.ukim.wp.lab.repository.jpa.CourseRepository;
import mk.finki.ukim.wp.lab.repository.jpa.GradeRepository;
import mk.finki.ukim.wp.lab.repository.jpa.StudentRepository;
import mk.finki.ukim.wp.lab.repository.jpa.TeacherRepository;
import mk.finki.ukim.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
//        return courseRepository.findAll().stream()
//                .filter(x->x.getCourseId().equals(courseId)).findFirst().get().getStudents();
        Course course=courseRepository.findById(courseId).get();
        return course.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
//        Student student=studentService.findByUserName(username);
//        Optional<Course> course=courseRepository.findById(courseId);
//        if(course.get().getStudents().contains(student))
//            return null;
//        return courseRepository.addStudentToCourse(student,course);
        Student student=studentRepository.findByUsername(username);
        try {
            Course course=courseRepository.findById(courseId).get();
            List<Student> students=course.getStudents();
            students.add(student);
            course.setStudents(students);
            courseRepository.save(course);
            gradeRepository.save(new Grade('9',student,course));
            return course;

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public void save(String courseId, String name, String description, Long teacherId) {
        Optional<Teacher> teacher=teacherRepository.findById(teacherId);
        if(!teacher.isEmpty()){
            if(!courseId.equals(""))
                courseRepository.deleteById(Long.parseLong(courseId));
            courseRepository.save(new Course(name,description,teacher.get()));
        }
    }

    @Override
    public List<Grade> getStudentGrades(Long courseId) {
        List<Student> students=listStudentsByCourse(courseId);
        List<Grade> grades=new ArrayList<>();
        for (int i=0;i<students.size();i++){
            Grade grade=gradeRepository.findByStudent_UsernameAndCourse_CourseId(students.get(i).getUsername(),courseId).get();
            grades.add(grade);
        }
        return grades;
    }

    public List<Course> sort() {
//        return listAll().stream().sorted(Comparator.comparing(x->x.getName().toLowerCase())).collect(Collectors.toList());
        return courseRepository.findByOrderByNameAsc();
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public List<Course> search(String search) {
        return courseRepository.findAllByNameContainingOrDescriptionContainingOrTipContainingOrderByName(search,search,search);
    }
}
