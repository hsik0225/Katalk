package com.naru.katalk.common;

public class Response {

    protected String message;

    protected int statusCode;

    protected String statusText;

    protected Object body;

    public Response(ResponseStatusCode code) {
        this.message = code.getMessage();
        this.statusCode = code.getCode();
        this.statusText = code.getStatusText();
    }

    public Response(ResponseStatusCode code, Object body) {
        this(code);
        this.body = body;
    }
}
