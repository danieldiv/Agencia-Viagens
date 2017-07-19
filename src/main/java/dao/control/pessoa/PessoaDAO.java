/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.pessoa;

import dao.model.DAOException;
import dao.model.InterfaceDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.pessoa.Endereco;
import model.pessoa.Pessoa;
import model.pessoa.Tipo;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class PessoaDAO implements InterfaceDAO<Pessoa> {

    private Connection geraConexao() throws DAOException {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection("localhost", "3306", "nicetrip", "root", "kggjfq377d6f");
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return conn;
    }

    @Override
    public void save(Pessoa obj) throws DAOException {
        EnderecoDAO dao = new EnderecoDAO();

        if (dao.getById(obj.getEndereco().getCep()) == null) {
            dao.save(obj.getEndereco());
        }

        String sql = "INSERT INTO pessoa (id_tipo, cep, nome, cpf, rg, email, "
                + "sexo, numero, complemento, dataNasc, dataCad, estCivil, "
                + "telFixo, telCelular) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date dataC = new java.sql.Date(obj.getDataCad().getTime());
            java.sql.Date dataN = new java.sql.Date(obj.getDataNasc().getTime());

            ps.setInt(1, obj.getTipo().getId_tipo());
            ps.setInt(2, obj.getEndereco().getCep());
            ps.setString(3, obj.getNome());
            ps.setString(4, obj.getCpf());
            ps.setString(5, obj.getRg());
            ps.setString(6, obj.getEmail());
            ps.setBoolean(7, obj.isSexo());
            ps.setInt(8, obj.getNumero());
            ps.setString(9, obj.getComplemento());
            ps.setDate(10, (Date) dataN);
            ps.setDate(11, (Date) dataC);
            ps.setString(12, obj.getEstCivil());
            ps.setString(13, obj.getTelFixo());
            ps.setString(14, obj.getTelCelular());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Pessoa obj) throws DAOException {
        EnderecoDAO dao = new EnderecoDAO();

        if (dao.getById(obj.getEndereco().getCep()) == null) {
            dao.save(obj.getEndereco());
        }

        String sql = "UPDATE pessoa SET cep = ?, nome = ?, "
                + "cpf = ?, rg = ?, email = ?, sexo = ?, numero = ?, "
                + "complemento = ?, dataNasc = ?, dataCad = ?, estCivil = ?, "
                + "telFixo = ?, telCelular = ? WHERE id_tipo = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date dataC = new java.sql.Date(obj.getDataCad().getTime());
            java.sql.Date dataN = new java.sql.Date(obj.getDataNasc().getTime());

            ps.setInt(1, obj.getEndereco().getCep());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getRg());
            ps.setString(5, obj.getEmail());
            ps.setBoolean(6, obj.isSexo());
            ps.setInt(7, obj.getNumero());
            ps.setString(8, obj.getComplemento());
            ps.setDate(9, (Date) dataN);
            ps.setDate(10, (Date) dataC);
            ps.setString(11, obj.getEstCivil());
            ps.setString(12, obj.getTelFixo());
            ps.setString(13, obj.getTelCelular());
            ps.setInt(14, obj.getTipo().getId_tipo());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pessoa> getAll() throws DAOException {
        List<Pessoa> lista = new ArrayList<>();
        Endereco endereco = new Endereco();
        Tipo tipo = new Tipo();

        EnderecoDAO daoE = new EnderecoDAO();
        TipoDAO daoT = new TipoDAO();

        String sql = "SELECT * FROM pessoa";
//        String sql = "SELECT * FROM pessoa WHERE responsavel is null";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pessoa responsavel = null;

                int id_pessoa = rs.getInt("id_pessoa");
                int id_responsavel = rs.getInt("responsavel");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                boolean sexo = rs.getBoolean("sexo");
                int numero = rs.getInt("numero");
                String complemento = rs.getString("complemento");
                Date dataNasc = rs.getDate("dataNasc");
                Date dataCad = rs.getDate("dataCad");
                String estCivil = rs.getString("estCivil");
                String telFixo = rs.getString("telFixo");
                String telCelular = rs.getString("telCelular");

                if (id_responsavel != 0) {
                    responsavel = getById(id_responsavel);
                }

                cpf = montarCpf(cpf);

                endereco = daoE.getById(rs.getInt("cep"));

                tipo = daoT.getById(rs.getInt("id_tipo"));

                lista.add(new Pessoa(id_pessoa, endereco, tipo, nome, cpf, rg, email, sexo, numero, complemento, dataNasc, dataCad, estCivil, telFixo, telCelular, responsavel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    /**
     * Busca todos os dependente
     *
     * @return
     * @throws DAOException
     */
    public List<Pessoa> getAllDep() throws DAOException {
        List<Pessoa> lista = new ArrayList<>();
        Endereco endereco;
        Tipo tipo;

        EnderecoDAO daoE = new EnderecoDAO();
        TipoDAO daoT = new TipoDAO();

        String sql = "SELECT * FROM pessoa WHERE responsavel is not null";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pessoa responsavel = null;

                int id_pessoa = rs.getInt("id_pessoa");
                int id_responsavel = rs.getInt("responsavel");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                boolean sexo = rs.getBoolean("sexo");
                int numero = rs.getInt("numero");
                String complemento = rs.getString("complemento");
                Date dataNasc = rs.getDate("dataNasc");
                Date dataCad = rs.getDate("dataCad");
                String estCivil = rs.getString("estCivil");
                String telFixo = rs.getString("telFixo");
                String telCelular = rs.getString("telCelular");

                if (id_responsavel != 0) {
                    responsavel = getById(id_responsavel);
                }

                cpf = montarCpf(cpf);

                endereco = daoE.getById(rs.getInt("cep"));
                tipo = daoT.getById(rs.getInt("id_tipo"));

                lista.add(new Pessoa(id_pessoa, endereco, tipo, nome, cpf, rg, email, sexo, numero, complemento, dataNasc, dataCad, estCivil, telFixo, telCelular, responsavel));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Pessoa getById(int id) throws DAOException {
        Pessoa pessoa = null;
        Endereco endereco;
        Tipo tipo;

        EnderecoDAO daoE = new EnderecoDAO();
        TipoDAO daoT = new TipoDAO();

        String sql = "SELECT * FROM pessoa WHERE id_pessoa = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pessoa responsavel = null;

                    int id_pessoa = rs.getInt("id_pessoa");
                    int id_responsavel = rs.getInt("responsavel");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    String rg = rs.getString("rg");
                    String email = rs.getString("email");
                    boolean sexo = rs.getBoolean("sexo");
                    int numero = rs.getInt("numero");
                    String complemento = rs.getString("complemento");
                    Date dataNasc = rs.getDate("dataNasc");
                    Date dataCad = rs.getDate("dataCad");
                    String estCivil = rs.getString("estCivil");
                    String telFixo = rs.getString("telFixo");
                    String telCelular = rs.getString("telCelular");

                    if (id_responsavel != 0) {
                        responsavel = getById(id_responsavel);
                    }

                    cpf = montarCpf(cpf);

                    endereco = daoE.getById(rs.getInt("cep"));
                    tipo = daoT.getById(rs.getInt("id_tipo"));

                    pessoa = new Pessoa(id_pessoa, endereco, tipo, nome, cpf, rg, email, sexo, numero, complemento, dataNasc, dataCad, estCivil, telFixo, telCelular, responsavel);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoa;
    }

    private String montarCpf(String cpf) {
        String[] CPF = new String[7];
        String newCpf = "";

        CPF[0] = cpf.substring(0, 3);
        CPF[1] = ".";
        CPF[2] = cpf.substring(3, 6);
        CPF[3] = ".";
        CPF[4] = cpf.substring(6, 9);
        CPF[5] = "-";
        CPF[6] = cpf.substring(9, 11);

        for (String string : CPF) {
            newCpf += string;
        }
        return newCpf;
    }
}
