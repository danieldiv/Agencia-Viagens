/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.nicetrip.controller.people;

import com.primefaces.nicetrip.controller.InterfaceBean;
import com.primefaces.nicetrip.dao.DAOException;
import com.primefaces.nicetrip.dao.people.PeopleDAO;
import com.primefaces.nicetrip.model.People;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Daniel
 */
@ManagedBean
@SessionScoped
public class PeopleBean implements InterfaceBean<People>, Serializable {

    private People people;
    private boolean habilitar;

    private TreeNode root;
    private TreeNode selectedItem;
    private List<People> noPai;
    private List<People> noFilha;

    public PeopleBean() {
        people = new People();
        habilitar = true;
    }

    //<editor-fold defaultstate="collapsed" desc="Metodos Override">
    @Override
    public void salvar() {
        PeopleDAO dao = new PeopleDAO();
        try {
            if (verificaCPF()) {
                dao.save(people);
            }
        } catch (DAOException ex) {
            Logger.getLogger(PeopleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void atualizar() {
        PeopleDAO dao = new PeopleDAO();
        try {
            if (verificaCPF()) {
                dao.update(people);
            }
        } catch (DAOException ex) {
            Logger.getLogger(PeopleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void excluir() {
        try {
            PeopleDAO dao = new PeopleDAO();
            dao.delete(people.getId_pessoa());
        } catch (DAOException ex) {
            Logger.getLogger(PeopleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<People> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Tree">
    public void consultar() throws DAOException {
        PeopleDAO dao = new PeopleDAO();

        this.noPai = dao.getAll();
        this.noFilha = dao.getAllDep();

        this.root = new DefaultTreeNode("Raiz", null);
        addNode(noPai, this.root);
    }

    public void addNode(List<People> people, TreeNode root) throws DAOException {
        List<People> lista;

        for (People p : people) {
            lista = new ArrayList<>();
            TreeNode no = new DefaultTreeNode(p, root);

            for (int i = 0; i < noFilha.size(); i++) {
                if (noFilha.get(i).getResponsavel().getId_pessoa() == p.getId_pessoa()) {
                    lista.add(noFilha.get(i));
                }
            }
            if (!lista.isEmpty()) {
                addNode(lista, no);
            }
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(TreeNode selectedItem) {
        this.selectedItem = selectedItem;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Classe">
    /**
     * Verifica se algum item da tabela foi selecionado, redireciona para a
     * pagina com os dados selecionado na tabela e retorna uma mensagem se nao
     * tiver nada selecionada
     *
     * @return
     */
    public String redirecionar() {
        if (people == null) {
            exibirMsg("Nenhum dado selecionado");
            return "";
        }
        return "update.xhtml?faces-redirect=true";

    }

    public void newPeople() {
        people = new People();
        people.setDataCad(dataHoje());
    }

    /**
     * Retorna a data atual menos X anos
     *
     * @return
     */
    public Date dataHoje() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 0);

        return cal.getTime();
    }

    /**
     * Converte a string minuscula para maiuscula
     */
    public void convertUppNome() {
        people.setNome(people.getNome().toUpperCase());
    }

    public void convertUppRg() {
        people.setRg(people.getRg().toUpperCase());
    }

    /**
     * Esxibe mensagem do parametro
     *
     * @param msg
     */
    private void exibirMsg(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Validadores e Conversores">
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
        String CPF = people.getCpf();
        String newCpf;
        if (!(CPF.contains("_")) && !CPF.isEmpty()) {

            String[] cpf = convertVal(CPF, CPF.length());
            newCpf = joinVal(cpf);
            int[] aryCpf = quebraVal(newCpf);

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
            people.setCpf(newCpf);
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
     * Convert String com mascara em um Array de String
     *
     * @param var
     * @param tipo
     * @return
     */
    private String[] convertVal(String var, int tipo) {
        if (tipo == 14 && var.contains(".")) {
            return var.split("[.-]");
        } else {
            return var.split("[()-]");
        }
    }

    /**
     * Junta valores de um Array em uma String
     *
     * @param val
     * @return
     */
    private String joinVal(String[] val) {
        String newVal = "";

        for (String string : val) {
            newVal += string;
        }
        return newVal;
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
}
