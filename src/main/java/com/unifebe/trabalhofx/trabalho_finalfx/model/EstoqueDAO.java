package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe para controle da tabela estoque
 * @extends Banco de Dados
 * @author Pedro A. Visconti
 */
public class EstoqueDAO extends BancoDeDados implements IEstoque {

    private Connection connection;
    private String database = "unifebe";

    /**
     * Construtor da classe EstoqueDAO, chamando o metodo conectarBanco()
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public EstoqueDAO() throws SQLException {
        this.connection = conectarBanco(database);
    }

    /**
     * Metodo para salvar o estoque do produto no banco de dados
     * @param estoque estoque a ser salvo
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void salvarEstoque(Estoque estoque) throws SQLException {
        PreparedStatement prts = null;
        try {
            String SQL = "INSERT INTO tbestoque (idProduto, idLocal_estoque, quantidade) VALUES (?,?,?)";
            prts = connection.prepareStatement(SQL);
            prts.setInt(1, estoque.getIdProduto());
            prts.setInt(2, estoque.getIdLocal_estoque());
            prts.setDouble(3, estoque.getQuantidade());
            prts.executeUpdate();
            System.out.println("Estoque do produto " + estoque.getIdProduto() + " salvo" );
        } catch (Exception e) {
            System.out.println("| Um erro ocorreu no cadastro do produto |");
        } finally {
            prts.close();
        }

    }




}
