package com.naru.katalk.common;

public final class SuccessResponse extends Response {

    private SuccessResponse(final ResponseStatusCode code) {
        super(code, null);
    }

    private SuccessResponse(final ResponseStatusCode code, Object body) {
        super(code, body);
    }

    public static SuccessResponse from(final ResponseStatusCode code) {
        return new SuccessResponse(code);
    }

    public static SuccessResponse of(final ResponseStatusCode code, Object body) {
        return new SuccessResponse(code, body);
    }
}
