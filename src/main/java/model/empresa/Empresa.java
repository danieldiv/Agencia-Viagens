/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.empresa;

import model.pessoa.Endereco;

/**
 *
 * @author Daniel
 */
public class Empresa {

    private int id_empresa;
    private Endereco endereco;
    private int numero;
    private String nome;
    private String cpnj;

    public Empresa() {
    }

    public Empresa(Endereco endereco, int numero, String nome, String cpnj) {
        this.endereco = endereco;
        this.numero = numero;
        this.nome = nome;
        this.cpnj = cpnj;
    }

    public Empresa(int id_empresa, Endereco endereco, int numero, String nome, String cpnj) {
        this.id_empresa = id_empresa;
        this.endereco = endereco;
        this.numero = numero;
        this.nome = nome;
        this.cpnj = cpnj;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpnj() {
        return cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_empresa;
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
        final Empresa other = (Empresa) obj;
        if (this.id_empresa != other.id_empresa) {
            return false;
        }
        return true;
    }

}
