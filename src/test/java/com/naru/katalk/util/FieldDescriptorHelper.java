package com.naru.katalk.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.restdocs.constraints.Constraint;
import org.springframework.restdocs.constraints.ConstraintDescriptionResolver;
import org.springframework.restdocs.constraints.ResourceBundleConstraintDescriptionResolver;
import org.springframework.restdocs.constraints.ValidatorConstraintResolver;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

public class FieldDescriptorHelper {

    private static final ValidatorConstraintResolver constraintResolver
            = new ValidatorConstraintResolver();

    private static final ConstraintDescriptionResolver descriptionResolver =
            new ResourceBundleConstraintDescriptionResolver();

    private static final List<String> descriptions = new ArrayList<>();

    public static final String MESSAGE_REG = "^.*[가-힣]+.*$";

    public static final String DELIMETER = ". ";

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
        String constraintMessages = "";

        List<Constraint> constraints
                = constraintResolver.resolveForProperty(property, clazz);

        // 제약 조건을 가지고 있지 않을 수 있으므로 if 문으로 한 번 체크합니다
        if (!constraints.isEmpty()) {

            for (Constraint constraint : constraints) {
                String message = getConstraintMessage(constraint);

                // 제약 조건에서 제약 조건 메세지를 가져온다
                descriptions.add(message + descriptionResolver.resolveDescription(constraint));
            }

            // collectionToDelimitedString : 컬렉션을 delimiter 로 연결합니다
            constraintMessages = collectionToDelimitedString(descriptions, DELIMETER);

            // 저장한 값 삭제
            descriptions.clear();
        }

        return getDescriptor(path, description, constraintMessages);
    }

    private static String getConstraintMessage(Constraint constraint) {
        String message = constraint.getConfiguration().get("message") + " ";

        // 한글이 한 글자도 포함되어 있으면 메세지를 추가, 아니라면 빈 문자열을 반환
        return message.matches(MESSAGE_REG) ? message : "";
    }
}
