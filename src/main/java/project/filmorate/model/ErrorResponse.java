package project.filmorate.model;

import lombok.Data;


public class ErrorResponse {
    String error;

    public ErrorResponse(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
