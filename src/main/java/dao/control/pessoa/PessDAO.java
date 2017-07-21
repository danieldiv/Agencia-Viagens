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
import model.pessoa.Pess;
import model.pessoa.Pessoa;
import model.pessoa.Tipo;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class PessDAO implements InterfaceDAO<Pess> {

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
    public void save(Pess obj) throws DAOException {
        String sql = "INSERT INTO tb_pessoa (nome, cpf, rg, certidaoNasc, dataNasc, dataCad) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date dataC = new java.sql.Date(obj.getDataCad().getTime());
            java.sql.Date dataN = new java.sql.Date(obj.getDataNasc().getTime());

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getRg());
            ps.setString(4, obj.getCertidaNasc());
            ps.setDate(5, (Date) dataN);
            ps.setDate(6, (Date) dataC);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Pess obj) throws DAOException {
        String sql = "UPDATE tb_pessoa SET nome = ?, cpf = ?, rg = ?, "
                + "certidaoNasc = ?, dataNasc = ?, dataCad = ? WHERE id_pessoa = ? "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date dataC = new java.sql.Date(obj.getDataCad().getTime());
            java.sql.Date dataN = new java.sql.Date(obj.getDataNasc().getTime());

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getRg());
            ps.setString(4, obj.getCertidaNasc());
            ps.setDate(5, (Date) dataN);
            ps.setDate(6, (Date) dataC);
            ps.setInt(7, obj.getId_pessoa());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = "DELETE FROM tb_pessoa WHERE id_pessoa = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pess> getAll() throws DAOException {
        List<Pess> lista = new ArrayList<>();

        String sql = "SELECT * FROM tb_pessoa WHERE responsavel is null";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_pessoa = rs.getInt("id_pessoa");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                Date dataNasc = rs.getDate("dataNasc");
                Date dataCad = rs.getDate("dataCad");
                String certidaoNasc = rs.getString("certidaoNasc");

                lista.add(new Pess(id_pessoa, null, nome, cpf, rg, certidaoNasc, dataNasc, dataCad));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<Pess> getAllDep() throws DAOException {
        List<Pess> lista = new ArrayList<>();

        String sql = "SELECT * FROM tb_pessoa WHERE responsavel is not null";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pess responsavel;

                int id_pessoa = rs.getInt("id_pessoa");
                int id_responsavel = rs.getInt("id_responsavel");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                Date dataNasc = rs.getDate("dataNasc");
                Date dataCad = rs.getDate("dataCad");
                String certidaoNasc = rs.getString("certidaoNasc");

                responsavel = getById(id_responsavel);

                lista.add(new Pess(id_pessoa, responsavel, nome, cpf, rg, certidaoNasc, dataNasc, dataCad));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Pess getById(int id) throws DAOException {
        Pess pessoas = null;
        
        String sql = "SELECT * FROM tb_pessoa WHERE id_pessoa = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    int id_pessoa = rs.getInt("id_pessoa");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    String rg = rs.getString("rg");
                    Date dataNasc = rs.getDate("dataNasc");
                    Date dataCad = rs.getDate("dataCad");
                    String certidaNasc = rs.getString("certidaoNasc");

                    pessoas = new Pess(id_pessoa, null, nome, cpf, rg, certidaNasc, dataNasc, dataCad);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoas;
    }

}
