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

    public PessoaBean() {
        pessoa = new Pessoa();

        pessoa.setDataCad(dataHoje());

        pessoaSelecionada = new Pessoa();

        habilitar = false;
    }

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
    public boolean convertCPF() {
        if (!getPessoa().getCpf().equals("___.___.___-__")) {
            System.out.println("teste");
            String[] cpf = getPessoa().getCpf().split("[.-]");

            String newCpf = "";

            for (String c : cpf) {
                newCpf += c;
            }

            pessoa.setCpf(newCpf);

            long i = Long.valueOf(newCpf);
            int[] aryCpf = new int[11];

            for (int j = 0; j < aryCpf.length; j++) {
                aryCpf[j] = (int) (i % 10);

                i /= 10;
            }

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
                exibirMsg("cpf");
                return false;
            }
        }
        return true;

    }

    /**
     * Exibe a mensagem de acordo com o que for requisitado
     *
     * @param valor
     */
    private void exibirMsg(String valor) {
        switch (valor) {
            case "endereco":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Endereço não informado", null));
                break;
            case "cpf":
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CPF inválido", null));
                break;
            default:
                break;
        }
    }

    /**
     * Verifica se todos os digitos do cpf sao iguais
     *
     * @param cpf
     * @return
     */
//    private boolean verificaVal(int[] cpf) {
//        int cont = 0;
//
//        for (int i = 0; i < cpf.length - 1; i++) {
//            if (cpf[i] == cpf[i + 1]) {
//                cont++;
//            }
//        }
//        return cont == 10;
//    }
    
    /**
     * Verifica de todos os digitos sao iguais
     */
    private static boolean verificaVal(int[] val) {
        int cont = 0;

        for (int i = 0; i < val.length - 1; i++) {
            if (val[i] == val[i + 1]) {
                cont++;
            }
        }
        return cont == (val.length - 1);
    }

    /**
     * Retorna verdadeiro se validacao do cpf for correta
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
     * Converte a string minuscula para maiuscula
     */
    public void convertUppC() {
        pessoa.setNome(pessoa.getNome().toUpperCase());
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
     * Modifica um atributo boolean
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
        cal.add(Calendar.YEAR, -2);

        return cal.getTime();
    }

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CEP não encontrado", null));
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

    @Override
    public void salvar() {
        if (pessoa.getEndereco() == null) {
            exibirMsg("endereco");
        } else if (convertCPF()) {
            try {
                Tipo tipo;
                TipoDAO daoT = new TipoDAO();

                tipo = daoT.getById(idTipo);
                pessoa.setTipo(tipo);

                PessoaDAO dao = new PessoaDAO();
                dao.save(pessoa);
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

}
