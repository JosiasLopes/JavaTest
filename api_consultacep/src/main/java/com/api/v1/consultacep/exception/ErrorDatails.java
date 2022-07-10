package com.api.v1.consultacep.exception;

import java.util.Date;

public class ErrorDatails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDatails(Date timestamp, String message, String details) {
       // super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
