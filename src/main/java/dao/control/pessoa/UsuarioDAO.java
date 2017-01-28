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
import model.pessoa.Usuario;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class UsuarioDAO implements InterfaceDAO<Usuario> {

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
    public void save(Usuario obj) throws DAOException {
        String sql = "INSERT INTO usuario (usuario, email, cpf, senha) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getUsuario());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getSenha());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Usuario obj) throws DAOException {
        String sql = "UPDATE usuario SET usuario = ?, email = ?, cpf = ?, "
                + "senha = ? WHERE id_usuario = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, obj.getUsuario());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getSenha());
            ps.setInt(5, obj.getId_usuario());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Usuario> getAll() throws DAOException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String usuario = rs.getString("usuario");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");

                lista.add(new Usuario(id_usuario, usuario, sql, cpf, senha));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Usuario getById(int id) throws DAOException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id_usuario = rs.getInt("id_usuario");
                    String usuarioNome = rs.getString("usuario");
                    String cpf = rs.getString("cpf");
                    String senha = rs.getString("senha");

                    usuario = new Usuario(id_usuario, usuarioNome, sql, cpf, senha);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

}
