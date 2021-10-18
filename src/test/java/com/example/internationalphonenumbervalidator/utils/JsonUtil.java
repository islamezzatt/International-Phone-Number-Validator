package com.example.internationalphonenumbervalidator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonUtil {

    public static <T> T fromResourcePath(String path, Class<T> beanClass) throws IOException {
        return fromInputStream(JsonUtil.class.getResourceAsStream(path), beanClass);
    }

    public static <T> T fromInputStream(InputStream inputStream, Class<T> beanClass) throws IOException {
        String result = new BufferedReader(new InputStreamReader(inputStream)).lines()
                .collect(Collectors.joining("\n"));
        return fromJson(result, beanClass);
    }

    public static <T> T fromJson(String json, Class<T> beanClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, beanClass);
    }

    public static <T> String toJson(T bean) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(bean);
    }

    public static <T> List<T> fromResourcePathUsingListObjectMapper(String path, Class<T> tClass) {

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
            return objectMapper.readValue(JsonUtil.class.getResourceAsStream(path), listType);
        } catch (IOException e) {
            return null;
        }
    }

}
