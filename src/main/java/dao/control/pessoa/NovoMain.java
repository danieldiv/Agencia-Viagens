/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.pessoa;

import com.sun.media.sound.EmergencySoundbank;
import dao.model.DAOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.empresa.CategoriaCarro;
import model.empresa.Empresa;
import model.empresa.TipoCarro;
import model.pessoa.Usuario;

/**
 *
 * @author Daniel
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UsuarioDAO dao = new UsuarioDAO();

//            Usuario user = dao.getById(2);

//            if (user == null) {
//                System.out.println("null");
//            } else {
//                System.out.println(user.getUsuario());
//            }

            Usuario user = new Usuario(2, "usuario1-teste", "email1", "cpf1", "senha1");
            user.setUsuario(convert("daNieL"));
            dao.update(user);
//            Usuario user = new Usuario("usuario2", "email2", "cpf2", "senha2");
//            Usuario user = new Usuario("usuario3", "email3", "cpf3", "senha3");
//            dao.delete(1);
//            JOptionPane.showMessageDialog(null, "delete");
//            List<Usuario> lista = dao.getAll();
////            
//            for (Usuario usuario : lista) {
//                System.out.println(usuario.getId_usuario());
//                System.out.println(usuario.getUsuario());
//            }
        } catch (DAOException ex) {
            Logger.getLogger(NovoMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static String convert(String nome){
        return nome.toLowerCase();
//        return nome.toUpperCase();
    }

}
