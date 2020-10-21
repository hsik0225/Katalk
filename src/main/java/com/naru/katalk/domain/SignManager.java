package com.naru.katalk.domain;

import com.naru.katalk.exception.LoginException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignManager {

    @Email
    private String email;

    // 소문자 영어, 숫자가 1번씩 포함되어야 하며 비밀번호는 대/소문자, 숫자만이 가능하다
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])[a-zA-Z0-9]*$")
    @Size(min = 6, max = 16)
    private String password;

    private String confirmPassword;

    public boolean checkConfirmPassword() {
        return this.password.equals(confirmPassword);
    }

    public void checkPassword(SignManager signManager) {
        if (!BCrypt.checkpw(signManager.password, this.password)) {
            throw new LoginException();
        }
    }

    public void hashPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }
}
