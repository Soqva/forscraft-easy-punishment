package com.s0qva.easypunishment.client.util.basic;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
    public static final String EMPTY = StringUtils.EMPTY;
    public static final String DOT = "\\.";
    public static final String EQUAL_SIGN = "=";
    public static final String SPACE = " ";

    public static String replaceAll(String string, String regex, String replacement) {
        return StringUtils.replaceAll(string, regex, replacement);
    }

    public static String[] split(String string, String separator) {
        return StringUtils.split(string, separator);
    }

    public static String substring(String string, int startIndex, int endIndex) {
        if (endIndex == -1) {
            return StringUtils.substring(string, startIndex);
        }
        return StringUtils.substring(string, startIndex, endIndex);
    }

    public static boolean isBlank(String string) {
        return StringUtils.isBlank(string);
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}
