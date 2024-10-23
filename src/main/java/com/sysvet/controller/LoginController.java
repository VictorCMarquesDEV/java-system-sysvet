package com.sysvet.controller;

import java.io.IOException;
import com.sysvet.App;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("/view/login");
    }
    @FXML
    private void switchToCadastro() throws IOException {
        App.setRoot("/view/cadastro");
    }
    @FXML
    private void switchToConsultas() throws IOException {
        App.setRoot("/view/consultas");
    }
    @FXML
    private void switchToGerenciar() throws IOException {
        App.setRoot("/view/gerenciar");
    }
    @FXML
    private void switchToInicio() throws IOException {
        App.setRoot("/view/inicio");
    }
    @FXML
    private void switchToMedicamentos() throws IOException {
        App.setRoot("/view/medicamentos");
    }
}
