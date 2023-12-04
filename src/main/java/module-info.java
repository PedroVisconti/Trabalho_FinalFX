module com.unifebe.trabalhofx.trabalho_finalfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.unifebe.trabalhofx.trabalho_finalfx to javafx.fxml;
    exports com.unifebe.trabalhofx.trabalho_finalfx;
    exports com.unifebe.trabalhofx.trabalho_finalfx.controller;
    opens com.unifebe.trabalhofx.trabalho_finalfx.controller to javafx.fxml;
    opens com.unifebe.trabalhofx.trabalho_finalfx.model to javafx.fxml;
    exports com.unifebe.trabalhofx.trabalho_finalfx.model;
}