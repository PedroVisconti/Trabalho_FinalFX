package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.Login;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Carrinho;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Cliente;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Produto;
import com.unifebe.trabalhofx.trabalho_finalfx.model.VendaDAO;
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
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.*;
import static com.unifebe.trabalhofx.trabalho_finalfx.model.UsuarioDAO.usuario_logado;

/**
 * Classe usada para o controle dos modulos ao usuario realizar login no sistema
 * @author Pedro A. Visconti
 */
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

    /**
     * retorna o texto digitado na barra de consulta de produtos da tela de faturamento
     * @return texto digitado da consulta de produto
     * @author Pedro A. Visconti
     */
    public String getConsultaBarra() {
        return consultaBarra;
    }

    /**
     * Retorna uma Stage usada para exibição das telas
     * @return Stage para Controllers
     * @author Pedro A. Visconti
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Exibe a tela de faturamento, levadno em consideração o tipo do usuario logado
     * @author Pedro A. Visconti
     */
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

    /**
     * Exibe a tela de produto do sistema
     * @author Pedro A. Visconti
     */
    @FXML
    public void telaProdutos(){

        try{

            exibirTela(stage, "Produtos-view.fxml", "Produtos");

        }catch (Exception e){

        }



    }

    /**
     * Exibe a tela de cliente do sistema
     * @author Pedro A. Visconti
     */
    @FXML
    public void telaCliente(){

        try{

            exibirTela(stage, "Cliente-view.fxml", "Clientes");

        }catch (Exception e){

        }


    }

    /**
     * Exibe a tela de escolha de produtos a serem faturados no sistema
     * @author Pedro A. Visconti
     */
    @FXML
    public void produtosFaturamento(){

        try{

            consultaBarra = brConsultaProduto.getText();
            brConsultaProduto.setText("");
            exibirTela(stage, "ProdutosFaturamento-view.fxml", "Produtos");

        }catch (Exception e){

        }

    }

    /**
     * Exibe a tela de escolha de clientes do sistema
     * @author Pedro A. Visconti
     */
    @FXML
    public void clienteFaturamento(){

        try{

            exibirTela(stage, "ClienteFaturamento-view.fxml", "Clientes");

        }catch (Exception e){

        }

    }

    /**
     * Metodo para realziar o faturamento do carrinho de itens
     * @throws SQLException
     */
    public void faturar() throws SQLException {
        VendaDAO venda = new VendaDAO();
        venda.salvarVenda(carrinhoItens);
    }

    /**
     * Metodo chamado na inicialização da Stage
     * @param url
     * @param resourceBundle
     * @author Pedro A. Visconti
     */
     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {

         fecharTela(Login.getStage());
         clCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
         clNome.setCellValueFactory(new PropertyValueFactory<Produto,String>("nome"));
         clValor.setCellValueFactory(new PropertyValueFactory<Produto, Double>("valor"));

     }

    /**
     * Metodo para carregar os itens no carrinho no carrinho de compra, são chamados também os metodos
     * calcularValorVenda() e atualizarClienteVenda()
     * @author Pedro A. Visconti
     */
    public void carregarItensCarrinho(){
        List<Produto> itens = carrinhoItens.getItens();
        ObservableList<Produto> obsList = FXCollections.observableArrayList(itens);
        tblProdutosVenda.setItems(obsList);
        tblProdutosVenda.getSelectionModel().selectedItemProperty();

        calcularValorVenda(obsList);
        atualizarClienteVenda();

    }

    /**
     * Realizado o calculo da lista de produtos recebidas, levando em consideração o valorVenda do produto
     * @param lista ObservableList de produtos
     * @author Pedro A. Visconti
     */
    public void calcularValorVenda(ObservableList<Produto> lista){
        Double valorVenda = 0.00;
        for (Produto produto : lista) {
            valorVenda += clValor.getCellObservableValue(produto).getValue();
        }
        txtValorVenda.setText(String.valueOf(valorVenda));
        carrinhoItens.setValor_venda(valorVenda);
    }

    /**
     * Atualiza o txtClienteVenda, setando o nome do cliente definido na venda
     * @author Pedro A. Visconti
     */
    public void atualizarClienteVenda(){
        if(carrinhoItens.getCliente() != null){
            txtClienteVenda.setText(carrinhoItens.getCliente().getNome());
        }else{
            txtClienteVenda.setText("Sem cliente");
        }
    }

}
