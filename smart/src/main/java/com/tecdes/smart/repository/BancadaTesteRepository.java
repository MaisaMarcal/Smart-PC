package com.tecdes.smart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.smart.model.BancadaTeste;

public interface BancadaTesteRepository  extends JpaRepository<BancadaTeste, Long> {
   
    List<BancadaTeste> findByVendaId(Long vendaId);

    boolean existsByVendaId(Long vendaId);
    
}
    

