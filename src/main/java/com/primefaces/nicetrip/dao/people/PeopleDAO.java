/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.nicetrip.dao.people;

import com.primefaces.nicetrip.dao.DAOException;
import com.primefaces.nicetrip.dao.InterfaceDAO;
import com.primefaces.nicetrip.model.People;
import com.primefaces.nicetrip.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class PeopleDAO implements InterfaceDAO<People> {

    public Connection geraConexao() throws DAOException {
        Connection conn;
        try {
            conn = ConnectionFactory.getConnection("localhost", "3306", "nicetrip", "root", "kggjfq377d6f");
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return conn;
    }

    @Override
    public void save(People obj) throws DAOException {
        String sql = "INSERT INTO tb_pessoa (nome, cpf, rg, certidaoNasc, dataNasc, dataCad) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date dataC = new java.sql.Date(obj.getDataCad().getTime());
            java.sql.Date dataN = new java.sql.Date(obj.getDataNasc().getTime());

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getRg());
            ps.setString(4, obj.getCertidaoNasc());
            ps.setDate(5, (Date) dataN);
            ps.setDate(6, (Date) dataC);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PeopleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(People obj) throws DAOException {
        String sql = "UPDATE tb_pessoa SET nome = ?, cpf = ?, rg = ?, "
                + "certidaoNasc = ?, dataNasc = ?, dataCad = ? WHERE id_pessoa = ? ";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            java.sql.Date dataC = new java.sql.Date(obj.getDataCad().getTime());
            java.sql.Date dataN = new java.sql.Date(obj.getDataNasc().getTime());

            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getCpf());
            ps.setString(3, obj.getRg());
            ps.setString(4, obj.getCertidaoNasc());
            ps.setDate(5, (Date) dataN);
            ps.setDate(6, (Date) dataC);
            ps.setInt(7, obj.getId_pessoa());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PeopleDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PeopleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<People> getAll() throws DAOException {
        List<People> lista = new ArrayList<>();

        String sql = "SELECT * FROM tb_pessoa WHERE responsavel is null ORDER BY nome";

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

                if (!cpf.isEmpty()) {
                    cpf = montarCpf(cpf);
                }

                lista.add(new People(id_pessoa, null, nome, cpf, rg, certidaoNasc, dataNasc, dataCad));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeopleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<People> getAllDep() throws DAOException {
        List<People> lista = new ArrayList<>();

        String sql = "SELECT * FROM tb_pessoa WHERE responsavel is not null";

        try (Connection conn = geraConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                People responsavel;

                int id_pessoa = rs.getInt("id_pessoa");
                int id_responsavel = rs.getInt("responsavel");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                Date dataNasc = rs.getDate("dataNasc");
                Date dataCad = rs.getDate("dataCad");
                String certidaoNasc = rs.getString("certidaoNasc");

                responsavel = getById(id_responsavel);

                if (!cpf.isEmpty()) {
                    cpf = montarCpf(cpf);
                }

                lista.add(new People(id_pessoa, responsavel, nome, cpf, rg, certidaoNasc, dataNasc, dataCad));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeopleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public People getById(int id) throws DAOException {
        People people = null;

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

                    if (!cpf.isEmpty()) {
                        cpf = montarCpf(cpf);
                    }

                    people = new People(id_pessoa, null, nome, cpf, rg, certidaNasc, dataNasc, dataCad);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeopleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return people;
    }

    /**
     * Converte o CPF padrão para o CPF com mascara
     *
     * @param cpf
     * @return
     */
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
