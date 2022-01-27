package com.drums.wilog.wilogapi.domian.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Destinatario {

    @Column(name = "destinatario_nome")
    private String nome;
    @Column(name = "destinatario_logradouro")
    private String logradouro;
    @Column(name = "destinatario_numero")
    private String numero;
    @Column(name = "destinatario_complemento")
    private String complemento;
    @Column(name = "destinatario_bairro")
    private String bairro;
}