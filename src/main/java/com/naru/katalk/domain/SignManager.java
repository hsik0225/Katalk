package com.naru.katalk.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.mindrot.jbcrypt.BCrypt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.naru.katalk.exception.LoginException;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignManager {

    private static final String EMAIL = "test@naru.com";

    private static final String PASSWORD = "test1234";

    private static final String CONFIRM_PASSWORD = "test1234";

    @Email
    private String email;

    // 소문자 영어, 숫자가 1번씩 포함되어야 하며 비밀번호는 대/소문자, 숫자만이 가능합니다
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])[a-zA-Z0-9]*$", message = "소문자 영어, 숫자가 1번씩 포함되어야 하며 비밀번호는 대/소문자, 숫자만이 가능합니다.")
    @Size(min = 6, max = 16)
    private String password;

    // persist 하지 않기 위해 transient 선언
    private transient String confirmPassword;

    public boolean checkConfirmPassword() {
        return this.password.equals(confirmPassword);
    }

    public void checkPassword(final SignManager signManager) {
        if (!BCrypt.checkpw(signManager.password, this.password)) {
            throw new LoginException();
        }
    }

    // 불변 객체임을 보장하기 위해 this.password = BCrypt.hashpw(this.password, BCrypt.gensalt()); 하지 않고
    // new SignManager로 새로운 객체를 생성하여 반환
    public static SignManager hashPassword(SignManager signManager) {
        String hashPassword = BCrypt.hashpw(signManager.password, BCrypt.gensalt());

        return new SignManager(signManager.email, hashPassword, signManager.confirmPassword);
    }

    public static SignManager getTestInstance() {
        return SignManager
                .builder()
                .email(EMAIL)
                .password(PASSWORD)
                .confirmPassword(CONFIRM_PASSWORD)
                .build();
    }
}
