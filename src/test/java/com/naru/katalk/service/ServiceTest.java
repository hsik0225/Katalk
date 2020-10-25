package com.naru.katalk.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.naru.katalk.config.ResultHandlerConfiguration;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class, MockitoExtension.class})
@AutoConfigureRestDocs
@ContextConfiguration(classes = ResultHandlerConfiguration.class)
public abstract class ServiceTest {

    @Autowired
    private RestDocumentationResultHandler restDocumentation;
}
