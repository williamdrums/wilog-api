package com.drums.wilog.wilogapi.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Problem {
    private Integer status;
    private LocalDateTime dateTime;
    private String title;
    private List<Field> fields;

    @AllArgsConstructor
    @Data
    public static class Field{
        private String name;
        private String message;
    }
}
