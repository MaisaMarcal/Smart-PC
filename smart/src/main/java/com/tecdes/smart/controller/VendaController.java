package com.tecdes.smart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecdes.smart.dto.VendaDTO;
import com.tecdes.smart.service.VendaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaDTO> criar(@RequestBody VendaDTO dto) {
        VendaDTO criada = vendaService.criarVenda(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }


    @GetMapping
    public ResponseEntity<List<VendaDTO>> listar() {
        List<VendaDTO> lista = vendaService.listarVendas();

        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizar(@PathVariable Long id, @RequestBody VendaDTO dto) {
        return ResponseEntity.ok(vendaService.atualizarVendaPut(id, dto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarParcial(@PathVariable Long id, @RequestBody VendaDTO dto) {
        return ResponseEntity.ok(vendaService.atualizarVendaPatch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendaService.excluirVenda(id);
        return ResponseEntity.noContent().build();
    }
}