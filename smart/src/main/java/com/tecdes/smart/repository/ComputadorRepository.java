package com.tecdes.smart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.smart.model.Computador;

public interface ComputadorRepository extends JpaRepository<Computador, Long> {

    List<Computador> findByVendaId(Long vendaId);

    boolean existsByVendaId(Long vendaId);
    
}
    



