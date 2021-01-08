package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    Student findByUsername(String username);
    void deleteByUsername(String username);


}
