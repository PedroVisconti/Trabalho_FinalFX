package com.unifebe.trabalhofx.trabalho_finalfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.acessarSistema;

public class Login extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        acessarSistema();

        /*
        URL url = new File("src/main/java/com/unifebe/trabalhofx/trabalho_finalfx/view/login-view.fxml").toURI().toURL(); //local onde esta a view
        Parent root = FXMLLoader.load(url); //carrega a view
        Scene scene = new Scene(root); //cria a scene com seu tamanho
        stage.setTitle("Gerenciamento de Banco de Dados"); //nome da aba
        stage.setScene(scene); //seta a scene que ira ser mostrada
        stage.show(); //mostra a scene
        */

    }

    public static void main(String[] args) {
        launch();
    }


}