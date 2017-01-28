/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.model;

import java.util.List;

/**
 *
 * @author Daniel
 */
public interface InterfaceBean<T> {

    void salvar();

    void atualizar();

    void excluir();

    List<?> buscarTodos();

}
