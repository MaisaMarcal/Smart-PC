package com.tecdes.smart.dto;

import java.util.List;

public record VendaDTO(
    Long id,
    String codigoVenda,
    Integer status,
    List<Long> computadoresIds
) {}