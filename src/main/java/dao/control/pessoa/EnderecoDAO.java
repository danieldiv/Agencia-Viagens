/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.pessoa;

import dao.model.DAOException;
import dao.model.InterfaceDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.pessoa.Endereco;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class EnderecoDAO implements InterfaceDAO<Endereco> {

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
    public void save(Endereco obj) throws DAOException {
        String sql = "INSERT INTO endereco (cep, logradouro, bairro, cidade, estado, uf) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, obj.getCep());
            ps.setString(2, obj.getLogradouro());
            ps.setString(3, obj.getBairro());
            ps.setString(4, obj.getCidade());
            ps.setString(5, obj.getEstado());
            ps.setString(6, obj.getUf());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Endereco obj) throws DAOException {
        String sql = "UPDATE endereco SET logradouro = ?, bairro = ?, "
                + "cidade = ?, estado = ?, uf = ? WHERE cep = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getLogradouro());
            ps.setString(2, obj.getBairro());
            ps.setString(3, obj.getCidade());
            ps.setString(4, obj.getEstado());
            ps.setString(5, obj.getUf());
            ps.setInt(6, obj.getCep());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = "DELETE FROM endereco WHERE cep = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Endereco> getAll() throws DAOException {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM endereco";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int cep = rs.getInt("cep");
                String logradouro = rs.getString("logradouro");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String uf = rs.getString("uf");

                lista.add(new Endereco(cep, logradouro, bairro, cidade, estado, uf));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Endereco getById(int id) throws DAOException {
        Endereco endereco = null;
        String sql = "SELECT * FROM endereco WHERE cep = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int cep = rs.getInt("cep");
                    String logradouro = rs.getString("logradouro");
                    String bairro = rs.getString("bairro");
                    String cidade = rs.getString("cidade");
                    String estado = rs.getString("estado");
                    String uf = rs.getString("uf");

                    endereco = new Endereco(cep, logradouro, bairro, cidade, estado, uf);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return endereco;
    }

}
