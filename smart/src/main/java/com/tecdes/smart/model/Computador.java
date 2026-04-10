package com.tecdes.smart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "computador")
public class Computador {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer numero;

    @OneToOne
    @JoinColumn(name = "venda_id", unique = true)
    private Venda venda;

    @OneToOne
    @JoinColumn(unique = true)
    private String  CodigoVenda;
    public Computador() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero){ this.numero = numero ;}

    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }

    
    public CodigoVenda getCodigoVenda() { return CodigoVenda; }
    public void setCodigoVenda(CodigoVenda CodigoVenda) { this.CodigoVenda = CodigoVenda; }

}



