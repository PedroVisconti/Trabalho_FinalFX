package com.unifebe.trabalhofx.trabalho_finalfx.model;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Classe para controle da tabela Vendas do banco de dados
 * @extend Banco de Dados
 * @author Pedro A. Visconti
 */
public class VendaDAO extends BancoDeDados {

    private Connection connection;
    private String database = "unifebe";

    /**
     * Construtor da classe, chamando o metodo conectarBanco()
     * @author Pedro A. Visconti
     */
    public VendaDAO() {
        this.connection = conectarBanco(database);
    }

    /**
     * Metodo para salvar a venda no banco de dados
     * @param carrinho carrinho da venda
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void salvarVenda(Carrinho carrinho) throws SQLException {
        PreparedStatement prts = null;
        java.util.Date data = new java.util.Date();
        java.sql.Date dataSql = new java.sql.Date(data.getTime());
        try {
            String SQL = "INSERT INTO tbvenda (data_venda, valor, idCliente) VALUES (?,?,?)";
            prts = connection.prepareStatement(SQL);

            prts.setString(1, String.valueOf(dataSql));
            prts.setDouble(2, carrinho.getValor_venda());
            prts.setDouble(3, carrinho.getCliente().getIdCliente());

            prts.executeUpdate();
            Venda_produtoDAO vp = new Venda_produtoDAO();
            vp.salvarItensVenda(carrinho.getItens(), retornaIDVenda());


            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setContentText("Venda salva");
            a.show();

        } catch (Exception e) {
            System.out.println("| Um erro ocorreu no cadastro da venda |");
            System.out.println(e);
        } finally {
            prts.close();
        }

    }

    /**
     * Metodo para retornar o ID da ultima venda
     * @return retorna o ID da ultima venda
     * @author Pedro A. Visconti
     */
    private int retornaIDVenda() {

        String SQL;
        PreparedStatement query = null;
        ResultSet rs = null;

        try {

            SQL = "SELECT MAX(idvenda) FROM tbvenda";
            query = connection.prepareStatement(SQL);
            rs = query.executeQuery();
            while(rs.next()){

                int resultado = rs.getInt("MAX(idvenda)");
                return resultado;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
