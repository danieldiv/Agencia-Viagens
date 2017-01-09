/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.empresa;

import java.util.Date;
import model.pessoa.Pessoa;

/**
 *
 * @author Daniel
 */
public class Pagamento {

    //pagamento
    private int id_pagamento;
    private Viagem viagem;
    private Pessoa pessoa;
    private Pagamento pagamento;
    private Date dataValidade;//ambos
    private double valorTotal;
    private double valorRestante;
    private int parcelas;

    //log de pagamento
    private double valorPago;
    private double valorParcela;
    private Date dataPagamento;
    private int parcelaAtual;

    //<editor-fold defaultstate="collapsed" desc="Cosntrutores">
    public Pagamento() {
    }

    /**
     * Construtor para salvar pagamento
     *
     * @param viagem
     * @param pessoa
     * @param pagamento
     * @param dataValidade
     * @param valorTotal
     * @param valorRestante
     * @param parcelas
     */
    public Pagamento(Viagem viagem, Pessoa pessoa, Pagamento pagamento, Date dataValidade, double valorTotal, double valorRestante, int parcelas) {
        this.viagem = viagem;
        this.pessoa = pessoa;
        this.pagamento = pagamento;
        this.dataValidade = dataValidade;
        this.valorTotal = valorTotal;
        this.valorRestante = valorRestante;
        this.parcelas = parcelas;
    }

    public Pagamento(int id_pagamento, Viagem viagem, Pessoa pessoa, Pagamento pagamento, Date dataValidade, double valorTotal, double valorRestante, int parcelas) {
        this.id_pagamento = id_pagamento;
        this.viagem = viagem;
        this.pessoa = pessoa;
        this.pagamento = pagamento;
        this.dataValidade = dataValidade;
        this.valorTotal = valorTotal;
        this.valorRestante = valorRestante;
        this.parcelas = parcelas;
    }

    /**
     * Contrutor para salvar logs de pagamento
     *
     * @param dataValidade
     * @param parcelas
     * @param valorPago
     * @param valorParcela
     * @param dataPagamento
     * @param parcelaAtual
     */
    public Pagamento(Date dataValidade, int parcelas, double valorPago, double valorParcela, Date dataPagamento, int parcelaAtual) {
        this.dataValidade = dataValidade;
        this.valorPago = valorPago;
        this.valorParcela = valorParcela;
        this.dataPagamento = dataPagamento;
        this.parcelaAtual = parcelaAtual;
    }

    public Pagamento(int id_pagamento, Date dataValidade, int parcelas, double valorPago, double valorParcela, Date dataPagamento, int parcelaAtual) {
        this.id_pagamento = id_pagamento;
        this.dataValidade = dataValidade;
        this.valorPago = valorPago;
        this.valorParcela = valorParcela;
        this.dataPagamento = dataPagamento;
        this.parcelaAtual = parcelaAtual;
    }
//</editor-fold>

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorRestante() {
        return valorRestante;
    }

    public void setValorRestante(double valorRestante) {
        this.valorRestante = valorRestante;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getParcelaAtual() {
        return parcelaAtual;
    }

    public void setParcelaAtual(int parcelaAtual) {
        this.parcelaAtual = parcelaAtual;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id_pagamento;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pagamento other = (Pagamento) obj;
        if (this.id_pagamento != other.id_pagamento) {
            return false;
        }
        return true;
    }

}
