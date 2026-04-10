package com.tecdes.smart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.smart.model.Acessorio;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {

    List<Acessorio> findByComputadorId(Long computadorId);
}