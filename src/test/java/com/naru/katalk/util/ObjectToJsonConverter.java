package com.naru.katalk.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectToJsonConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Request & Response 의 JSON 형식을 깔끔하게 정리된 형태로 출력한다
    static {
        OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    private ObjectToJsonConverter() {}

    public static String objectToJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
}
