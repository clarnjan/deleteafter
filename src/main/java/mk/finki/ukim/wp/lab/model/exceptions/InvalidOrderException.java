package mk.finki.ukim.wp.lab.model.exceptions;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException() {
        super("Invalid attributes!");
    }
}
