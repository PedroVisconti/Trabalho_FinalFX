package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ClienteController.getClienteEditar;
import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

public class AlterarClienteController implements Initializable {

    private Cliente clientEditando;
    private ClienteDAO clienteDAO = new ClienteDAO();

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;


    public void salvarAlteracao() throws IOException {

        String nome, CPF;

        nome = txtNome.getText();
        CPF = txtCPF.getText();

        clientEditando.setNome(nome);
        clientEditando.setCPF(CPF);

        try{
            clienteDAO.alterarCliente(clientEditando);

        }catch (SQLException e){

        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Cliente " + clientEditando.getNome() + " alterado");
        a.show();

        exibirTela(LogadoController.getStage(), "Cliente-view.fxml", "Clientes");

    }

    public void exibirDadosProdutoEditar(){

        try{

            clientEditando = getClienteEditar();

            txtNome.setText(clientEditando.getNome());
            txtCPF.setText(clientEditando.getCPF());


        }catch (Exception e){

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exibirDadosProdutoEditar();
    }
}
