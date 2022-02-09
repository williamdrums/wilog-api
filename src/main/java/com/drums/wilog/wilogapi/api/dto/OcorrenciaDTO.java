package com.drums.wilog.wilogapi.api.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OcorrenciaDTO {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
