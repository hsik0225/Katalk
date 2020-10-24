package com.naru.katalk.util;

import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

public class FieldDescriptorHelper {

    public static FieldDescriptor getDescriptor(String path, String description) {
        return getDescriptor(path, description, "");
    }

    public static FieldDescriptor getDescriptor(String path, String description,
                                                String constraint) {
        return fieldWithPath(path).type(JsonFieldType.STRING)
                .description(description)
                .attributes(key("constraint").value(constraint));
    }

    public static <T> FieldDescriptor getDescriptor(String path, String description,
                                                    Class<T> clazz) {

        String[] properties = path.split("\\.");
        String property = properties[properties.length - 1];

        ConstraintDescriptions descriptions = new ConstraintDescriptions(clazz);

        // collectionToDelimitedString : 콜렉션을 delimiter 로 연결한다
        String constraintMessages =
                collectionToDelimitedString(descriptions.descriptionsForProperty(property), ". ");

        return getDescriptor(path, description, constraintMessages);
    }
}
