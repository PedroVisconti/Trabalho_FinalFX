package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Produto;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

public class ProdutosController  implements Initializable {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    private static Produto produtoEditar = new Produto();

    @FXML
    private TextField brConsulta;
    @FXML
    private Button btnConsulta;
    @FXML
    private Button btnApagarProduto;
    @FXML
    private TableView<Produto> tblProdutos;
    @FXML
    private TableColumn<Produto, Integer> clCodigo;
    @FXML
    private TableColumn<Produto, String> clNome;
    @FXML
    private TableColumn<Produto, Double> clValor;


    public static Produto getProdutoEditar() {
        return produtoEditar;
    }

    @FXML
    public void telaCadastrarProduto(){

        try{

            exibirTela(LogadoController.getStage(), "cadastroProduto-view.fxml", "Cadastro de produto");

        }catch (Exception e){

        }
    }

    @FXML
    public void removerProduto() throws SQLException {

        produtoDAO.removerProduto(tblProdutos.getSelectionModel().getSelectedItem().getIdProduto());
        carregarListaProdutos();

    }

    @FXML
    public void editarProduto() throws IOException {

        produtoEditar = tblProdutos.getSelectionModel().getSelectedItem();
        if(produtoEditar != null){
            exibirTela(LogadoController.getStage(), "alterarProduto-view.fxml" , "Editar de produto");
        }else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Selecione um produto");
            a.show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{

            clCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
            clNome.setCellValueFactory(new PropertyValueFactory<Produto,String>("nome"));
            clValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valor_venda"));

            carregarListaProdutos();

        }catch (Exception e){

        }


    }

    public void carregarListaProdutos() throws SQLException {

        produtoDAO = new ProdutoDAO();
        List<Produto> itens = new ArrayList<>();
        itens = produtoDAO.buscarProdutoALL();
        ObservableList<Produto> obsList = FXCollections.observableArrayList(itens);
        tblProdutos.setItems(obsList);

        tblProdutos.getSelectionModel().selectedItemProperty();
    }


}
