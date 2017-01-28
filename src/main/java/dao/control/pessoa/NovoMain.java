/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.pessoa;

import dao.model.DAOException;
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
        PessoaDAO dao = new PessoaDAO();
        System.out.println(dao.getById(4));
    }


}
