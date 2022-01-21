package com.drums.wilog.wilogapi.domian.service;

import com.drums.wilog.wilogapi.domian.exception.BusinessRuleException;
import com.drums.wilog.wilogapi.domian.model.Client;
import com.drums.wilog.wilogapi.domian.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long clientId){
        return clientRepository.findById(clientId);
    }

    @Transactional
    public Client save(Client client){
      boolean emailInUse =  clientRepository.findByEmail(client.getEmail())
              .stream().anyMatch(existingCustomer -> !existingCustomer.equals(client));
      if (emailInUse){
          throw new BusinessRuleException("Já existe um cliente cadastrado com este e-mail.");
      }
      return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long clientId){
        clientRepository.deleteById(clientId);
    }

    public boolean existsById(Long clientId){
       return clientRepository.existsById(clientId);
    }
}
