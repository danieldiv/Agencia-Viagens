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
public class estado_info {

    private String area_km2;
    private String codigo_ibge;
    private String nome;

    public estado_info() {
    }

    public String getArea_km2() {
        return area_km2;
    }

    public void setArea_km2(String area_km2) {
        this.area_km2 = area_km2;
    }

    public String getCodigo_ibge() {
        return codigo_ibge;
    }

    public void setCodigo_ibge(String codigo_ibge) {
        this.codigo_ibge = codigo_ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
