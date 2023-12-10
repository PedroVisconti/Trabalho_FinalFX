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

/**
 * Classe usada para o modulo de produtos do sistema
 * @author Pedro A. Visconti
 */
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


    /**
     * Retorna o produto a ser editado pelo usuario
     * @return produto a ser editado
     * @author Pedro A. Visconti
     */
    public static Produto getProdutoEditar() {
        return produtoEditar;
    }

    /**
     * Exibe a tela de cadastro de produtos do sistema
     * @author Pedro A. Visconti
     */
    @FXML
    public void telaCadastrarProduto(){

        try{

            exibirTela(LogadoController.getStage(), "cadastroProduto-view.fxml", "Cadastro de produto");

        }catch (Exception e){

        }
    }

    /**
     * Apaga um produto do sistema e exibe a lista novamente pelo metodo carregarListaProdutos()
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    @FXML
    public void removerProduto() throws SQLException {

        produtoDAO.removerProduto(tblProdutos.getSelectionModel().getSelectedItem().getIdProduto());
        carregarListaProdutos();

    }

    /**
     * Metodo para exibição da tela de edição de produto, verificando se o usuario selecionaou um produto da lista
     * @throws IOException
     * @author Pedro A. Visconti
     */
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

    /**
     * Meotdo chamado na inicialização da Stage, chamando o metodo carregarListaProdutos()
     * @param url
     * @param resourceBundle
     * @author Pedro A. Visconti
     */
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

    /**
     * Metodo para realizar o carregamento da lista de produtos
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void carregarListaProdutos() throws SQLException {

        produtoDAO = new ProdutoDAO();
        List<Produto> itens = new ArrayList<>();
        itens = produtoDAO.buscarProdutoALL();
        ObservableList<Produto> obsList = FXCollections.observableArrayList(itens);
        tblProdutos.setItems(obsList);

        tblProdutos.getSelectionModel().selectedItemProperty();
    }


}
