package com.sysvet.controller;

import java.io.IOException;
import com.sysvet.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.hibernateSessionFactorySingleton;
import repositories.AdminRepository;
import repositories.UsuariosRepository;
import models.Admin;
import models.Usuarios;

public class LoginController {

    @FXML
    private TextField loginUser; // Referência ao campo de texto do usuário
    @FXML
    private PasswordField loginPassword; // Referência ao campo de senha

    // Obtendo os repositórios da singleton
    private final AdminRepository adminRepository = hibernateSessionFactorySingleton.getAdminRepository();
    private final UsuariosRepository usuariosRepository = hibernateSessionFactorySingleton.getUsuariosRepository();

    @FXML
    private void validateLogin() throws IOException {
        String username = loginUser.getText();
        String password = loginPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        // Verifica se o login é do admin
        if (username.equals("admin")) {
            Admin admin = adminRepository.findByField("username", "admin");

            if (admin != null && admin.verifyPassword(password)) {
                // Armazena o admin logado
                App.setUsuarioLogado((Object) admin);  // Corrigido para converter corretamente
                App.setRoot("/view/inicio");
            } else {
                showAlert("Erro de Login", "Usuário ou senha incorretos. Por favor, tente novamente.");
            }
        } else {
            // Para usuários comuns, a lógica é semelhante à anterior
            Usuarios usuario = usuariosRepository.findByField("username", username);

            if (usuario != null && usuario.verifyPassword(password)) {
                // Armazena o usuário logado
                App.setUsuarioLogado((Object) usuario);  // Corrigido para converter corretamente
                App.setRoot("/view/inicio");
            } else {
                showAlert("Login inválido", "Usuário ou senha incorretos. Por favor, tente novamente.");
            }
        }
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
