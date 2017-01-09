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
import model.pessoa.Tipo;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class TipoDAO implements InterfaceDAO<Tipo> {

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
    public void save(Tipo obj) throws DAOException {
        //Não utilizado
    }

    @Override
    public void update(Tipo obj) throws DAOException {
        //Não utilizado
    }

    @Override
    public void delete(int id) throws DAOException {
        //Não utilizado
    }

    @Override
    public List<Tipo> getAll() throws DAOException {
        List<Tipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_tipo = rs.getInt("id_tipo");
                String nome = rs.getString("nome");
                boolean tipoFC = rs.getBoolean("tipo");

                lista.add(new Tipo(id_tipo, nome, tipoFC));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public Tipo getById(int id) throws DAOException {
        Tipo tipo = null;
        String sql = "SELECT * FROM tipo WHERE id_tipo = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id_tipo = rs.getInt("id_tipo");
                    String nome = rs.getString("nome");
                    boolean tipoFC = rs.getBoolean("tipo");

                    tipo = new Tipo(id_tipo, nome, tipoFC);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipo;
    }
}
