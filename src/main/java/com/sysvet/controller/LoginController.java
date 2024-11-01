package com.sysvet.controller;

import java.io.IOException;
import com.sysvet.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField loginUser; // Referência ao campo de texto do usuário
    @FXML
    private PasswordField loginPassword; // Referência ao campo de senha

    @FXML
    private void validateLogin() throws IOException {

        if (loginUser.getText().isEmpty() || loginPassword.getText().isEmpty()) {
            showAlert("Campos obrigatórios", "Por favor, preencha todos os campos.");
            return; // Interrompe a execução se os campos estiverem vazios
        }

        App.setRoot("/view/inicio");
    }

    // Método para mostrar um alerta
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null); // Sem cabeçalho
        alert.setContentText(message);
        alert.showAndWait(); // Exibe o alerta e aguarda até que seja fechado
    }

}
