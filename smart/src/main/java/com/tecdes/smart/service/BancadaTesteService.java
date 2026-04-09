package com.tecdes.smart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tecdes.smart.dto.BancadaTesteDTO;
import com.tecdes.smart.model.BancadaTeste;
import com.tecdes.smart.model.Venda;
import com.tecdes.smart.repository.BancadaTesteRepository;
import com.tecdes.smart.repository.VendaRepository;

@Service
public class BancadaTesteService {

    private final BancadaTesteRepository bancadaTesteRepository;
    private final VendaRepository vendaRepository;

    public BancadaTesteService(BancadaTesteRepository bt, VendaRepository vd) {
        this.bancadaTesteRepository = bt;
        this.vendaRepository = vd;
    }

  
    public BancadaTesteDTO criarBancada(BancadaTesteDTO dto) {


        if (dto.vendaId() != null && bancadaTesteRepository.existsByVendaId(dto.vendaId())) {
            throw new RuntimeException("Venda já está em uma bancada");
        }

        BancadaTeste bancada = new BancadaTeste();

        bancada.setNumero(dto.numero());
        bancada.setDataHoraEntrada(dto.dataHoraEntrada());
        bancada.setDataHoraSaida(dto.dataHoraSaida());

        if (dto.vendaId() != null) {
            Venda venda = vendaRepository.findById(dto.vendaId())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

            bancada.setVenda(venda);
        }

        BancadaTeste salva = bancadaTesteRepository.save(bancada);

        return converterParaDTO(salva);
    }

    public List<BancadaTesteDTO> listarBancadas() {
        return bancadaTesteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }


    public BancadaTesteDTO buscarPorId(Long id) {
        BancadaTeste bancada = bancadaTesteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bancada não encontrada"));

        return converterParaDTO(bancada);
    }

    public BancadaTesteDTO atualizarBancadaPut(Long id, BancadaTesteDTO dto) {

        BancadaTeste bancada = bancadaTesteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bancada não encontrada"));

        if (dto.vendaId() != null &&
            bancadaTesteRepository.existsByVendaId(dto.vendaId()) &&
            (bancada.getVenda() == null || !bancada.getVenda().getId().equals(dto.vendaId()))) {

            throw new RuntimeException("Venda já está em outra bancada");
        }

        bancada.setNumero(dto.numero());
        bancada.setDataHoraEntrada(dto.dataHoraEntrada());
        bancada.setDataHoraSaida(dto.dataHoraSaida());

        if (dto.vendaId() != null) {
            Venda venda = vendaRepository.findById(dto.vendaId())
                    .orElseThrow(() -> new RuntimeException("Venda não encontrada"));

            bancada.setVenda(venda);
        } else {
            bancada.setVenda(null); // libera a bancada
        }

        return converterParaDTO(bancadaTesteRepository.save(bancada));
    }

    public void excluirBancada(Long id) {
        bancadaTesteRepository.deleteById(id);
    }

    private BancadaTesteDTO converterParaDTO(BancadaTeste bt) {
        return new BancadaTesteDTO(
                bt.getId(),
                bt.getNumero(),
                bt.getDataHoraEntrada(),
                bt.getDataHoraSaida(),
                bt.getVenda() != null ? bt.getVenda().getId() : null
        );
    }
}