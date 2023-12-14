package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe responsavel pelo controle dos metodos da classe Venda_produto
 * @author Pedro A. Visconti
 */
public interface IVenda_produto {

    public void salvarItensVenda(List<Produto> produtos, int id_venda) throws SQLException;


}
