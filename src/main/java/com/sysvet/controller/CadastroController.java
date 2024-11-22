package com.sysvet.controller;

import com.sysvet.App;
import exceptions.GenericException;  
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Animal;
import models.Proprietario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repositories.AnimalRepository;
import repositories.ProprietarioRepository;
import utils.hibernateSessionFactorySingleton;

import java.io.IOException;

public class CadastroController {

    private final ProprietarioRepository proprietarioRepository = new ProprietarioRepository(hibernateSessionFactorySingleton.getSessionFactory());
    private final AnimalRepository animalRepository = new AnimalRepository(hibernateSessionFactorySingleton.getSessionFactory());

    
    @FXML
    private TextField nomeProprietario;

    @FXML
    private TextField cpfProprietario;

    @FXML
    private TextField enderecoProprietario;

    @FXML
    private TextField phone;

    @FXML
    private TextField nomeAnimal;

    @FXML
    private TextField especieAnimal;

    @FXML
    private TextField racaAnimal;

    @FXML
    private DatePicker dataNascimentoAnimal;

    @FXML
    private TextField historicoMedicoAnimal;

    @FXML
    private Button cadastrarButton;

    @FXML
    private Button cancelarButton;

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
    private void initialize() {
        cadastrarButton.setCursor(Cursor.HAND);
    }

    @FXML
    private void handlecadastrar(ActionEvent event) {

        if (areFieldsEmpty()) {
            showAlert("Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        Session session = null;
        Transaction transaction = null;

        try {
           session = hibernateSessionFactorySingleton.getSessionFactory().openSession();

            transaction = session.beginTransaction();

            // Verifica se o proprietário já existe
            Proprietario proprietario = proprietarioRepository.findByCpf(cpfProprietario.getText());

            if (proprietario == null) {
                proprietario = new Proprietario(
                        nomeProprietario.getText(),
                        cpfProprietario.getText(),
                        enderecoProprietario.getText(),
                        phone.getText()
                );
                proprietarioRepository.save(proprietario);
            }

            // Cria o novo animal
            Animal animal = new Animal(
                    nomeAnimal.getText(),
                    especieAnimal.getText(),
                    racaAnimal.getText(),
                    dataNascimentoAnimal.getValue() != null ? dataNascimentoAnimal.getValue().toString() : null,
                    historicoMedicoAnimal.getText(),
                    proprietario);

            try {
                // Tenta salvar o animal
                animalRepository.save(animal);
            } catch (GenericException e) {  // Agora trata a GenericException
                // Se o animal já foi registrado, exibe um alerta
                showAlert("Erro ao cadastrar animal", e.getMessage());
                return;
            }

            transaction.commit();

            // Alerta de sucesso
            showSuccessAlert();

            // Redireciona para a tela de cadastro
           switchToInicio();


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            showAlert("Erro", "Ocorreu um erro inesperado: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    private boolean areFieldsEmpty() {
        return nomeProprietario.getText().isEmpty() ||
                cpfProprietario.getText().isEmpty() ||
                enderecoProprietario.getText().isEmpty() ||
                phone.getText().isEmpty() ||
                nomeAnimal.getText().isEmpty() ||
                especieAnimal.getText().isEmpty() ||
                racaAnimal.getText().isEmpty() ||
                dataNascimentoAnimal.getValue() == null ||
                historicoMedicoAnimal.getText().isEmpty();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cadastro realizado");
        alert.setHeaderText(null);
        alert.setContentText("Cadastro realizado com sucesso!");
        alert.showAndWait();
    }
}
