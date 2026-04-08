package com.tecdes.smart.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record BancadaTesteDTO(
    Long id,
    Integer numero,
    Long vendaId,
    String codigoVenda,
    LocalDateTime dataHoraEntrada,
    LocalDateTime dataHoraSaida
) {}

