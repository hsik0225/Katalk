package com.naru.katalk.common;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.Getter;

import com.naru.katalk.exception.RegisterException;

// 상속하여 새로운 관련 인스턴스를 생성하지 못하도록 설정
public final class ErrorResponse extends Response {

    // 생성자를 private을 만들어 불변 객체로 만든다
    private ErrorResponse(final ErrorCode code) {
        super(code, null);
    }

    // 만약 생성자를 그대로 사용한다면 어떤 클래스인지 알기 위하여 instanceof 를 사용해야 한다
    // ex. BindingResult 클래스가 들어왔을 경우 bindingResult instanceof BindingResult
    private ErrorResponse(final ErrorCode code, final FieldError... errors) {
        super(code, errors);
    }

    public static ErrorResponse from(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse from(final RegisterException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ErrorResponse(errorCode,
                FieldError.of(e.getField(), e.getValue(), errorCode.getReason()));
    }

    public static ErrorResponse of(final MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        FieldError error = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.LOGIN_INPUT_INVALID, error);
    }

    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, FieldError.from(bindingResult));
    }

    @Getter
    public static class FieldError {

        private final String field;

        private final String value;

        private final String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static FieldError of(final String field, final String value,
                                    final String reason) {

            return new FieldError(field, value, reason);
        }

        private static FieldError[] from(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors =
                    bindingResult.getFieldErrors();

            return fieldErrors
                    .stream()
                    .map(fieldError -> new FieldError(
                            fieldError.getField(),
                            fieldError.getRejectedValue() == null ? "" :
                                    fieldError.getRejectedValue().toString(),
                            fieldError.getDefaultMessage()))
                    .toArray(FieldError[]::new);
        }
    }
}
