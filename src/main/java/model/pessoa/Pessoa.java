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
public class Pessoa implements Serializable {

    private int id_pessoa;
    private Endereco endereco;
    private Tipo tipo;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private boolean sexo;
    private int numero;
    private String complemento;
    private Date dataNasc;
    private Date dataCad;
    private String estCivil;
    private String telFixo;
    private String telCelular;

    public Pessoa() {
    }

    public Pessoa(Endereco endereco, Tipo tipo, String nome, String cpf, String rg, String email, boolean sexo, int numero, String complemento, Date dataNasc, Date dataCad, String estCivil, String telFixo, String telCelular) {
        this.endereco = endereco;
        this.tipo = tipo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.sexo = sexo;
        this.numero = numero;
        this.complemento = complemento;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
        this.estCivil = estCivil;
        this.telFixo = telFixo;
        this.telCelular = telCelular;
    }

    public Pessoa(int id_pessoa, Endereco endereco, Tipo tipo, String nome, String cpf, String rg, String email, boolean sexo, int numero, String complemento, Date dataNasc, Date dataCad, String estCivil, String telFixo, String telCelular) {
        this.id_pessoa = id_pessoa;
        this.endereco = endereco;
        this.tipo = tipo;
        this.nome = nome;
        setCpf(cpf);
        this.rg = rg;
        this.email = email;
        this.sexo = sexo;
        this.numero = numero;
        this.complemento = complemento;
        this.dataNasc = dataNasc;
        this.dataCad = dataCad;
        this.estCivil = estCivil;
        this.telFixo = telFixo;
        this.telCelular = telCelular;
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
        this.cpf = montarCpf(cpf);
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

    public String getNome() {
        return nome;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelFixo() {
        return telFixo;
    }

    public void setTelFixo(String telFixo) {
        this.telFixo = telFixo;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
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
    
    private String montarCpf(String cpf){
        String[] CPF = new String[7];
        String newCpf = "";

        CPF[0] = cpf.substring(0, 3);
        CPF[1] = ".";
        CPF[2] = cpf.substring(3, 6);
        CPF[3] = ".";
        CPF[4] = cpf.substring(6, 9);
        CPF[5] = "-";
        CPF[6] = cpf.substring(9, 11);

        for (String string : CPF) {
            newCpf += string;
        }
        return newCpf;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id_pessoa=" + id_pessoa + ", endereco=" + endereco + ", tipo=" + tipo + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", email=" + email + ", sexo=" + sexo + ", numero=" + numero + ", complemento=" + complemento + ", dataNasc=" + dataNasc + ", dataCad=" + dataCad + ", estCivil=" + estCivil + ", telFixo=" + telFixo + ", telCelular=" + telCelular + '}';
    }

}
