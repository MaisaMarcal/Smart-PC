package com.tecdes.smart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.smart.model.Prateleira;

public interface PrateleiraRepository extends JpaRepository<Prateleira, Long> {


    Optional<Prateleira> findByNumero(Integer numero);
}
