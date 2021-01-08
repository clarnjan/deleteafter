package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findByOrderByNameAsc();
    List<Course> findAllByNameContainingOrDescriptionContainingOrTipContainingOrderByName(String name,String desc,String tip);
}
