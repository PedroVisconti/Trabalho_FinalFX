package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.UsuarioController.getUsuarioEditar;
import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

/**
 * Classe para realizar o controle da edição de usuarios
 * @author Pedro A. Visconti
 */
public class AlterarUsuarioController implements Initializable {

    private Usuario usuarioEditando;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;

    /**
     * Construtor da classe AlterarUsuarioController
     * @throws SQLException
     * @author Pedro A. Visconti
     */

    public AlterarUsuarioController() throws SQLException {
    }

    /**
     * Metodo usado para mostrar os dados do usuario que serão editadosw
     * @author Pedro A. Visconti
     */
    public void exibirDadosUsuarioEditar(){

        try{
            usuarioEditando = getUsuarioEditar();

            txtNome.setText(usuarioEditando.getNome());
            txtSobrenome.setText((usuarioEditando.getSobrenome()));
            txtUsuario.setText(usuarioEditando.getLogin());

        }catch (Exception e){

        }

    }

    /**
     * Metodo para salvar as alterações realizadas no usuario
     * @throws IOException
     * @author Pedro A. Visconti
     */
    public void salvarAlteracao() throws IOException {

        String nome, sobrenome, usuario, senha;

        nome = txtNome.getText();
        sobrenome = txtSobrenome.getText();
        usuario = txtUsuario.getText();
        senha = txtSenha.getText();

        usuarioEditando.setNome(nome);
        usuarioEditando.setSobrenome(sobrenome);
        usuarioEditando.setLogin(usuario);
        usuarioEditando.setSenha(senha);

        try{

            usuarioDAO.atualizarUsuario(usuarioEditando);

        }catch (SQLException e){

        }

        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText("produto " + usuarioEditando.getNome() + " alterado");
        a.show();

        exibirTela(LogadoController.getStage(), "Usuarios-view.fxml", "Usuario");

    }

    /**
     * Chamado na inicialização da Scene, para realizar o carregamento das categorias e os dados dos usuarios a ser editado
     * @param url
     * @param resourceBundle
     * @author Pedro A. Visconti
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        exibirDadosUsuarioEditar();


    }

}
