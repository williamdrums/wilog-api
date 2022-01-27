package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.domian.model.Entrega;
import com.drums.wilog.wilogapi.domian.repository.EntregaRepository;
import com.drums.wilog.wilogapi.domian.service.SolicitacaoEntregaService;
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
    private EntregaRepository entregaRepository;

    @Autowired
    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega creteDelivery(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.createDelivery(entrega);
    }

    @GetMapping
    public List<Entrega> findAll() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> find(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
