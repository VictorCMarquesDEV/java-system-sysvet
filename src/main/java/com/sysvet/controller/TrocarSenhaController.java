package com.sysvet.controller;

import java.io.IOException;

import com.sysvet.App;
import models.Admin;
import models.Usuarios;
import repositories.AdminRepository;
import repositories.UsuariosRepository;
import utils.hibernateSessionFactorySingleton;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;

public class TrocarSenhaController {

    @FXML
    private PasswordField atualPassword; // Senha atual
    @FXML
    private PasswordField newPassword1; // Nova senha
    @FXML
    private PasswordField newPassword2; // Repetir nova senha
    @FXML
    private Label errorMessage; // Mensagem de erro
    @FXML
    private Text title; // Título na tela

    private final AdminRepository adminRepository = new AdminRepository(hibernateSessionFactorySingleton.getSessionFactory());
    private final UsuariosRepository usuariosRepository = new UsuariosRepository(hibernateSessionFactorySingleton.getSessionFactory());

    @FXML
    private void initialize() {
        // Ajusta o título conforme o tipo de usuário logado
        Object usuarioLogado = App.getUsuarioLogado();
        if (usuarioLogado instanceof Admin) {
            title.setText("Trocar Senha - Administrador");
        } else if (usuarioLogado instanceof Usuarios) {
            title.setText("Trocar Senha - Usuário");
        }
    }

    @FXML
    private void save() {
        String senhaAtual = atualPassword.getText();
        String novaSenha1 = newPassword1.getText();
        String novaSenha2 = newPassword2.getText();

        if (senhaAtual.isEmpty() || novaSenha1.isEmpty() || novaSenha2.isEmpty()) {
            errorMessage.setText("Todos os campos são obrigatórios.");
            return;
        }

        // Verifica se as novas senhas coincidem
        if (!novaSenha1.equals(novaSenha2)) {
            errorMessage.setText("As novas senhas não coincidem.");
            return;
        }

        // A lógica de verificação de senha atual depende do tipo de usuário
        try {
            Object usuarioLogado = App.getUsuarioLogado();
            if (usuarioLogado instanceof Admin) {
                Admin admin = (Admin) usuarioLogado;
                if (admin.verifyPassword(senhaAtual)) {
                    admin.setSenha(novaSenha1);
                    adminRepository.update(admin);
                    showAlert("Sucesso", "Senha alterada com sucesso.");
                } else {
                    errorMessage.setText("Senha atual incorreta.");
                }
            } else if (usuarioLogado instanceof Usuarios) {
                Usuarios usuario = (Usuarios) usuarioLogado;
                if (usuario.verifyPassword(senhaAtual)) {
                    usuario.setPassword(novaSenha1);
                    usuariosRepository.update(usuario);
                    showAlert("Sucesso", "Senha alterada com sucesso.");
                } else {
                    errorMessage.setText("Senha atual incorreta.");
                }
            }
        } catch (Exception e) {
            errorMessage.setText("Erro ao alterar a senha: " + e.getMessage());
        }
    }

    // Método para exibir um alerta
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
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
    @FXML
    private void switchToTrocarSenha() throws IOException {
        App.setRoot("/view/trocarsenha");
    }
}
