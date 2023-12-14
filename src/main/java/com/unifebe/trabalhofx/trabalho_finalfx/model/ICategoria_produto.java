package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe responsavel pelo controle da classe Categoria_produto
 * @author Pedro A. Visconti
 */
public interface ICategoria_produto {

    public void Categoria_produtosDAO() throws SQLException;
    public List<Categoria_produto> listarCategorias() throws SQLException;
    public Categoria_produto retornarCategoria(int id) throws SQLException;

}
