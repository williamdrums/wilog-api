package com.drums.wilog.wilogapi.api.dto;

import com.drums.wilog.wilogapi.domian.model.StatusEntrega;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class EntregaDTO {

    private Long id;
    private String nomeCliente;
    private DestinatarioDTO destinatario;
    private BigDecimal taxa;
    private StatusEntrega statusEntrega;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
