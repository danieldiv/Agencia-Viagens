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
public class Carro {

    private int id_carro;
    private Empresa empresa;
    private CategoriaCarro categoriaCarro;
    private TipoCarro tipoCarro;
    private int poltronas;
    private boolean sanitario;
    private boolean arCondicionado;
    private boolean televisao;
    private String placa;

    public Carro() {
    }

    public Carro(Empresa empresa, CategoriaCarro categoriaCarro, TipoCarro tipoCarro, int poltronas, boolean sanitario, boolean arCondicionado, boolean televisao, String placa) {
        this.empresa = empresa;
        this.categoriaCarro = categoriaCarro;
        this.tipoCarro = tipoCarro;
        this.poltronas = poltronas;
        this.sanitario = sanitario;
        this.arCondicionado = arCondicionado;
        this.televisao = televisao;
        this.placa = placa;
    }

    public Carro(int id_carro, Empresa empresa, CategoriaCarro categoriaCarro, TipoCarro tipoCarro, int poltronas, boolean sanitario, boolean arCondicionado, boolean televisao, String placa) {
        this.id_carro = id_carro;
        this.empresa = empresa;
        this.categoriaCarro = categoriaCarro;
        this.tipoCarro = tipoCarro;
        this.poltronas = poltronas;
        this.sanitario = sanitario;
        this.arCondicionado = arCondicionado;
        this.televisao = televisao;
        this.placa = placa;
    }

    public int getId_carro() {
        return id_carro;
    }

    public void setId_carro(int id_carro) {
        this.id_carro = id_carro;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public CategoriaCarro getCategoriaCarro() {
        return categoriaCarro;
    }

    public void setCategoriaCarro(CategoriaCarro categoriaCarro) {
        this.categoriaCarro = categoriaCarro;
    }

    public TipoCarro getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(TipoCarro tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public int getPoltronas() {
        return poltronas;
    }

    public void setPoltronas(int poltronas) {
        this.poltronas = poltronas;
    }

    public boolean isSanitario() {
        return sanitario;
    }

    public void setSanitario(boolean sanitario) {
        this.sanitario = sanitario;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isTelevisao() {
        return televisao;
    }

    public void setTelevisao(boolean televisao) {
        this.televisao = televisao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_carro;
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
        final Carro other = (Carro) obj;
        if (this.id_carro != other.id_carro) {
            return false;
        }
        return true;
    }

}
