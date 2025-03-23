package org.example.swe304.Exceptions;

import lombok.*;

import java.util.Date;


@Data
public class APIErrors<T> {

    private String errorId;
    private Date errorTime;
    private T errors;

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }

    public T getErrors() {
        return errors;
    }

    public void setErrors(T errors) {
        this.errors = errors;
    }

    public APIErrors(String errorId, Date errorTime, T errors) {
        this.errorId = errorId;
        this.errorTime = errorTime;
        this.errors = errors;
    }

    public APIErrors() {
    }


}
