package com.drums.wilog.wilogapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Problema {
    private Integer status;
    private OffsetDateTime dateHora;
    private String titulo;
    private List<Campo> campos;

    @AllArgsConstructor
    @Data
    public static class Campo {
        private String nome;
        private String message;
    }
}
