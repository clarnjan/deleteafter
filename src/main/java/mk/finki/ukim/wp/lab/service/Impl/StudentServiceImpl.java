package mk.finki.ukim.wp.lab.service.Impl;

import mk.finki.ukim.wp.lab.model.Student;
import mk.finki.ukim.wp.lab.repository.jpa.StudentRepository;
import mk.finki.ukim.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return null;
    }

    @Override
    public Student findByUserName(String username) {
//        return studentRepository.findAll().stream().filter(x->x.getUsername().equals(username)).findFirst().get();
          return studentRepository.findByUsername(username);
    }

    @Override
    public Student save(String username, String password, String name, String surname) {
//        if(studentRepository.findAll().stream().anyMatch(x->x.getUsername().equals(username)))
//            return null;
//        Student student=new Student(username,password,name,surname);
//        studentRepository.findAll().add(student);
//        return student;

        studentRepository.deleteByUsername(username);
        return studentRepository.save(new Student(username,password,name,surname));
    }
}
