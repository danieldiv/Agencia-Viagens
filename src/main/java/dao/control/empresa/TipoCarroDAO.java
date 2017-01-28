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
import model.empresa.TipoCarro;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class TipoCarroDAO implements InterfaceDAO<TipoCarro> {

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
    public void save(TipoCarro obj) throws DAOException {
        //Não utilizado
    }

    @Override
    public void update(TipoCarro obj) throws DAOException {
        //Não utilizado
    }

    @Override
    public void delete(int id) throws DAOException {
        //Não utilizado
    }

    @Override
    public List<TipoCarro> getAll() throws DAOException {
        List<TipoCarro> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_carro";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_tipoCarro = rs.getInt("id_tipocarro");
                String nome = rs.getString("nome");

                lista.add(new TipoCarro(id_tipoCarro, nome));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoCarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public TipoCarro getById(int id) throws DAOException {
        TipoCarro tipoCarro = null;
        String sql = "SELECT * FROM tipo_carro WHERE id_tipocarro = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id_tipoCarro = rs.getInt("id_tipocarro");
                    String nome = rs.getString("nome");

                    tipoCarro = new TipoCarro(id_tipoCarro, nome);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoCarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tipoCarro;
    }

}
