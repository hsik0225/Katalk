package com.naru.katalk.util;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

public class FieldDescriptorHelper {

    public static FieldDescriptor getFieldDescriptor(String path, String description,
                                                     boolean isOptional) {
        FieldDescriptor fieldDescriptor = fieldWithPath(path).type(JsonFieldType.STRING)
                .description(description)
                .attributes(key("constraint").value(""));

        return isOptional ? fieldDescriptor.optional() : fieldDescriptor;
    }

    public static <T> FieldDescriptor getStringFieldDescriptor(String path, String description,
                                                               Class<T> clazz) {
        String property = path.contains("\\.") ? path.split("\\.")[1] : path;
        ConstraintDescriptions descriptions = new ConstraintDescriptions(clazz);

        // collectionToDelimitedString : 콜렉션을 delimiter 로 연결한다
        String constraintMessages =
                collectionToDelimitedString(descriptions.descriptionsForProperty(property), ".");

        return fieldWithPath(path).type(JsonFieldType.STRING)
                .description(description)
                .attributes(key("constraint").value(constraintMessages));
    }
}
