package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.Login;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Cliente;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

public class ClienteController implements Initializable {

    private ClienteDAO clienteDAO= new ClienteDAO();
    private static Cliente clienteEditar = new Cliente();

    @FXML
    private TableView<Cliente> tblCliente;
    @FXML
    private TableColumn<Cliente, Integer> clID;
    @FXML
    private TableColumn<Cliente, String> clNome;
    @FXML
    private TableColumn<Cliente, String > clCPF;


    public static Cliente getClienteEditar() {
        return clienteEditar;
    }


    @FXML
    public void cadastrarCliente() throws IOException {

        exibirTela(LogadoController.getStage(), "cadastroCliente-view.fxml", "Cadastrar Cliente" );

    }

    @FXML
    public void removerCliente() throws SQLException {

        clienteDAO.apagarCliente(tblCliente.getSelectionModel().getSelectedItem().getIdCliente());
        carregarLista();

    }

    @FXML
    public void alterarCliente() throws IOException {

        clienteEditar = tblCliente.getSelectionModel().getSelectedItem();
        if(clienteEditar != null){
            exibirTela(LogadoController.getStage(), "alterarCliente-view.fxml" , "Editar Cliente");
        }else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Selecione um cliente");
            a.show();
        }
    }

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

    public void carregarLista() throws SQLException {

        clienteDAO = new ClienteDAO();
        List<Cliente> itens = new ArrayList<>();
        itens = clienteDAO.consultarClientesALL();
        ObservableList<Cliente> obsList = FXCollections.observableArrayList(itens);
        tblCliente.setItems(obsList);

    }
}
