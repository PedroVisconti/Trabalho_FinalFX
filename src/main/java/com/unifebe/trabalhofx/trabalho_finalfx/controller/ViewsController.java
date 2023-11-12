package com.unifebe.trabalhofx.trabalho_finalfx.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ViewsController {


    private static Scene scene;
    private static Parent root;
    private static Stage stage = new Stage();

    @FXML
    public static void trocarTela(String scr, String nome_pagina) throws IOException {

        URL url = new File("src/main/java/com/unifebe/trabalhofx/trabalho_finalfx/view/" + scr).toURI().toURL();
        root = FXMLLoader.load(url);
        scene = new Scene(root);
        stage.setTitle(nome_pagina);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public static void acessarSistema() throws IOException {
        URL url = new File("src/main/java/com/unifebe/trabalhofx/trabalho_finalfx/view/login-view.fxml").toURI().toURL(); //local onde esta a view
        Parent root = FXMLLoader.load(url); //carrega a view
        Scene scene = new Scene(root); //cria a scene com seu tamanho
        stage.setTitle("Sistema Super de gestão"); //nome da aba
        stage.setScene(scene); //seta a scene que ira ser mostrada
        stage.show(); //mostra a scene
    }


}
