/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.control;

import bean.model.InterfaceBean;
import dao.control.pessoa.TipoDAO;
import dao.model.DAOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.pessoa.Tipo;

/**
 *
 * @author Daniel
 */
@ManagedBean
@ViewScoped
public class TipoBean implements InterfaceBean<Tipo>, Serializable {

    private Tipo tipo;
    private Tipo tipoSelecionado;

    public TipoBean() {
        tipo = new Tipo();
        tipoSelecionado = new Tipo();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipoSelecionado() {
        return tipoSelecionado;
    }

    public void setTipoSelecionado(Tipo tipoSelecionado) {
        this.tipoSelecionado = tipoSelecionado;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos Override">
    @Override
    public void salvar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipo> buscarTodos() {
        TipoDAO dao = new TipoDAO();

        try {
            return dao.getAll();
        } catch (DAOException ex) {
            Logger.getLogger(TipoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
//</editor-fold>

}
