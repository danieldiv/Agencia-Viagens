/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pessoa;

/**
 *
 * @author Daniel
 */
public class Endereco {

    private int cep;
    private String logradouro;
    private String bairro;
    private String ciadade;
    private String uf;

    public Endereco() {
    }

    public Endereco(int cep, String logradouro, String bairro, String ciadade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.ciadade = ciadade;
        this.uf = uf;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCiadade() {
        return ciadade;
    }

    public void setCiadade(String ciadade) {
        this.ciadade = ciadade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.cep;
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
        final Endereco other = (Endereco) obj;
        if (this.cep != other.cep) {
            return false;
        }
        return true;
    }

}
