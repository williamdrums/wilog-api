package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.api.dto.EntregaDTO;
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
    private ModelMapper modelMapper;

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
    public ResponseEntity<EntregaDTO> find(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega -> {
                    EntregaDTO entregaDTO = modelMapper.map(entrega, EntregaDTO.class);
                    return ResponseEntity.ok(entregaDTO);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
