package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.Login;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Carrinho;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.*;
import static com.unifebe.trabalhofx.trabalho_finalfx.model.UsuarioDAO.usuario_logado;

public class LogadoController  implements Initializable {

    private static Stage stage = new Stage();
    private static Carrinho carrinhoItens = new Carrinho();
    private static String consultaBarra;

    @FXML
    private TableView<Produto> tblProdutosVenda;
    @FXML
    private TableColumn<Produto, Integer> clCodigo;
    @FXML
    private TableColumn<Produto, String> clNome;
    @FXML
    private TableColumn<Produto, Double> clValor;
    @FXML
    private TextField txtClienteVenda;
    @FXML
    private TextField txtValorVenda;
    @FXML
    private TextField brConsultaProduto;

    public String getConsultaBarra() {
        return consultaBarra;
    }

    public static Stage getStage() {
        return stage;
    }

    @FXML
    public void telaFaturamento(){
        try{
            if(usuario_logado.getIdTipo() == 1){
                trocarTela("logadoADM-view.fxml","Boas vindas ao sistema Super");
            }else{
                trocarTela("logadoPadrao-view.fxml","Boas vindas ao sistema Super");
            }
        }catch (Exception e){

        }
    }

    @FXML
    public void telaProdutos(){

        try{

            exibirTela(stage, "Produtos-view.fxml", "Produtos");

        }catch (Exception e){

        }



    }

    @FXML
    public void telaCliente(){

        try{

            exibirTela(stage, "Cliente-view.fxml", "Clientes");

        }catch (Exception e){

        }


    }

    public void produtosFaturamento(){

        try{

            consultaBarra = brConsultaProduto.getText();
            exibirTela(stage, "ProdutosFaturamento-view.fxml", "Produtos");

        }catch (Exception e){

        }

    }

     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {

         fecharTela(Login.getStage());
        clCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
        clNome.setCellValueFactory(new PropertyValueFactory<Produto,String>("nome"));
        clValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valor"));

     }

    public void carregarItensCarrinho(){

        ObservableList<Produto> obsList = FXCollections.observableArrayList(carrinhoItens.getItens());
        tblProdutosVenda.setItems(obsList);

        tblProdutosVenda.getSelectionModel().selectedItemProperty();
    }

    public static void adicionarItensCarrinho(Produto produto){
        carrinhoItens.adicionarItem(produto);
        //carregarItensCarrinho(); arrumar para exibir os itens do carrinho
    }


}
