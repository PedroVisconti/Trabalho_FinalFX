package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Carrinho;
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

/**
 * Classe usada pelo modulo de produtos a serem selecionados para o faturamento
 * @author Pedro A. Visconti
 */
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

    /**
     * Adiciona o item seleciona no carrinho
     * @author Pedro A. Visconti
     */
    public void adicionarCarrinho(){
        produto = tblProdutos.getSelectionModel().getSelectedItem();
        Carrinho.adicionarItem(produto);

    }

    /**
     * Metodo chamado na inicialização da Stage, setando a String do nome do produto na barra de consulta
     * e chamando o metodo mostrarProdutos()
     * @param url
     * @param resourceBundle
     * @author Pedro A. Visconti
     */
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

    /**
     * Exibe a lista de produtos do sistema, levando em consideração o texto digitado na barra de consulta. Caso
     * não seja digitado nada, ira ser mostrado a lista de todos os produtos do sistema
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void mostrarProdutos() throws SQLException {
        String produto = brConsulta.getText();
        if(produto == null){
            carregarListaProdutos(produtoDAO.buscarProdutoALL());
        }else{
            carregarListaProdutos(produtoDAO.buscarProdutoNome(produto));
        }
    }

    /**
     * Realiza a consulta do produto digitado na barra, chamando o metodo mostrarProdutos()
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void realizarConsulta() throws SQLException {
        obsList = null;
        mostrarProdutos();
    }

    /**
     * Metodo para carregar a lista de produtos
     * @param produtos Lista de produtos a ser carregados
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void carregarListaProdutos(List<Produto> produtos) throws SQLException {
        obsList = FXCollections.observableArrayList(produtos);
        tblProdutos.setItems(obsList);

    }
}
