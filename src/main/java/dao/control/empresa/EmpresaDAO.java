/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.control.empresa;

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
import model.empresa.Empresa;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class EmpresaDAO implements InterfaceDAO<Empresa> {

    private Connection geraConexao() throws DAOException {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection("localhost", "3306", "agencia_viagens", "root", "kggjfq377d6f");
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return conn;
    }

    @Override
    public void save(Empresa obj) throws DAOException {
        String sql = "INSERT INTO empresa (nome, cnpj) VALUES (?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpnj());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Empresa obj) throws DAOException {
        String sql = "UPDATE empresa SET nome = ?, cnpj = ? WHERE id_empresa = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpnj());
            ps.setInt(3, obj.getId_empresa());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = "DELETE FROM empresa WHERE id_empresa = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Empresa> getAll() throws DAOException {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_empresa = rs.getInt("id_empresa");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");

                lista.add(new Empresa(id_empresa, nome, cnpj));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Empresa getById(int id) throws DAOException {
        Empresa empresa = null;
        String sql = "SELECT * FROM empresa WHERE id_empresa = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id_empresa = rs.getInt("id_empresa");
                    String nome = rs.getString("nome");
                    String cnpj = rs.getString("cnpj");

                    empresa = new Empresa(id_empresa, nome, cnpj);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresa;
    }

}
