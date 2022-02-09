package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.api.dto.OcorrenciaDTO;
import com.drums.wilog.wilogapi.api.mapper.OcorrenciaMapper;
import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.model.Ocorrencia;
import com.drums.wilog.wilogapi.domian.model.input.OcorrenciaInput;
import com.drums.wilog.wilogapi.domian.service.BuscaEntregaService;
import com.drums.wilog.wilogapi.domian.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/entregas/{entregaId}/ocorrencias")
@RestController
public class OcorrenciaController {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO register(
            @PathVariable Long entregaId,
            @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
                .register(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaDTO> findAll(@PathVariable Long entregaId){
        Entrega entrega = buscaEntregaService.find(entregaId);

        return ocorrenciaMapper.toCollectionModel(entrega.getOcorrencias());
    }
}
