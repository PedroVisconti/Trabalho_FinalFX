package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Produto;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class ProdutosFaturamentoController implements Initializable {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private Produto produto = new Produto();
    private LogadoController lc = new LogadoController();
    private ObservableList<Produto> obsList;

    @FXML
    private TableView<Produto> tblProdutos;
    @FXML
    private TableColumn<Produto, Integer> clCodigo;
    @FXML
    private TableColumn<Produto, String> clNome;
    @FXML
    private TableColumn<Produto, Double> clValor;
    @FXML
    private TextField brConsulta;

    public void adicionarCarrinho(){
        produto = tblProdutos.getSelectionModel().getSelectedItem();
        //LogadoController logado = new LogadoController();
        LogadoController.adicionarItensCarrinho(produto);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{

            tblProdutos.getSelectionModel().selectedItemProperty();
            clCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
            clNome.setCellValueFactory(new PropertyValueFactory<Produto,String>("nome"));
            clValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valor_venda"));
            tblProdutos.getSelectionModel().selectedItemProperty();

            brConsulta.setText(lc.getConsultaBarra());
            mostrarProdutos();


        }catch (Exception e){

        }


    }

    public void mostrarProdutos() throws SQLException {
        String produto = brConsulta.getText();
        if(produto == null){
            carregarListaProdutos(produtoDAO.buscarProdutoALL());
        }else{
            carregarListaProdutos(produtoDAO.buscarProdutoNome(produto));
        }
    }

    public void realizarConsulta() throws SQLException {
        obsList = null;
        mostrarProdutos();
    }

    public void carregarListaProdutos(List<Produto> produtos) throws SQLException {
        obsList = FXCollections.observableArrayList(produtos);
        tblProdutos.setItems(obsList);

    }
}
