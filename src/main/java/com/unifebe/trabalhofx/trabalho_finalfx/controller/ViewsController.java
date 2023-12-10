package com.unifebe.trabalhofx.trabalho_finalfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Classe para realizar o controle das Views do projeto
 * @author Pedro A. Visconti
 */
public class ViewsController {

    private static Scene scene;
    private static Parent root;
    private static Stage stage = new Stage();

    /**
     * Realiza a atualização de uma Stage
     * @param scr Caminho do .fxml
     * @param nome_pagina nome da pagina que sera usada
     * @throws IOException
     * @author Pedro A. Visconti
     */
    @FXML
    public static void trocarTela(String scr, String nome_pagina) throws IOException {
        URL url = new File("src/main/java/com/unifebe/trabalhofx/trabalho_finalfx/view/" + scr).toURI().toURL();
        root = FXMLLoader.load(url);
        scene = new Scene(root);
        stage.setTitle(nome_pagina);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exibe uma nova tela no sistema
     * @param stage Stage que sera usada
     * @param scr caminho do .fxml
     * @param nome_pagina nome da pagina que sera usada
     * @throws IOException
     * @author Pedro A. Visconti
     */
    @FXML
    public static void exibirTela(Stage stage, String scr, String nome_pagina) throws IOException {

        URL url = new File("src/main/java/com/unifebe/trabalhofx/trabalho_finalfx/view/" + scr).toURI().toURL();
        root = FXMLLoader.load(url);
        scene = new Scene(root);
        stage.setTitle(nome_pagina);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Realiza o fachamento da Stage
     * @param stage Stage a ser fechada
     * @author Pedro A. Visconti
     */
    public static void fecharTela(Stage stage){
        stage.close();
    }



}
