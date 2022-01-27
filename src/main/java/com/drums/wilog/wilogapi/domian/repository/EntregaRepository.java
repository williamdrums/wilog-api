package com.drums.wilog.wilogapi.domian.repository;

import com.drums.wilog.wilogapi.domian.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository  extends JpaRepository<Entrega,Long> {
}
