package com.drums.wilog.wilogapi.api.controller;

import com.drums.wilog.wilogapi.domian.model.Client;
import com.drums.wilog.wilogapi.domian.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findById(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@Valid @PathVariable Long clientId, @RequestBody Client client) {

        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(clientId);
        Client clientUpdate = clientRepository.save(client);

        return ResponseEntity.ok(clientUpdate);
    }

    @DeleteMapping("{clientId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId) {

        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.deleteById(clientId);
        return ResponseEntity.noContent().build();
    }
}
