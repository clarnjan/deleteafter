package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryStudentRepository {
    public static List<Student> studentList=new ArrayList<>();

    Student s1=new Student("Katerina","lcate","Katerina","Golchevska");
    Student s2=new Student("Aleksandra","aclca","Aleksandra","Golchevska");
    Student s3=new Student("Dimitar","clirnitar","Dimitar","Anakiev");
    Student s4=new Student("Tashko","ta6k0","Tashko","Pavlov");
    Student s5=new Student("Elena","3l3na","Elena","Kuzmanovska");

    public InMemoryStudentRepository(){

        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);

    }

    public List<Student> findAllStudents(){
        return studentList;
    }


    public List<Student> findAllByNameOrSurname(String text){
        List<Student> contejnat=new ArrayList<>();
        for(Student s : studentList){
            if(s.getName().contains(text)||s.getSurname().contains(text)){
                contejnat.add(s);
            }
        }
        return contejnat;
    }

    /*
    public Student save(String username, String password, String name, String surname) {
        Student s=new Student(username,password,name,surname);
        studentList.add(s);
        return s;
    }*/
}
