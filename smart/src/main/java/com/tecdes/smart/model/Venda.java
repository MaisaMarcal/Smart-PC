package com.tecdes.smart.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String codigoVenda;

    private Integer status;

    @OneToMany(mappedBy = "venda")
    private List<Computador> computadores;

    public Venda() {}

    public Long getId() { return id; }

    public String getCodigoVenda() { return codigoVenda; }
    public void setCodigoVenda(String codigoVenda) { this.codigoVenda = codigoVenda; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public List<Computador> getComputadores() { return computadores; }
    public void setComputadores(List<Computador> computadores) { this.computadores = computadores; }
}