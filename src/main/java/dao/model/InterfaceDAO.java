/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.model;

import java.util.List;

/**
 *
 * @author Daniel
 */
public interface InterfaceDAO<T> {

    void save(T obj) throws DAOException;

    void update(T obj) throws DAOException;

    void delete(int id) throws DAOException;
    
    List<T> getAll() throws DAOException;
    
    T getById(int id) throws DAOException;
}
