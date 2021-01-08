package mk.finki.ukim.wp.lab.model.exceptions;

public class CourseAlreadyExistsException extends RuntimeException{

    public CourseAlreadyExistsException(String message) {
        super(String.format("Course with name: %s already exists!!", message));
    }
}
