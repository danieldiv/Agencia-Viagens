/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.pessoa;

import dao.model.DAOException;
import java.util.List;
import model.pessoa.Pessoa;

/**
 *
 * @author Daniel
 */
public class NovoMain {

    /**
     * @param args the command line arguments
     * @throws dao.model.DAOException
     */
    public static void main(String[] args) throws DAOException {
        PessDAO dao = new PessDAO();
        System.out.println(dao.getAll());
//        PessoaDAO dao = new PessoaDAO();
//
//        List<Pessoa> l = dao.getAll();
//
//        for (Pessoa pessoa : l) {
//            System.out.println(pessoa);
//        }

    }

    public static boolean vriResponsavel(Pessoa pessoa) {
        return pessoa.getResponsavel() == null;
    }

}
