package com.s0qva.easypunishment.client.util.basic;

import com.s0qva.easypunishment.client.exception.ClassMismatchException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ReflectionUtil {
    private static final Logger LOGGER = LogManager.getLogger(ReflectionUtil.class);

    private ReflectionUtil() {
    }

    public static <T> Object obtainObject(T object, String fieldName) {
        try {
            Field foundField = object.getClass().getDeclaredField(fieldName);

            allowAccess(foundField);

            return foundField.get(object);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            LOGGER.error("An exception occurred: {}", exception.getMessage());
            return null;
        }
    }

    public static <T, FT> Object obtainObject(T object, Class<FT> desiredFieldTypeClass, String fieldName) {
        try {
            Field foundField = object.getClass().getDeclaredField(fieldName);

            isMatchOrElseThrow(foundField, desiredFieldTypeClass);
            allowAccess(foundField);

            return foundField.get(object);
        } catch (NoSuchFieldException
                 | IllegalAccessException
                 | IllegalArgumentException
                 | NullPointerException exception) {
            LOGGER.error("An exception occurred: {}", exception.getMessage());
            return null;
        }
    }

    public static <T, ET> List<ET> obtainList(T object, Class<ET> listElementTypeClass, String fieldName) {
        Object obtainedObject = obtainObject(object, List.class, fieldName);

        if (Objects.isNull(obtainedObject)) {
            return null;
        }
        List<?> foundList = (List<?>) obtainedObject;
        List<ET> desiredList = new ArrayList<>();

        try {
            for (Object foundListElement : foundList) {
                desiredList.add(listElementTypeClass.cast(foundListElement));
            }
            return desiredList;
        } catch (ClassCastException exception) {
            LOGGER.error("An exception occurred: {}", exception.getMessage());
            return null;
        }
    }

    public static <T> Integer obtainInteger(T object, String fieldName) {
        Object obtainedObject = obtainObject(object, int.class, fieldName);

        if (Objects.isNull(obtainedObject)) {
            return null;
        }
        try {
            return (Integer) obtainedObject;
        } catch (ClassCastException exception) {
            LOGGER.error("An exception occurred: {}", exception.getMessage());
            return null;
        }
    }

    public static <T, VT> void setPrivateFinalValue(T object, String fieldName, VT value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);

            allowAccess(field);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            LOGGER.error("An exception occurred: {}", exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public static void isMatchOrElseThrow(Class<?> firstClass, Class<?> secondClass, @Nullable String exceptionMessage) {
        if (isMatch(firstClass, secondClass)) {
            return;
        }
        if (StringUtils.isNotBlank(exceptionMessage)) {
            throw new ClassMismatchException(exceptionMessage);
        }
        throw new ClassMismatchException();
    }

    public static void isMatchOrElseThrow(Class<?> firstClass, Class<?> secondClass) {
        isMatchOrElseThrow(firstClass, secondClass, StringUtils.EMPTY);
    }

    public static void isMatchOrElseThrow(Field field, Class<?> classToMatch, String exceptionMessage) {
        isMatchOrElseThrow(field.getType(), classToMatch, exceptionMessage);
    }

    public static void isMatchOrElseThrow(Field field, Class<?> classToMatch) {
        isMatchOrElseThrow(field, classToMatch, StringUtils.EMPTY);
    }

    public static boolean isMatch(Class<?> firstClass, Class<?> secondClass) {
        return firstClass.equals(secondClass);
    }

    public static boolean isMatch(Field field, Class<?> classToMatch) {
        return isMatch(field.getType(), classToMatch);
    }

    public static void allowAccess(Field field) {
        field.setAccessible(true);
    }

    public static void restrictAccess(Field field) {
        field.setAccessible(false);
    }
}
