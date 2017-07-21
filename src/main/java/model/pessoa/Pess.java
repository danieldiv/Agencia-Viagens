/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pessoa;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Pess implements Serializable {

    private int id_pessoa;
    private Pess responsavel;
    private String nome;
    private String cpf;
    private String rg;
    private String certidaNasc;
    private Date dataNasc;
    private Date dataCad;

    public Pess() {
    }

    public Pess(int id_pessoa, Pess responsavel, String nome, String cpf, String rg, String certidaNasc, Date dataNasc, Date dataCad) {
        this.id_pessoa = id_pessoa;
        this.responsavel = responsavel;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.certidaNasc = certidaNasc;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
    }

    public Pess(Pess responsavel, String nome, String cpf, String rg, String certidaNasc, Date dataNasc, Date dataCad) {
        this.responsavel = responsavel;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.certidaNasc = certidaNasc;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public Pess getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pess responsavel) {
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

    public String getCertidaNasc() {
        return certidaNasc;
    }

    public void setCertidaNasc(String certidaNasc) {
        this.certidaNasc = certidaNasc;
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
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id_pessoa;
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
        final Pess other = (Pess) obj;
        if (this.id_pessoa != other.id_pessoa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoas{" + "id_pessoa=" + id_pessoa + ", responsavel=" + responsavel + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", certidaNasc=" + certidaNasc + ", dataNasc=" + dataNasc + ", dataCad=" + dataCad + '}';
    }

}
