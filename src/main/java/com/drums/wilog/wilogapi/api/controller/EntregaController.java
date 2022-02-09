package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.api.dto.EntregaDTO;
import com.drums.wilog.wilogapi.api.mapper.EntregaMapper;
import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.model.input.EntregaInput;
import com.drums.wilog.wilogapi.domian.repository.EntregaRepository;
import com.drums.wilog.wilogapi.domian.service.FinalizacaoEntregaService;
import com.drums.wilog.wilogapi.domian.service.SolicitacaoEntregaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {


    @Autowired
    private EntregaMapper entregaMapper;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @Autowired
    private FinalizacaoEntregaService finalizacaoEntregaService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO creteDelivery(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaMapper.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.createDelivery(novaEntrega);

        return entregaMapper.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalization(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizationDelivery(entregaId);
    }

    @GetMapping
    public List<EntregaDTO> findAll() {
        return entregaMapper.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> find(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega))).orElse(ResponseEntity.notFound().build());
    }
}
