/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pessoa;

import java.io.Serializable;

/**
 *
 * @author Daniel
 */
public class Tipo implements Serializable{

    private int id_tipo;
    private String nome;
    private boolean tipo;

    public Tipo() {
    }

    public Tipo(int id_tipo, String nome, boolean tipo) {
        this.id_tipo = id_tipo;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public String getNome() {
        return nome;
    }

    public boolean isTipo() {
        return tipo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id_tipo;
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
        final Tipo other = (Tipo) obj;
        if (this.id_tipo != other.id_tipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
