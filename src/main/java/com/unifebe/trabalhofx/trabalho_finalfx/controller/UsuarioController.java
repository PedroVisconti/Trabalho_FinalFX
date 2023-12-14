package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import com.unifebe.trabalhofx.trabalho_finalfx.model.Cliente;
import com.unifebe.trabalhofx.trabalho_finalfx.model.ClienteDAO;
import com.unifebe.trabalhofx.trabalho_finalfx.model.Usuario;
import com.unifebe.trabalhofx.trabalho_finalfx.model.UsuarioDAO;
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
import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.trocarTela;

/**
 * Classe usada para o controle de usuarios no sistema
 * @author Pedro A. Visconti
 */
public class UsuarioController implements Initializable {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static Usuario usuarioEditar = new Usuario();

    @FXML
    private TableView<Usuario> tblUsuario;
    @FXML
    private TableColumn<Usuario, Integer> clID;
    @FXML
    private TableColumn<Usuario, String> clNome;
    @FXML
    private TableColumn<Usuario, String > clLogin;

    public UsuarioController() throws SQLException {
    }

    public static Usuario getUsuarioEditar() {
        return usuarioEditar;
    }

    /**
     * Metodo para exibir a tela de cadastro de usuarios no sitema
     * @throws IOException
     * @author Pedro A. Visconti
     */
    public void cadastrarUsuario() throws IOException {

        trocarTela("cadastroUsuario-view.fxml", "Cadastrar Usuarios");
    }

    /**
     * Metodo para apagar um cliente do sistema
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void apagarUsuario() throws SQLException {
        usuarioDAO.apagarUsuario(tblUsuario.getSelectionModel().getSelectedItem().getIdUsuario());
        carregarLista();

    }

    /**
     * Metodo para alterar os dados de usuario
     * @throws IOException
     * @author Pedro A. Visconti
     */
    public void editarUsuario() throws IOException {

        usuarioEditar = tblUsuario.getSelectionModel().getSelectedItem();
        if(usuarioEditar != null){
            exibirTela(LogadoController.getStage(), "alterarCadastroUsuario-view.fxml" , "Editar Usuario");
        }else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Selecione um usuario");
            a.show();
        }

    }


    /**
     * Metodo chamado na inicialização da Stage
     * @param url
     * @param resourceBundle
     * @author Pedro A. Visconti
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clID.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idUsuario"));
        clNome.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nome"));
        clLogin.setCellValueFactory(new PropertyValueFactory<Usuario, String >("login"));

        tblUsuario.getSelectionModel().selectedItemProperty();

        try {
            carregarLista();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            carregarLista();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo para carregar a lista de usuarios
     * @throws SQLException
     * @author Pedro A. Visconti
     */
    public void carregarLista() throws SQLException {

        List<Usuario> itens = new ArrayList<>();
        itens = usuarioDAO.retornarUsuarios();
        ObservableList<Usuario> obsList = FXCollections.observableArrayList(itens);
        tblUsuario.setItems(obsList);

    }

}
