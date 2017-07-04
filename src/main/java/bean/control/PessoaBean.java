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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.pessoa.Endereco;
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
public class PessoaBean implements InterfaceBean, Serializable {

    private Pessoa pessoa;
    private Pessoa pessoaSelecionada;
    private int CEP;
    private int idTipo;
    private boolean habilitar;

    private TreeNode raiz;
    private List<Pessoa> pessoaRaiz;
    private List<Pessoa> pessoaFilha;

    public PessoaBean() {
        pessoa = new Pessoa();

        pessoa.setDataCad(dataHoje());

        pessoaSelecionada = new Pessoa();

        habilitar = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
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

    public List<Pessoa> getPessoaRaiz() {
        return pessoaRaiz;
    }

    public List<Pessoa> getPessoaFilha() {
        return pessoaFilha;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Telefone">
    /**
     * Checa qual telefone será validado, fixo ou celular
     *
     * @param t
     */
    public void checkTel(int t) {
        if (t == 0 && pessoa.getTelFixo().length() == 13) {
            verificaTel(pessoa.getTelFixo());
        } else if (t == 1 && pessoa.getTelCelular().length() == 14) {
            verificaTel(pessoa.getTelCelular());
        }
    }

    /**
     * [1-9]{2}\-[2-9][0-9]{7,8}
     *
     * OS DIGITOS NÃO PODEM SER TODOS IGUAIS (33)3333-3333 *
     *
     * DDD | Dois digitos de 1 a 9. Não existem códigos de DDD com o dígito 0 *
     *
     * O PRIMEIRO DIGITO NUNCA SERÁ 0 ou 1. Reservado para emergencias 193...
     *
     * OS DEMAIS 7 ou 8 DIGITOS PODEM IR DE 0 a 9
     *
     *
     * @param telefone
     * @return
     */
    private boolean verificaTel(String telefone) {
        if (!(telefone.equals("(__)____-____") || telefone.equals("(__)____-____")
                || telefone.contains("_"))) {
            String tel[] = convertVal(telefone, telefone.length());
            final int ddd = 1, tA = 2;

            boolean valida = false;

            if (!verificaVal(quebraVal(joinVal(tel)))) {
                if (validaDDD(tel[ddd])) {
                    if (verPriDigito(tel[tA])) {
                    } else {
                        valida = true;
                    }
                } else {
                    valida = true;
                }
            } else {
                valida = true;
            }

            if (valida) {
                exibirMsg("Telefone inválido");
                return false;
            }
        }
        return true;
    }

    /**
     * Valida DDD
     *
     * @param ddd
     * @return
     */
    private boolean validaDDD(String ddd) {
        int[] newDDD = quebraVal(ddd);

        for (int i = 0; i < newDDD.length; i++) {
            if (newDDD[i] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se o primeiro digito é 0 ou 1
     *
     * @return
     */
    private static boolean verPriDigito(String var) {
        int[] tel = quebraVal(var);
        int pD = tel[tel.length - 1];

        return !(pD == 0 || pD == 1);
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
        if (!(CPF.equals("___.___.___-__") || CPF.contains("_"))) {

            String[] cpf = convertVal(CPF, CPF.length());
            String newCpf = joinVal(cpf);
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
            pessoa.setCpf(newCpf);
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

    private void msgSucesso(Pessoa pessoa) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        pessoa.getTipo().getNome() + "foi salvo com sucesso", null));
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
     * Modifica uma atributo booleano
     *
     * @param b
     */
    public void mdfHab(boolean b) {
        setHabilitar(b);
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

    public void limpar() {
        pessoa.setEndereco(new Endereco());
        pessoa.setNumero(0);
        pessoa.setComplemento("");
        modifHab();
        setCEP(0);

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

    //<editor-fold defaultstate="collapsed" desc="Meotodos Endereco">
    /**
     * Copia uma pagina e salva em arquivo xml
     */
    public void salvarArquivo() {
        File file = new File("C:\\xtream\\endereco.xml");
        try {
            URL url = new URL("http://api.postmon.com.br/v1/cep/" + getCEP() + "?format=xml");

            new LoadPage().getPage(url, file);

            buscaArquivo();
        } catch (IOException ex) {
            exibirMsg("CEP não encontrado");
        }
    }

    /**
     * Busca um arquivo xml e seta em um objeto
     */
    private void buscaArquivo() {
        File ler = new File("C:\\xtream\\endereco.xml");
        XStream xstream = new XStream(new DomDriver());

        xstream.alias("result", result.class);

        result res = (result) xstream.fromXML(ler);

        Endereco end = new Endereco();

        end.setBairro(res.getBairro());
        end.setCep(res.getCep());
        end.setCidade(res.getCidade());
        end.setEstado(res.getEstado_info().getNome());
        end.setLogradouro(res.getLogradouro());
        end.setUf(res.getEstado());

        pessoa.setEndereco(end);
        modifHab();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Override">
    @Override
    public void salvar() {
        if (pessoa.getEndereco() == null) {
            exibirMsg("Endereço não informado");
        } else if (verificaCPF() && verificaTel(pessoa.getTelCelular())) {
            try {
                Tipo tipo;
                TipoDAO daoT = new TipoDAO();

                tipo = daoT.getById(idTipo);
                pessoa.setTipo(tipo);

                PessoaDAO dao = new PessoaDAO();
                dao.save(pessoa);

                msgSucesso(pessoa);
            } catch (DAOException ex) {
                Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void atualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir() {
        try {
            PessoaDAO dao = new PessoaDAO();
            dao.delete(pessoaSelecionada.getId_pessoa());
        } catch (DAOException ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pessoa> buscarTodos() {
        PessoaDAO dao = new PessoaDAO();
        try {
            return dao.getAll();
        } catch (DAOException ex) {
            Logger.getLogger(PessoaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Metodos Tree">
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
//</editor-fold>
}
