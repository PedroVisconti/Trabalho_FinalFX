package com.unifebe.trabalhofx.trabalho_finalfx;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import static com.unifebe.trabalhofx.trabalho_finalfx.controller.ViewsController.exibirTela;

public class Login extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        exibirTela(stage, "login-view.fxml", "Sistema Super de gestão");

    }

    public static void main(String[] args) {
        launch();
    }


}