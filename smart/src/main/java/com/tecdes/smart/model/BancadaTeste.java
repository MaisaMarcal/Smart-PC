package com.tecdes.smart.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bancadas")
public class BancadaTeste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private LocalDateTime dataHoraSaida;
    private LocalDateTime dataHoraEntrada;

    @Column(unique = true)
    private Integer numero;

    @OneToOne
    @JoinColumn(name = "venda_id", unique = true)
    private Venda venda;

    public BancadaTeste() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHoraEntrada() { return dataHoraEntrada; }
    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) { this.dataHoraEntrada = dataHoraEntrada; }
    public LocalDateTime getDataHoraSaida() { return dataHoraSaida; }
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) { this.dataHoraSaida = dataHoraSaida; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero){ this.numero = numero ;}

    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }

}

