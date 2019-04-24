package com.car.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static String objToJsonStr(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String val = null;
        try {
            val = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static <T> T jsonStrToObj(String jsonStr, Class<T> tClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

