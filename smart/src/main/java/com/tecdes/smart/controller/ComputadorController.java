package com.tecdes.smart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecdes.smart.dto.ComputadorDTO;
import com.tecdes.smart.service.ComputadorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/computadores")
@RequiredArgsConstructor 

public class ComputadorController {



    private final ComputadorService computadorService;
    
    @PostMapping("/salvar")
    public ResponseEntity<ComputadorDTO> criarBancada(@RequestBody ComputadorDTO computadorDTO) {

        ComputadorDTO computadorCriado = computadorService.criarComputadores(computadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(computadorCriado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ComputadorDTO>> listarComputadores() {
        List<ComputadorDTO> computadores = computadorService.listarComputadores();
        return computadores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(computadores);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ComputadorDTO> atualizarComputador(@PathVariable Long id, @RequestBody ComputadorDTO computadorDTO) {
        return ResponseEntity.ok(computadorService.atualizarComputadorPut(id, computadorDTO));
    }
    

    // DELETE: Remover um registro [cite: 260]
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarComputador(@PathVariable Long id) {
        computadorService.excluirComputador(id);
        return ResponseEntity.noContent().build();
    }
}


    
