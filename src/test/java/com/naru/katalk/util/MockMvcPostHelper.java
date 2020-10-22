package com.naru.katalk.util;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class MockMvcPostHelper {

    private MockMvcPostHelper() {
    }

    public static MockHttpServletRequestBuilder postObject(String urlTemplate, Object obj)
            throws JsonProcessingException {
        return post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJsonConverter.objectToJson(obj))
                .characterEncoding("UTF-8");
    }
}
