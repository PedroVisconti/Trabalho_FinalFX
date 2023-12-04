package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstoqueDAO extends BancoDeDados {

    private Connection connection;
    private String database = "unifebe";

    public EstoqueDAO() throws SQLException {
        this.connection = conectarBanco(database);
    }

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
