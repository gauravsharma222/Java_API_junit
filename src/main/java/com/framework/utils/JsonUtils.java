package com.framework.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.tests.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJsonFile(String path, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(path), clazz);
    }
    public static <T> List<T> readJsonList(String filePath, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        return mapper.readValue(new File(filePath), type);
    }

    public static <T> String convertObjectToJson(T object) throws IOException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

}
