package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;

/**
 * Interface contendo os metodos para o controle da classe BancoDeDados
 * @author Pedro A. Visconti
 */
public interface IBancoDeDados {

    public Connection conectarBanco(String database);

}