package com.drums.wilog.wilogapi.domian.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClienteIdInput {

    @NotNull
    private Long id;

}
