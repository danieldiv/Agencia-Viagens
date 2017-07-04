/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.control;

import dao.control.pessoa.PessoaDAO;
import dao.model.DAOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.pessoa.Pessoa;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class TreeUnidadeBean implements Serializable {

    private TreeNode raiz;
    private List<Pessoa> pessoaRaiz;
    private List<Pessoa> pessoaFilha;

    public void consultar() throws DAOException {
        PessoaDAO dao = new PessoaDAO();

        pessoaRaiz = dao.getAll();
        pessoaFilha = dao.getAllDep();

        this.raiz = new DefaultTreeNode("Raiz", null);
        adicionarNos(pessoaRaiz, this.raiz);
    }

    public void adicionarNos(List<Pessoa> pessoas, TreeNode pai) throws DAOException {
        List<Pessoa> lista;

        for (Pessoa p : pessoas) {
            lista = new ArrayList<>();
            TreeNode no = new DefaultTreeNode(p, pai);

            for (int i = 0; i < pessoaFilha.size(); i++) {
                if (pessoaFilha.get(i).getResponsavel().getId_pessoa() == p.getId_pessoa()) {
                    lista.add(pessoaFilha.get(i));
                }
            }
            if (!lista.isEmpty()) {
                adicionarNos(lista, no);
            }
        }
    }

    public TreeNode getRaiz() {
        return raiz;
    }

    public List<Pessoa> getPessoaRaiz() {
        return pessoaRaiz;
    }

    public List<Pessoa> getPessoaFilha() {
        return pessoaFilha;
    }

}
