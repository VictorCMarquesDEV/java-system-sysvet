package com.sysvet.controller;

import com.sysvet.App;

import context.GerenciarContext;
import exceptions.GenericException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Funcionario;
import repositories.FuncionarioRepository;
import utils.hibernateSessionFactorySingleton;

public class GerenciarFormController implements Initializable {
    FuncionarioRepository employee_repository = new FuncionarioRepository(
            hibernateSessionFactorySingleton.getSessionFactory());

    @FXML
    private TextField cpf;

    @FXML
    private TextField name;

    @FXML
    private TextField salary;

    @FXML
    private Label errorMessage;

    @FXML
    private Text title;

    @FXML
    private void switchToLogin() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/login");
    }

    @FXML
    private void switchToCadastro() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/cadastro");
    }

    @FXML
    private void switchToConsultas() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/consultas");
    }

    @FXML
    private void switchToTrocarSenha() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/trocarsenha");
    }

    @FXML
    private void switchToGerenciar() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/gerenciar");
    }

    @FXML
    private void switchToInicio() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/inicio");
    }

    @FXML
    private void switchToMedicamentos() throws IOException {
        GerenciarContext.getInstance().clear();
        App.setRoot("/view/medicamentos");
    }

    @FXML
    void save(ActionEvent event) {
        String name_value = name.getText();
        String cpf_value = cpf.getText();

        try {
            Double salary_value = Double.parseDouble(salary.getText());

            if (name_value.trim().isEmpty() || cpf_value.trim().isEmpty()) {
                errorMessage.setText("Há campos vazios no formulário");
            } else {

                if (GerenciarContext.getInstance().getEdit()) {
                    GerenciarContext.getInstance().getEmployee().setNome(name_value);
                    GerenciarContext.getInstance().getEmployee().setSalario(salary_value);

                    employee_repository.update(GerenciarContext.getInstance().getEmployee());

                    GerenciarContext.getInstance().clear();
                } else {
                    employee_repository.save(new Funcionario(name_value, cpf_value, salary_value));
                }

                switchToGerenciar();
            }
        } catch (NumberFormatException e) {
            errorMessage.setText("O salário deve ser um número válido.");
        } catch (GenericException e) {
            // Captura a exceção personalizada e exibe a mensagem
            errorMessage.setText(e.getMessage());
        } catch (Exception e) {
            // Captura outras exceções, se necessário
            errorMessage.setText("Erro: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (GerenciarContext.getInstance().getEdit()) {
            name.setText(GerenciarContext.getInstance().getEmployee().getNome());
            cpf.setText(GerenciarContext.getInstance().getEmployee().getCpf());
            cpf.setDisable(true);
            salary.setText(Double.toString(GerenciarContext.getInstance().getEmployee().getSalario()));

            title.setText("Atualização de funcionário");
        }
    }
}
