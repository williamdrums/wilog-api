package com.drums.wilog.wilogapi.domian.repository;

import com.drums.wilog.wilogapi.domian.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

//    List<Cliente> findByName(String name);
//    List<Cliente> findByNameContaining(String name);
    Optional<Cliente> findByEmail(String email);

}
