/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.empresa;

import dao.control.pessoa.TipoDAO;
import dao.model.DAOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.pessoa.Tipo;

/**
 *
 * @author Daniel
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TipoDAO dao = new TipoDAO();
        
        try {
            List<Tipo> lista = dao.getAll();
            
            for (Tipo tipo : lista) {
                System.out.println("id: " + tipo.getId_tipo());
                System.out.println("nome: " + tipo.getNome());
            }
        } catch (DAOException ex) {
            Logger.getLogger(NovoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
