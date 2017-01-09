/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.empresa;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Viagem {

    private int id_viagem;
    private Carro carro;
    private String localHorigem;
    private String localDestino;
    private Date dataSaida;
    private Date dataChegada;
    private Date dataCriacao;
    private Date dataModificacao;
    private double valorPassagem;
    private int assLivres;

    public Viagem() {
    }

    public Viagem(Carro carro, String localHorigem, String localDestino, Date dataSaida, Date dataChegada, Date dataCriacao, Date dataModificacao, double valorPassagem, int assLivres) {
        this.carro = carro;
        this.localHorigem = localHorigem;
        this.localDestino = localDestino;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.valorPassagem = valorPassagem;
        this.assLivres = assLivres;
    }

    public Viagem(int id_viagem, Carro carro, String localHorigem, String localDestino, Date dataSaida, Date dataChegada, Date dataCriacao, Date dataModificacao, double valorPassagem, int assLivres) {
        this.id_viagem = id_viagem;
        this.carro = carro;
        this.localHorigem = localHorigem;
        this.localDestino = localDestino;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.valorPassagem = valorPassagem;
        this.assLivres = assLivres;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(int id_viagem) {
        this.id_viagem = id_viagem;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public String getLocalHorigem() {
        return localHorigem;
    }

    public void setLocalHorigem(String localHorigem) {
        this.localHorigem = localHorigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(double valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public int getAssLivres() {
        return assLivres;
    }

    public void setAssLivres(int assLivres) {
        this.assLivres = assLivres;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id_viagem;
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
        final Viagem other = (Viagem) obj;
        if (this.id_viagem != other.id_viagem) {
            return false;
        }
        return true;
    }

}
