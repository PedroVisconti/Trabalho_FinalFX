package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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
    public static void exibirTela(Stage stage, String scr, String nome_pagina) throws IOException {

        URL url = new File("src/main/java/com/unifebe/trabalhofx/trabalho_finalfx/view/" + scr).toURI().toURL();
        root = FXMLLoader.load(url);
        scene = new Scene(root);
        stage.setTitle(nome_pagina);
        stage.setScene(scene);
        stage.show();

    }

    public static void fecharTela(Stage stage){
        stage.close();
    }



}
