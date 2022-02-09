package com.drums.wilog.wilogapi.domian.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OcorrenciaInput {

    @NotBlank
    private String descricao;
}
