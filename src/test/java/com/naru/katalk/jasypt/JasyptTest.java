package com.naru.katalk.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.naru.katalk.config.JasyptConfig;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(JasyptConfig.class)
public class JasyptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void initTest() {
        assertThat(stringEncryptor).isNotNull();
    }

    @Test
    public void jasyptTest() {
        String encryptedMessage = stringEncryptor.encrypt("test");
        String decryptedMessage = stringEncryptor.decrypt(encryptedMessage);

        System.out.println("encryptedMessage = " + encryptedMessage);

        assertThat(decryptedMessage).isEqualTo("decryptedMessage");
    }
}
