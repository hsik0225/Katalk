package com.naru.katalk.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ObjectToJsonConverter() {
    }

    public static String objectToJson(Object obj) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
}
