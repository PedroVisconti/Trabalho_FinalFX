package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.fecharTela;

public class CadastroProdutoController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private ChoiceBox<Categoria_produto> boxCategoria;
    @FXML
    private TextField txtEstoque;
    @FXML
    private TextField txtValor;


    @FXML
    public void cadastrarProduto() {

        try{

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Estoque estoque = new Estoque();

            String nome, estoque_produto, valor_venda;
            Categoria_produto idCategoria = new Categoria_produto();

            nome = txtNome.getText();
            idCategoria = boxCategoria.getValue();
            estoque_produto = txtEstoque.getText();
            valor_venda = txtValor.getText();

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setIdCategoria(idCategoria.getIdCategoria_produto());
            produto.setValor(Double.parseDouble(valor_venda));

            try{

                produtoDAO.salvarProduto(produto);


            }catch (SQLException e){
                System.out.println("não foi possivel salvar o produto " + produto.getNome());
            }

            try{

                estoque.setIdProduto(produtoDAO.retornaIDProduto(nome));
                estoque.setIdLocal_estoque(1);
                estoque.setQuantidade(Double.parseDouble(estoque_produto));

                EstoqueDAO estoqueDAO = new EstoqueDAO();
                estoqueDAO.salvarEstoque(estoque);

            } catch (SQLException e){
                System.out.println("Um erro ocorre ao salvar o estoque do produto " + produto.getNome());
            }

        }finally {
            fecharTela(LogadoController.getStage());
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Categoria_produtosDAO categorias = new Categoria_produtosDAO();
            List<Categoria_produto> itens = new ArrayList<>();
            categorias = new Categoria_produtosDAO();
            itens = categorias.listarCategorias();
            ObservableList<Categoria_produto> obsList = FXCollections.observableArrayList(itens);
            boxCategoria.setItems(obsList);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
