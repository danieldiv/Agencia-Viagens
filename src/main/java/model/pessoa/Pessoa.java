/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pessoa;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class Pessoa {

    private int id_pessoa;
    private Endereco endereco;
    private Tipo tipo;
    private String cpf;
    private String rg;
    private String email;
    private boolean sexo;
    private int numero;
    private Date dataNasc;
    private Date dataCad;
    private String estCivil;

    public Pessoa() {
    }

    public Pessoa(Endereco endereco, Tipo tipo, String cpf, String rg, String email, boolean sexo, int numero, Date dataNasc, Date dataCad, String estCivil) {
        this.endereco = endereco;
        this.tipo = tipo;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.sexo = sexo;
        this.numero = numero;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
        this.estCivil = estCivil;
    }

    public Pessoa(int id_pessoa, Endereco endereco, Tipo tipo, String cpf, String rg, String email, boolean sexo, int numero, Date dataNasc, Date dataCad, String estCivil) {
        this.id_pessoa = id_pessoa;
        this.endereco = endereco;
        this.tipo = tipo;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.sexo = sexo;
        this.numero = numero;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
        this.estCivil = estCivil;
    }

    public int getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(int id_pessoa) {
        this.id_pessoa = id_pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
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

    public String getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(String estCivil) {
        this.estCivil = estCivil;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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
        final Pessoa other = (Pessoa) obj;
        if (this.id_pessoa != other.id_pessoa) {
            return false;
        }
        return true;
    }

}
