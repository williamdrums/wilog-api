package com.drums.wilog.wilogapi.domian.service;

import com.drums.wilog.wilogapi.domian.exception.RegraNegocioException;
import com.drums.wilog.wilogapi.domian.model.Cliente;
import com.drums.wilog.wilogapi.domian.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long clientId){
        return clienteRepository.findById(clientId);
    }

    public Cliente find(Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));
        return  cliente;
    }
    @Transactional
    public Cliente save(Cliente cliente){
      boolean emailEmUso =  clienteRepository.findByEmail(cliente.getEmail())
              .stream().anyMatch(existingCustomer -> !existingCustomer.equals(cliente));
      if (emailEmUso){
          throw new RegraNegocioException("Já existe um cliente cadastrado com este e-mail.");
      }
      return clienteRepository.save(cliente);
    }

    @Transactional
    public void delete(Long clientId){
        clienteRepository.deleteById(clientId);
    }

    public boolean existsById(Long clientId){
       return clienteRepository.existsById(clientId);
    }
}
