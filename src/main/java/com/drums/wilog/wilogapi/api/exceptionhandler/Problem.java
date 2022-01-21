package com.drums.wilog.wilogapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
