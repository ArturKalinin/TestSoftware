package project.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.filmorate.exception.FriendsException;
import project.filmorate.exception.InvalidUser;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.exception.ValidationException;
import project.filmorate.model.ErrorResponse;
import project.filmorate.exception.IncorrectParameterException;
import project.filmorate.exception.InvalidFilmId;
import project.filmorate.exception.LikesException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler({InvalidFilmId.class, InvalidUserId.class, InvalidUser.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleInvalidId(final RuntimeException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(final IncorrectParameterException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleLikesException(final LikesException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleFriendsException(final FriendsException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleThrowable(final Throwable e){
        return new ErrorResponse(e.getMessage());
    }


}
