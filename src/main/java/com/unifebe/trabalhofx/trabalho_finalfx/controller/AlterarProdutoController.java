package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Categoria_produto;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Categoria_produtosDAO;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Produto;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ProdutosController.getProdutoEditar;
import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

/**
 * Classe usada para alteração dos dados de produto pelo alterarProduto-view.fxml
 * @author Pedro A. Visconti
 */
public class AlterarProdutoController implements Initializable {

    private Produto produtoEditando;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private Categoria_produtosDAO categoria = new Categoria_produtosDAO();

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNome;
    @FXML
    private ChoiceBox<Categoria_produto> boxCategoria;
    @FXML
    private TextField txtValor;

    /**
     * Construtor da classe AlterarProdutoController
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public AlterarProdutoController() throws SQLException {

    }

    /**
     * Metodo usado para mostrar os dados dos produtos que serão editadosw
     * @author Pedro A. Visconti
     */
    public void exibirDadosProdutoEditar(){

        try{
            produtoEditando = getProdutoEditar();

            txtCodigo.setText(Integer.toString(produtoEditando.getCodigo()));
            txtNome.setText(produtoEditando.getNome());
            boxCategoria.setValue(categoria.retornarCategoria(produtoEditando.getIdCategoria()));
            txtValor.setText(Double.toString(produtoEditando.getValor_venda()));

        }catch (Exception e){

        }

    }

    /**
     * Carrega as categorias do produtos
     * @author Pedro A. Visconti
     */

    public void carregarCategorias(){
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

    /**
     * Metodo para salvar as alterações realizadas no produto
     * @throws IOException
     * @author Pedro A. Visconti
     */

    public void salvarAlteracao() throws IOException {

        String nome, valor_venda;
        Categoria_produto idCategoria = new Categoria_produto();

        nome = txtNome.getText();
        idCategoria = boxCategoria.getValue();
        valor_venda = txtValor.getText();

        produtoEditando.setNome(nome);
        produtoEditando.setIdCategoria(idCategoria.getIdCategoria_produto());
        produtoEditando.setValor(Double.parseDouble(valor_venda));

        try{
            produtoDAO.atualizarProduto(produtoEditando);

        }catch (SQLException e){

        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("produto " + produtoEditando.getNome() + " alterado");
        a.show();

        exibirTela(LogadoController.getStage(), "Produtos-view.fxml", "Produtos");

    }

    /**
     * Chamado na inicialização da Scene, para realizar o carregamento das categorias e os dados dos produtos a ser editado
     * @param url
     * @param resourceBundle
     * @author Pedro A. Visconti
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carregarCategorias();
        exibirDadosProdutoEditar();


    }


}
