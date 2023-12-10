package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Carrinho;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Cliente;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.fecharTela;

/**
 * Classe usada para a seleção do cleinte que ira ser definido para a venda
 * @author Pedro A. Visconti
 */
public class ClienteFaturamento implements Initializable {

    private ClienteDAO clienteDAO= new ClienteDAO();
    private Cliente clienteFaturamento = new Cliente();

    @FXML
    private TableView<Cliente> tblCliente;
    @FXML
    private TableColumn<Cliente, Integer> clID;
    @FXML
    private TableColumn<Cliente, String> clNome;
    @FXML
    private TableColumn<Cliente, String > clCPF;

    /**
     * Seleciona o cliente e seta no carrinho
     * @author Pedro A. Visconti
     */
    public void escolherCliente(){
        clienteFaturamento = tblCliente.getSelectionModel().getSelectedItem();
        Carrinho.setCliente(clienteFaturamento);
        fecharTela(LogadoController.getStage());
    }

    /**
     * Metodo chamdo na inicialização da Stage, chamado o metodo carregarLista()
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clID.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        clNome.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nome"));
        clCPF.setCellValueFactory(new PropertyValueFactory<Cliente, String >("CPF"));


        try {
           carregarLista();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Metodo para realização do carregamento da lista de clientes do sistema
     * @throws SQLException
     * @author Pédro A. Visconti
     */
    public void carregarLista() throws SQLException {

        clienteDAO = new ClienteDAO();
        List<Cliente> itens = new ArrayList<>();
        itens = clienteDAO.consultarClientesALL();
        ObservableList<Cliente> obsList = FXCollections.observableArrayList(itens);
        tblCliente.setItems(obsList);

    }
}

