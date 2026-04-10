package com.tecdes.smart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecdes.smart.dto.ComputadorDTO;
import com.tecdes.smart.model.Computador;
import com.tecdes.smart.model.Venda;
import com.tecdes.smart.repository.ComputadorRepository;
import com.tecdes.smart.repository.VendaRepository;

@Service
public class ComputadorService {
    
    private final ComputadorRepository computadorRepository;
    private final VendaRepository vendaRepository;

    public ComputadorService(ComputadorRepository pc, VendaRepository vd) {
        this.computadorRepository = pc;
        this.vendaRepository = vd;
    }

  
    public ComputadorDTO criarComputadores(ComputadorDTO dto) {


        if (dto.vendaId() != null && computadorRepository.existsByVendaId(dto.vendaId())) {
            throw new RuntimeException("Venda já está em uma bancada");
        }

        ComputadorRepository computador = new Computador();

        computador.setNumero(dto.numero());


        if (dto.vendaId() != null) {
            Venda venda = vendaRepository.findById(dto.vendaId())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

            computador.setVenda(venda);
        }

        Computador salva = computadorRepository.save(computador);

        return converterParaDTO(salva);
    }

    public List<ComputadorDTO> listarComputadores() {
        return computadorRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }


    public ComputadorDTO buscarPorId(Long id) {
        Computador computador = computadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bancada não encontrada"));

        return converterParaDTO(computador);
    }

    public ComputadorDTO atualizarComputadorPut(Long id, ComputadorDTO dto) {

        Computador computador = computadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bancada não encontrada"));

        if (dto.vendaId() != null &&
           computadorRepository.existsByVendaId(dto.vendaId()) &&
            (computador.getVenda() == null || !computador.getVenda().getId().equals(dto.vendaId()))) {

            throw new RuntimeException("Venda já está em outra bancada");
        }

        computador.setNumero(dto.numero());

        if (dto.vendaId() != null) {
            Venda venda = vendaRepository.findById(dto.vendaId())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

            computador.setVenda(venda);
        } else {
            computador.setVenda(null); // libera a bancada
        }

        return converterParaDTO(computadorRepository.save(computador));
    }

    public void excluirComputador(Long id) {
       computadorRepository.deleteById(id);
    }

    private ComputadorDTO converterParaDTO(Computador pc) {
        return new ComputadorDTO(
                pc.getId(),
                pc.getNumero(),
                pc.getVenda() != null ? pc.getVenda().getId() : null,
                pc.getCodigoVenda()
        );
    }
}

