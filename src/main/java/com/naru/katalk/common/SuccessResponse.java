package com.naru.katalk.common;

public final class SuccessResponse extends Response {

    private SuccessResponse(final SuccessCode code) {
        super(code, null);
    }

    private SuccessResponse(final SuccessCode code, Object body) {
        super(code, body);
    }

    public static SuccessResponse from(final SuccessCode code) {
        return new SuccessResponse(code);
    }

    public static SuccessResponse of(final SuccessCode code, Object body) {
        return new SuccessResponse(code, body);
    }
}
