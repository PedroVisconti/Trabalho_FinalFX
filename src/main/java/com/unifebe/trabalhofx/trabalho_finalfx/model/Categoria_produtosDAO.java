package com.unifebe.trabalhofx.trabalho_finalfx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para controle da tabela Categoria_produto no banco de dados
 * @extends Banco de Dados
 * @author Pedro A. Visconti
 */
public class Categoria_produtosDAO extends BancoDeDados implements ICategoria_produto {

    private Connection connection;
    private String database = "unifebe";

    /**
     * Construtor da classe, chamando o metodo conectarBanco() para realizar a conexão
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void Categoria_produtosDAO() throws SQLException {
        this.connection = conectarBanco(database);
    }

    /**
     * Metodo para recurar do banco de dados as categorias de produto
     * @return lista de categorias
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public List<Categoria_produto> listarCategorias() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Categoria_produto> categoriaList = new ArrayList<Categoria_produto>();

        try{

            String SQL = "SELECT * FROM tbcategoria_produto";
            pstmt = connection.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()){
                Categoria_produto categoria = new Categoria_produto();
                categoria.setIdCategoria_produto(rs.getInt("idcategoria_produto"));
                categoria.setNome_categorai(rs.getString("nome_categoria"));
                categoriaList.add(categoria);
            }

            return categoriaList;


        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            pstmt.close();
            rs.close();
        }


    }

    /**
     * Retorna uma categoria com base no seu ID
     * @param id id da categoria
     * @return Categoria do ID
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public Categoria_produto retornarCategoria(int id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Categoria_produto categoria = new Categoria_produto();

        try{

            String SQL = "SELECT * FROM tbcategoria_produto WHERE idcategoria_produto = " + id;
            pstmt = connection.prepareStatement(SQL);
            rs = pstmt.executeQuery();

            while (rs.next()){
                categoria.setIdCategoria_produto(rs.getInt("idcategoria_produto"));
                categoria.setNome_categorai(rs.getString("nome_categoria"));
            }

            return categoria;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            pstmt.close();
            rs.close();
        }

    }

}
