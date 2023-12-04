package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Usuario;
import com.unifebe.trabalhofx.trabalho_finalfx.model.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.trocarTela;

public class CadastroUsuarioController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnCadastrar;


    /**
     * Metodo para realizar o cadastro do usuario atraves da tela de login do sistema
     * pegando os dados inseridos e gerando um objeto do tipo usuario que ira ser salvo no banco de dados
     */
    public void realizarCadastro() {

        try{

            String nome, sobrenome, usuario, senha;

            nome = txtNome.getText();
            sobrenome = txtSobrenome.getText();
            usuario = txtUsuario.getText();
            senha = txtSenha.getText();

            Usuario u = new Usuario(nome, sobrenome, usuario, senha, 2);

            try{
                UsuarioDAO uDAO = new UsuarioDAO();
                uDAO.salvarUsuario(u);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setAlertType(Alert.AlertType.CONFIRMATION);
                a.setContentText("Usuario " + usuario + " cadastrado com sucesso");
                a.show();
                System.out.println("Usuario " + usuario + "Salvo com sucesso");

            }catch (SQLException e){

                System.out.println("Não foi possivel salvar o usuario no banco");

            }
        }finally {

            try{

                trocarTela("login-view.fxml","Sistema Super de gestão");

            }catch (IOException e){

            }
        }




    }



}
