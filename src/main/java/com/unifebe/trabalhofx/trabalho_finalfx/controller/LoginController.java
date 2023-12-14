package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;
import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.trocarTela;

/**
 * Classe responsavel pelo controle de login no sitema
 * @author Pedro A. Visconti
 */
public class LoginController {

    UsuarioDAO usuario = new UsuarioDAO();
    @FXML
    private TextField txtusuario;
    @FXML
    private PasswordField txtsenha;
    @FXML
    private Button btnlogin;

    private static Stage stage = new Stage();



    public LoginController() throws SQLException {

    }


    /**
     * Metodo para realizar login no sitema
     * @author Pedro A. Visconti
     */
    @FXML
    public void realizarLogin(){
        try{

            String login, senha;
            login = txtusuario.getText();
            senha = txtsenha.getText();
            String scr;
            String nome_pagina;

            if(usuario.login(login, senha)){
                switch (usuario.getIDUsuario_logado()){
                    case 1:
                        scr = "logadoADM-view.fxml";
                        nome_pagina = "Bem vindo " + usuario.getNomeUsuario_logado() + "" + usuario.getSobrenomeUsuario_logado();
                        trocarTela(scr, nome_pagina);
                        break;
                    case 2:
                        scr = "logadoNORMAL-view.fxml";
                        nome_pagina = "Bem vindo " + usuario.getNomeUsuario_logado() + "" + usuario.getSobrenomeUsuario_logado();
                        trocarTela(scr, nome_pagina);
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

    /**
     * Exibe a tela de cadastro de novos usuarios fora do sistema
     * @author Pedro A. Visconti
     */
    public void realizarCadastro(){

        try{

            exibirTela(stage, "cadastroUsuario-view.fxml", "Cadastrar novo Usuario");

        }catch (IOException e){
            System.out.println("Não foi possivel abrir a tela de cadastro de novo usuario");
        }


    }

    public static Stage getStage() {
        return stage;
    }
}