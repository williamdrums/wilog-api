package com.drums.wilog.wilogapi.domian.model;

import com.drums.wilog.wilogapi.domian.ValidationGroups;
import com.drums.wilog.wilogapi.domian.exception.RegraNegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;


    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new RegraNegocioException("Entrega não pode ser finalizada");
        }
        setStatusEntrega(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatusEntrega());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }
}
