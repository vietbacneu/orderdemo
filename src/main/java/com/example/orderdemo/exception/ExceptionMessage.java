package com.example.orderdemo.exception;

public class ExceptionMessage {
    private int status;
    private String message;

    public ExceptionMessage() {
    }

    public ExceptionMessage(int numberStatus, String message) {
        this.status = numberStatus;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int numberStatus) {
        this.status = numberStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
