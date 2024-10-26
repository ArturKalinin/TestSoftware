package project.filmorate.exception;

public class InvalidFilmId extends RuntimeException{
    public InvalidFilmId(String message) {
        super(message);
    }
}
