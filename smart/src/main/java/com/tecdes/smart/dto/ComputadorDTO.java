package com.tecdes.smart.dto;



public record ComputadorDTO(
    Long id,
    Integer numero,
    Long vendaId,
    String codigoVenda
) {}
