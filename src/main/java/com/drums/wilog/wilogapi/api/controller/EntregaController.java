package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.api.dto.EntregaDTO;
import com.drums.wilog.wilogapi.api.mapper.EntregaMapper;
import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.repository.EntregaRepository;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO creteDelivery(@Valid @RequestBody Entrega entrega) {
        Entrega entregaSolicitada = solicitacaoEntregaService.createDelivery(entrega);
        return entregaMapper.toModel(entregaSolicitada);
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
