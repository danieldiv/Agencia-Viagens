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
public class CategoriaCarro {

    private int id_categoriaCarro;
    private String nome;

    public CategoriaCarro() {
    }

    public CategoriaCarro(String nome) {
        this.nome = nome;
    }

    public CategoriaCarro(int id_categoriaCarro, String nome) {
        this.id_categoriaCarro = id_categoriaCarro;
        this.nome = nome;
    }

    public int getId_categoriaCarro() {
        return id_categoriaCarro;
    }

    public void setId_categoriaCarro(int id_categoriaCarro) {
        this.id_categoriaCarro = id_categoriaCarro;
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
        hash = 43 * hash + this.id_categoriaCarro;
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
        final CategoriaCarro other = (CategoriaCarro) obj;
        if (this.id_categoriaCarro != other.id_categoriaCarro) {
            return false;
        }
        return true;
    }

}
