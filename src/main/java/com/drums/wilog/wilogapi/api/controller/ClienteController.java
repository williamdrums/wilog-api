package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.domian.model.Cliente;
import com.drums.wilog.wilogapi.domian.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Cliente> findById(@PathVariable Long clientId) {
        Optional<Cliente> client = clienteService.findById(clientId);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@Valid @RequestBody Cliente client) {
        return clienteService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Cliente> update(@Valid @PathVariable Long clientId, @RequestBody Cliente client) {

        if (!clienteService.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
        Cliente clientUpdate = clienteService.save(client);

        return ResponseEntity.ok(clientUpdate);
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {

        if (!clienteService.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        clienteService.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
