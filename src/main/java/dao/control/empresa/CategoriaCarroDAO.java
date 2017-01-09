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
import model.empresa.CategoriaCarro;
import util.ConnectionFactory;

/**
 *
 * @author Daniel
 */
public class CategoriaCarroDAO implements InterfaceDAO<CategoriaCarro> {

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
    public void save(CategoriaCarro obj) throws DAOException {
        //Não utilizado
    }

    @Override
    public void update(CategoriaCarro obj) throws DAOException {
        //Não utilizado
    }

    @Override
    public void delete(int id) throws DAOException {
        //Não utilizado
    }

    @Override
    public List<CategoriaCarro> getAll() throws DAOException {
        List<CategoriaCarro> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria_carro";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_categoriaCarro = rs.getInt("id_categoriacarro");
                String nome = rs.getString("nome");

                lista.add(new CategoriaCarro(id_categoriaCarro, nome));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public CategoriaCarro getById(int id) throws DAOException {
        CategoriaCarro categoriaCarro = null;
        String sql = "SELECT * FROM categoria_carro WHERE id_categoriacarro = ?";

        try (Connection conn = geraConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id_categoriaCarro = rs.getInt("id_categoriacarro");
                    String nome = rs.getString("nome");

                    categoriaCarro = new CategoriaCarro(id_categoriaCarro, nome);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoriaCarro;
    }

}
