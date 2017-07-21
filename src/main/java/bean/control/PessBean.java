/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.control;

import bean.model.InterfaceBean;
import bean.xml.LoadPage;
import bean.xml.result;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import dao.control.pessoa.PessDAO;
import dao.control.pessoa.PessoaDAO;
import dao.control.pessoa.TipoDAO;
import dao.model.DAOException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import model.pessoa.Endereco;
import model.pessoa.Pess;
import model.pessoa.Pessoa;
import model.pessoa.Tipo;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Daniel
 */
@ManagedBean
@ViewScoped
public class PessBean implements InterfaceBean, Serializable {

    private Pess pessoa;
    private Pess pessoaSelecionada;
    private boolean habilitar;

    private TreeNode raiz;
    private TreeNode selectedItem;
    private List<Pess> pessoaRaiz;
    private List<Pess> pessoaFilha;

    public PessBean() {
        pessoa = new Pess();
        pessoa.setDataCad(dataHoje());
        pessoaSelecionada = new Pess();
        habilitar = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public TreeNode getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(TreeNode selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Pess getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pess pessoa) {
        this.pessoa = pessoa;
    }

    public Pess getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pess pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
    }

    public TreeNode getRaiz() {
        return raiz;
    }

    public List<Pess> getPessoaRaiz() {
        return pessoaRaiz;
    }

    public List<Pess> getPessoaFilha() {
        return pessoaFilha;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos CPF">
    /**
     * VALIDACAO DO PRIMEIRO DIGITO
     *
     * Multiplica-se os 9 primeiros dígitos pela sequência decrescente de
     * números de 10 à 2 e soma os resultados.
     *
     * 5 * 10 + 2 * 9 + 9 * 8 + 9 * 7 + 8 * 6 + 2 * 5 + 2 * 4 + 4 * 3 + 7 * 2
     *
     * Multiplicar esse resultado por 10 e dividi por 11.
     *
     * (295 * 10 / 11) = 268 | RESTO = 2
     *
     * O resto da divisao deve ser igual ao primeiro digito depois do '-' Se o
     * resto da divisão for igual a 10 considera-se como 0.
     *
     * VALIDACAO DO SEGUNDO DIGITO
     *
     * Multiplica-se os 10 primeiros dígitos pela sequência decrescente de
     * números de 11 à 2 e soma os resultados.
     *
     * 5 * 11 + 2 * 10 + 9 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 2 * 5 + 4 * 4 + 7 * 3 +
     * 2 * 2 = (347 * 10 / 11) = 315 | RESTO = 5
     *
     * CPFs com todos os digitos iguais passam como true pela verificacao, mas
     * sao invalidos (111.111.111-11, 222.222.222-22, …)
     *
     * @return
     */
    public boolean verificaCPF() {
        final String CPF = getPessoa().getCpf();
        if (!(CPF.contains("_"))) {

            int[] aryCpf = quebraVal(CPF);
            boolean valida = false;

            if (!verificaVal(aryCpf)) {
                if (validaCPF(aryCpf, 0, 1)) {
                    if (!validaCPF(aryCpf, 1, 0)) {
                        valida = true;
                    }
                } else {
                    valida = true;
                }
            } else {
                valida = true;
            }
            if (valida) {
                exibirMsg("CPF inválido");
                return false;
            }
        }
        return true;
    }

    /**
     * Valida o CPF
     *
     * @param cpf
     * @param condicao
     * @param posicao
     * @return
     */
    private boolean validaCPF(int[] cpf, int condicao, int posicao) {
        int soma = 0;

        for (int j = 10 + condicao; j > 1; j--) {
            soma += (cpf[j - condicao] * j);
        }

        return ((soma * 10) % 11) == cpf[posicao];
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Classe">
    /**
     * Exibe a mensagem de acordo com o que for requisitado
     *
     * @param valor
     */
    private void exibirMsg(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
    }

    
    /**
     * Retorna um boolean para habilitar e desabilitar botoes e inputs
     *
     * @return
     */
    public boolean btnVisible() {
        return isHabilitar();
    }

    /**
     * Modifica um atributo boolean - reavaliar
     */
    public void modifHab() {
        if (isHabilitar()) {
            setHabilitar(false);
        } else {
            setHabilitar(true);
        }
    }

    

    /**
     * Retorna a data atual menos 2 anos
     *
     * @return
     */
    public final Date dataHoje() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 0);

        return cal.getTime();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Validadores e Conversores">
    /**
     * Converte a string minuscula para maiuscula
     */
    public void convertUppC() {
        pessoa.setNome(pessoa.getNome().toUpperCase());
    }

    /**
     * Verifica de todos os digitos sao iguais
     */
    private boolean verificaVal(int[] val) {
        int cont = 0;

        for (int i = 0; i < val.length - 1; i++) {
            if (val[i] == val[i + 1]) {
                cont++;
            }
        }
        return cont == (val.length - 1);
    }

    /**
     * Transforma int em Array de int
     *
     * @param val
     * @return
     */
    private static int[] quebraVal(String val) {
        Long i = Long.valueOf(val);
        int[] ary = new int[val.length()];

        for (int j = 0; j < ary.length; j++) {
            ary[j] = (int) (i % 10);

            i /= 10;
        }
        return ary;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Tree">
    public void consultar() throws DAOException {
        System.out.println("1");
        PessDAO dao = new PessDAO();
        System.out.println("2");
//        PessDAO dao = new PessDAO();
        
//        pessoaRaiz = dao.getAll();
//        pessoaFilha = dao.getAllDep();
//
//        this.raiz = new DefaultTreeNode("Raiz", null);
//        adicionarNos(pessoaRaiz, this.raiz);
    }

    public void adicionarNos(List<Pess> pessoas, TreeNode pai) throws DAOException {
        List<Pess> lista;

        for (Pess p : pessoas) {
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
    }

    @Override
    public List buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>
}
