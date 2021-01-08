package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {

    Optional<Grade> findByStudent_UsernameAndCourse_CourseId(String username,Long courseId);
}
