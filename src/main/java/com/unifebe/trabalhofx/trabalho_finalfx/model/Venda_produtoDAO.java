package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe para controle da tabela venda_produto
 * @extend BancoDeDaods
 * @author Pedro A. Visconti
 */
public class Venda_produtoDAO extends BancoDeDados implements IVenda_produto {

    private Connection connection;
    private String database = "unifebe";

    /**
     * Construtor da classe, realizando a conexão com o banco pelo metodo conectarBanco()
     * @author Pedro A. Visconti
     */
    public Venda_produtoDAO(){
        this.connection = conectarBanco(database);
    }

    /**
     * Salva os itens da venda no banco de dados
     * @param produtos lista de produtos da venda
     * @param id_venda id da venda
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void salvarItensVenda(List<Produto> produtos, int id_venda) throws SQLException {

        String SQL = "INSERT into tbvenda_produto (idVenda, idProduto, preco_venda) VALUES (?,?,?)";
        PreparedStatement prts = null;
        try {

            for(Produto produto : produtos){
                prts = connection.prepareStatement(SQL);
                prts.setInt(1, id_venda);
                prts.setInt(2, produto.getIdProduto());
                prts.setDouble(3, produto.getValor_venda());

                prts.executeUpdate();
            }


        } catch (Exception e) {
            System.out.println("| Um erro ocorreu no cadastro dos itens da venda |");
            System.out.println(e);
        } finally {
            prts.close();
        }




    }
}
