package com.tecdes.smart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tecdes.smart.dto.BancadaTesteDTO;
import com.tecdes.smart.model.BancadaTeste;
import com.tecdes.smart.service.BancadaTesteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bancadaTeste")
@RequiredArgsConstructor 
public class BancadaTesteController {


    private final BancadaTesteService bancadaTesteService;
    
    @PostMapping("/salvar")
    public ResponseEntity<BancadaTesteDTO> criarBancada(@RequestBody BancadaTesteDTO bancadaTesteDTO) {

        BancadaTesteDTO bancadaCriada = bancadaTesteService.criarBancada(bancadaTesteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bancadaCriada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<BancadaTesteDTO>> listarBancadas() {
        List<BancadaTesteDTO> bancadas = bancadaTesteService.listarBancadas();
        return bancadas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bancadas);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<BancadaTesteDTO> atualizarBancada(@PathVariable Long id, @RequestBody BancadaTesteDTO bancadaTesteDTO) {
        return ResponseEntity.ok(bancadaTesteService.atualizarBancadaPut(id, bancadaTesteDTO));
    }
    
    @PatchMapping("/patch/{id}")
    public ResponseEntity<BancadaTesteDTO> atualizarBancadaPatch(@PathVariable Long id, @RequestBody BancadaTesteDTO bancadaTesteDTO) {
        return ResponseEntity.ok(bancadaTesteService.atualizarBancadaPatch(id, bancadaTesteDTO));
    }
    // DELETE: Remover um registro [cite: 260]
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarBancada(@PathVariable Long id) {
        bancadaTesteService.excluirBancada(id);
        return ResponseEntity.noContent().build();
    }
}

