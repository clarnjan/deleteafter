package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student findByUserName(String username);
    Student save(String username, String password, String name, String surname);

}
