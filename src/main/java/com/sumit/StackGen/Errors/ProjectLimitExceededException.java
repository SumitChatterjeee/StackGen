package com.sumit.StackGen.Errors;

public class ProjectLimitExceededException extends RuntimeException {

    public ProjectLimitExceededException(String message) {
        super(message);
    }
}