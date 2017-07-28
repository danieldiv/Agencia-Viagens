/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primefaces.nicetrip.utils;

import com.primefaces.nicetrip.dao.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class ConnectionFactory {

    public static Connection getConnection(String server, String port, String db, String user, String password)
            throws DAOException {
        String url = "jdbc:mysql://" + server + ":" + port + "/" + db;
        try {
            //Ajuda a identificar a falta do driver JDBC
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DAOException("Impossívelconectar-se a url: " + url + "?user=" + user + "&password=" + password, e);
        }
    }       
    
}
