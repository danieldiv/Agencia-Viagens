/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.nicetrip.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class People implements Serializable{

    private int id_pessoa;
    private People responsavel;
    private String nome;
    private String cpf;
    private String rg;
    private String certidaoNasc;
    private Date dataNasc;
    private Date dataCad;

    public People() {
    }

    public People(int id_pessoa, People responsavel, String nome, String cpf, String rg, String certidaoNasc, Date dataNasc, Date dataCad) {
        this.id_pessoa = id_pessoa;
        this.responsavel = responsavel;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.certidaoNasc = certidaoNasc;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
    }

    public People(People responsavel, String nome, String cpf, String rg, String certidaoNasc, Date dataNasc, Timestamp dataCad) {
        this.responsavel = responsavel;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.certidaoNasc = certidaoNasc;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public People getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(People responsavel) {
        this.responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCertidaoNasc() {
        return certidaoNasc;
    }

    public void setCertidaoNasc(String certidaoNasc) {
        this.certidaoNasc = certidaoNasc;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getDataCad() {
        return dataCad;
    }

    public void setDataCad(Date dataCad) {
        this.dataCad = dataCad;
    }

    @Override
    public String toString() {
        return "People{" + "id_pessoa=" + id_pessoa + ", responsavel=" + responsavel + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", certidaoNasc=" + certidaoNasc + ", dataNasc=" + dataNasc + ", dataCad=" + dataCad + '}';
    }

}
