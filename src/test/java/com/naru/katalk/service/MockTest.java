package com.naru.katalk.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.naru.katalk.config.ResultHandlerConfiguration;

/*
Service 단에서 Mockito 를 사용하는 이유

 */
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class, MockitoExtension.class})
@AutoConfigureRestDocs
@ContextConfiguration(classes = ResultHandlerConfiguration.class)
public class MockTest {
}
