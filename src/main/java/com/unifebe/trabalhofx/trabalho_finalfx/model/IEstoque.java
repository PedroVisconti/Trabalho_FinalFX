package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.SQLException;

/**
 * Classe responsavel pelo controle da classe EstoqueDAO
 * @author Pedro A. Visconti
 */
public interface IEstoque {

    public void salvarEstoque(Estoque estoque) throws SQLException;

}
