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
public class TipoCarro {

    private int id_tipoCarro;
    private String nome;

    public TipoCarro() {
    }

    public TipoCarro(String nome) {
        this.nome = nome;
    }

    public TipoCarro(int id_tipoCarro, String nome) {
        this.id_tipoCarro = id_tipoCarro;
        this.nome = nome;
    }

    public int getId_tipoCarro() {
        return id_tipoCarro;
    }

    public void setId_tipoCarro(int id_tipoCarro) {
        this.id_tipoCarro = id_tipoCarro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id_tipoCarro;
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
        final TipoCarro other = (TipoCarro) obj;
        if (this.id_tipoCarro != other.id_tipoCarro) {
            return false;
        }
        return true;
    }

}
