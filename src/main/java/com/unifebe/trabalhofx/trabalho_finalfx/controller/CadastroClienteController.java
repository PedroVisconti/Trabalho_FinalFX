package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Cliente;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ClienteDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

public class CadastroClienteController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCPF;

    public void cadastrarCliente() throws IOException {

        String nome = txtNome.getText(), CPF = txtCPF.getText();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCPF(CPF);

        ClienteDAO clienteDAO = new ClienteDAO();
        try{

            clienteDAO.cadastrarCliente(cliente);

        }catch (SQLException e){

        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("Cliente " + cliente.getNome() + " cadastrado com sucesso");
        a.show();

        exibirTela(LogadoController.getStage(), "Cliente-view.fxml", "Clientes");


    }

}
