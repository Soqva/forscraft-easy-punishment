package com.s0qva.easypunishment.client.util.basic;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public final class FileUtil {
    public static final String LINE_SEPARATOR = System.lineSeparator();

    private FileUtil() {
    }

    public static boolean exists(Path filePath) {
        return Files.exists(filePath);
    }

    public static boolean notExists(Path filePath) {
        return !exists(filePath);
    }

    public static void create(Path filePath) {
        try {
            FileUtils.touch(filePath.toFile());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Collection<String> readAll(Path filePath) {
        Collection<String> readData = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                readData.add(currentLine);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return readData;
    }

    public static void writeCollection(Path filePath, Collection<?> collection) {
        writeCollection(filePath, collection, StandardCharsets.UTF_8, true);
    }

    public static void writeCollection(Path filePath, Collection<?> collection, Charset encoding) {
        writeCollection(filePath, collection, encoding, true);
    }

    public static void writeCollection(Path filePath, Collection<?> collection, boolean shouldAppend) {
        writeCollection(filePath, collection, StandardCharsets.UTF_8, shouldAppend);
    }

    public static void writeCollection(Path filePath, Collection<?> collection,
                                                       Charset encoding, boolean shouldAppend) {
        for (Object element : collection) {
            write(filePath, element.toString(), encoding, shouldAppend);
        }
    }

    public static void write(Path filePath, String data) {
        write(filePath, data, StandardCharsets.UTF_8, true);
    }

    public static void write(Path filePath, String data, Charset encoding) {
        write(filePath, data, encoding, true);
    }

    public static void write(Path filePath, String data, boolean shouldAppend) {
        write(filePath, data, StandardCharsets.UTF_8, shouldAppend);
    }

    public static void write(Path filePath, String data, Charset encoding, boolean shouldAppend) {
        File file = filePath.toFile();

        try {
            FileUtils.write(file, data, encoding, shouldAppend);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void writeCollectionWithNewLineBefore(Path filePath, Collection<?> collection) {
        writeCollectionWithNewLineBefore(filePath, collection, StandardCharsets.UTF_8, true);
    }

    public static void writeCollectionWithNewLineBefore(Path filePath, Collection<?> collection, Charset encoding) {
        writeCollectionWithNewLineBefore(filePath, collection, encoding, true);
    }

    public static void writeCollectionWithNewLineBefore(Path filePath, Collection<?> collection, boolean shouldAppend) {
        writeCollectionWithNewLineBefore(filePath, collection, StandardCharsets.UTF_8, shouldAppend);
    }

    public static void writeCollectionWithNewLineBefore(Path filePath, Collection<?> collection,
                                                        Charset encoding, boolean shouldAppend) {
        for (Object element : collection) {
            writeWithNewLineBefore(filePath, element.toString(), encoding, shouldAppend);
        }
    }

    public static void writeWithNewLineBefore(Path filePath, String data) {
        writeWithNewLineBefore(filePath, data, StandardCharsets.UTF_8, true);
    }

    public static void writeWithNewLineBefore(Path filePath, String data, Charset encoding) {
        writeWithNewLineBefore(filePath, data, encoding, true);
    }

    public static void writeWithNewLineBefore(Path filePath, String data, boolean shouldAppend) {
        writeWithNewLineBefore(filePath, data, StandardCharsets.UTF_8, shouldAppend);
    }

    public static void writeWithNewLineBefore(Path filePath, String data, Charset encoding, boolean shouldAppend) {
        File file = filePath.toFile();

        try {
            FileUtils.write(file, LINE_SEPARATOR, encoding, shouldAppend);
            FileUtils.write(file, data, encoding, shouldAppend);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void writeCollectionWithNewLineAfter(Path filePath, Collection<?> collection) {
        writeCollectionWithNewLineAfter(filePath, collection, StandardCharsets.UTF_8, true);
    }

    public static void writeCollectionWithNewLineAfter(Path filePath, Collection<?> collection, Charset encoding) {
        writeCollectionWithNewLineAfter(filePath, collection, encoding, true);
    }

    public static void writeCollectionWithNewLineAfter(Path filePath, Collection<?> collection, boolean shouldAppend) {
        writeCollectionWithNewLineAfter(filePath, collection, StandardCharsets.UTF_8, shouldAppend);
    }

    public static void writeCollectionWithNewLineAfter(Path filePath, Collection<?> collection,
                                                        Charset encoding, boolean shouldAppend) {
        for (Object element : collection) {
            writeWithNewLineAfter(filePath, element.toString(), encoding, shouldAppend);
        }
    }

    public static void writeWithNewLineAfter(Path filePath, String data) {
        writeWithNewLineAfter(filePath, data, StandardCharsets.UTF_8, true);
    }

    public static void writeWithNewLineAfter(Path filePath, String data, Charset encoding) {
        writeWithNewLineAfter(filePath, data, encoding, true);
    }

    public static void writeWithNewLineAfter(Path filePath, String data, boolean shouldAppend) {
        writeWithNewLineAfter(filePath, data, StandardCharsets.UTF_8, shouldAppend);
    }

    public static void writeWithNewLineAfter(Path filePath, String data, Charset encoding, boolean shouldAppend) {
        File file = filePath.toFile();

        try {
            FileUtils.write(file, data, encoding, shouldAppend);
            FileUtils.write(file, LINE_SEPARATOR, encoding, shouldAppend);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
