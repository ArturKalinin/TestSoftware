package project.filmorate.exception;

public class IncorrectParameterException extends RuntimeException {
    String parameter;

    public IncorrectParameterException(String message, String parameter) {
        super(message + ": " + parameter);

    }
}