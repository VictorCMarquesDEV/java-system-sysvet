package com.sysvet.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.sysvet.App;

import context.ConsultasContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Consulta;
import repositories.ConsultasRepository;
import utils.hibernateSessionFactorySingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class ConsultasFormController implements Initializable {

    ConsultasRepository employee_repository = new ConsultasRepository(
            hibernateSessionFactorySingleton.getSessionFactory());

    @FXML
    private DatePicker data;

    @FXML
    private ChoiceBox hora;

    @FXML
    private TextField descricao;

    @FXML
    private TextField cliente;

    @FXML
    private TextField pet;

    @FXML
    private TextField responsavel;

    @FXML
    private Label errorMessage;

    @FXML
    private Text title;

    @FXML
    private void switchToLogin() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/login");
    }

    @FXML
    private void switchToCadastro() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/cadastro");
    }

    @FXML
    private void switchToConsultas() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/consultas");
    }

    @FXML
    private void switchToGerenciar() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/gerenciar");
    }

    @FXML
    private void switchToInicio() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/inicio");
    }

    @FXML
    private void switchToMedicamentos() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/medicamentos");
    }

    @FXML
    private void switchToTrocarSenha() throws IOException {
        ConsultasContext.getInstance().clear();
        App.setRoot("/view/trocarsenha");
    }

    @FXML
    void save(ActionEvent event) {
        String data_value = (data.getValue() != null) ? data.getValue().toString() : "";
        String hora_value = (hora.getValue() != null) ? hora.getValue().toString() : "";
        String descricao_value = descricao.getText();
        String cliente_value = cliente.getText();
        String pet_value = pet.getText();
        String responsavel_value = responsavel.getText();

        try {

            if (descricao_value.trim().isEmpty() || cliente_value.trim().isEmpty() || pet_value.trim().isEmpty()
                    || responsavel_value.trim().isEmpty() || data_value.trim().isEmpty()
                    || hora_value.trim().isEmpty()) {
                showAlert("Campos obrigatórios", "Há campos vazios no formulário. Por favor, preencha todos os campos.");
            } else {

                if (ConsultasContext.getInstance().getEdit()) {
                    ConsultasContext.getInstance().getEmployee().setData(data_value);
                    ConsultasContext.getInstance().getEmployee().setHora(hora_value);
                    ConsultasContext.getInstance().getEmployee().setDescricao(descricao_value);
                    ConsultasContext.getInstance().getEmployee().setCliente(cliente_value);
                    ConsultasContext.getInstance().getEmployee().setPet(pet_value);
                    ConsultasContext.getInstance().getEmployee().setResponsavel(responsavel_value);

                    employee_repository.update(ConsultasContext.getInstance().getEmployee());

                    ConsultasContext.getInstance().clear();
                } else {
                    employee_repository
                            .save(new Consulta(data_value, hora_value, descricao_value, cliente_value, pet_value,
                                    responsavel_value));
                }

                switchToConsultas();
            }
        } catch (Exception e) {
            // Captura outras exceções, se necessário
            errorMessage.setText("Erro: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // Adicionando opções à ChoiceBox
        ObservableList<String> horarios = FXCollections.observableArrayList(
            "08:00", "09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00"
            );
        hora.setItems(horarios);

        if (ConsultasContext.getInstance().getEdit()) {

            data.setValue(LocalDate.parse(ConsultasContext.getInstance().getEmployee().getData()));
            hora.setValue(ConsultasContext.getInstance().getEmployee().getHora());
            descricao.setText(ConsultasContext.getInstance().getEmployee().getDescricao());
            cliente.setText(ConsultasContext.getInstance().getEmployee().getCliente());
            pet.setText(ConsultasContext.getInstance().getEmployee().getPet());
            responsavel.setText(ConsultasContext.getInstance().getEmployee().getResponsavel());

            title.setText("Atualização de consulta");
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
