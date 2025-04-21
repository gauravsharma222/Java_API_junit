package com.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJsonFile(String path, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(path), clazz);
    }
}
