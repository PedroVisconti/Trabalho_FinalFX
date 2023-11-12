package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Usuario;
import com.unifebe.trabalhofx.trabalho_finalfx.model.usuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventObject;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.trocarTela;

public class LoginController {

    usuarioDAO usuario = new usuarioDAO();
    @FXML
    private TextField txtusuario;
    @FXML
    private PasswordField txtsenha;
    @FXML
    private Button btnlogin;

    public LoginController() throws SQLException {

    }


    @FXML
    public void realizarLogin(){
        try{

            String login, senha;
            login = txtusuario.getText();
            senha = txtsenha.getText();

            if(usuario.login(login, senha)){
                switch (usuario.getIDUsuario_logado()){
                    case 1:
                        String scr = "logadoADM-view.fxml";
                        String nome_pagina = "Bem vindo " + usuario.getNomeUsuario_logado() + "" + usuario.getSobrenomeUsuario_logado();
                        trocarTela(scr, nome_pagina);
                        break;
                    case 2:

                        break;
                }
            }else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Acesso negagado");
                a.show();
            }

        }catch (Exception e){
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Um erro ocorreu");
            a.show();

        }
    }

}