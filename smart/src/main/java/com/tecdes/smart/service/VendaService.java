package com.tecdes.smart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecdes.smart.dto.VendaDTO;
import com.tecdes.smart.model.Computador;
import com.tecdes.smart.model.Venda;
import com.tecdes.smart.repository.ComputadorRepository;
import com.tecdes.smart.repository.VendaRepository;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ComputadorRepository computadorRepository;

    public VendaService(VendaRepository vr, ComputadorRepository cr) {
        this.vendaRepository = vr;
        this.computadorRepository = cr;
    }

    public VendaDTO criarVenda(VendaDTO dto) {

        Venda venda = new Venda();
        venda.setCodigoVenda(dto.codigoVenda());
        venda.setStatus(dto.status());


        if (dto.computadoresIds() != null) {
            List<Computador> computadores = dto.computadoresIds().stream()
                .map(id -> computadorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Computador não encontrado")))
                .collect(Collectors.toList());

            venda.setComputadores(computadores);

            // importante: setar venda em cada computador
            computadores.forEach(c -> c.setVenda(venda));
        }

        return converterParaDTO(vendaRepository.save(venda));
    }

    public List<VendaDTO> listarVendas() {
        return vendaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public VendaDTO buscarPorId(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        return converterParaDTO(venda);
    }

    public VendaDTO atualizarVendaPut(Long id, VendaDTO dto) {

        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        venda.setCodigoVenda(dto.codigoVenda());
        venda.setStatus(dto.status());

        return converterParaDTO(vendaRepository.save(venda));
    }

    public VendaDTO atualizarVendaPatch(Long id, VendaDTO dto) {

        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

        if (dto.codigoVenda() != null) {
            venda.setCodigoVenda(dto.codigoVenda());
        }

        if (dto.status() != null) {
            venda.setStatus(dto.status());
        }

        return converterParaDTO(vendaRepository.save(venda));
    }


    public void excluirVenda(Long id) {
        vendaRepository.deleteById(id);
    }


    private VendaDTO converterParaDTO(Venda v) {
        List<Long> ids = v.getComputadores() != null
                ? v.getComputadores().stream().map(Computador::getId).toList()
                : null;

        return new VendaDTO(
                v.getId(),
                v.getCodigoVenda(),
                v.getStatus(),
                ids
        );
    }
}
