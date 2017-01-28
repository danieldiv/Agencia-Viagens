/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.xml;

/**
 *
 * @author Daniel
 */
public class result {

    private String bairro;
    private String cidade;
    private int cep;
    private String logradouro;
    private estado_info estado_info;
    private cidade_info cidade_info;
    private String estado;

    public result() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public estado_info getEstado_info() {
        return estado_info;
    }

    public void setEstado_info(estado_info estado_info) {
        this.estado_info = estado_info;
    }

    public cidade_info getCidade_info() {
        return cidade_info;
    }

    public void setCidade_info(cidade_info cidade_info) {
        this.cidade_info = cidade_info;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
