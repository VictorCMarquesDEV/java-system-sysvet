package com.sysvet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sysvet.App;

import context.ConsultasContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Consulta;
import repositories.ConsultasRepository;
import utils.hibernateSessionFactorySingleton;

public class ConsultasFormController implements Initializable {

    ConsultasRepository employee_repository = new ConsultasRepository(
            hibernateSessionFactorySingleton.getSessionFactory());

    @FXML
    private TextField data;

    @FXML
    private TextField hora;

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
        String data_value = data.getText();
        String hora_value = hora.getText();
        String descricao_value = descricao.getText();
        String cliente_value = cliente.getText();
        String pet_value = pet.getText();
        String responsavel_value = responsavel.getText();

        try {

            if (descricao_value.trim().isEmpty() || cliente_value.trim().isEmpty() || pet_value.trim().isEmpty()
                    || responsavel_value.trim().isEmpty() || data_value.trim().isEmpty()
                    || hora_value.trim().isEmpty()) {
                errorMessage.setText("Há campos vazios no formulário");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (ConsultasContext.getInstance().getEdit()) {
            data.setText(ConsultasContext.getInstance().getEmployee().getData());
            hora.setText(ConsultasContext.getInstance().getEmployee().getHora());
            descricao.setText(ConsultasContext.getInstance().getEmployee().getDescricao());
            cliente.setText(ConsultasContext.getInstance().getEmployee().getCliente());
            pet.setText(ConsultasContext.getInstance().getEmployee().getPet());
            responsavel.setText(ConsultasContext.getInstance().getEmployee().getResponsavel());

            title.setText("Atualização de consulta");
        }
    }
}
