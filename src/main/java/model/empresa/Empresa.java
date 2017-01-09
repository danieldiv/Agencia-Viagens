/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.empresa;

/**
 *
 * @author Daniel
 */
public class Empresa {

    private int id_empresa;
    private String nome;
    private String cpnj;

    public Empresa() {
    }

    public Empresa(String nome, String cpnj) {
        this.nome = nome;
        this.cpnj = cpnj;
    }

    public Empresa(int id_empresa, String nome, String cpnj) {
        this.id_empresa = id_empresa;
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
