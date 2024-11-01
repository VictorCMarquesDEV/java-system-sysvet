package com.sysvet.controller;

import com.sysvet.App;

import exceptions.UserAlreadyRegistered;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Funcionario;
import repositories.FuncionarioRepository;
import utils.hibernateSessionFactorySingleton;

public class GerenciarFormController {
    FuncionarioRepository employee_repository = new FuncionarioRepository(hibernateSessionFactorySingleton.getSessionFactory());

    @FXML
    private TextField cpf;

    @FXML
    private TextField name;

    @FXML
    private TextField salary;

    @FXML
    private Label errorMessage;

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
    void save(ActionEvent event){
        String name_value = name.getText();
        String cpf_value = cpf.getText();

        try {
            Double salary_value = Double.parseDouble(salary.getText());

            if(name_value.trim().isEmpty() || cpf_value.trim().isEmpty()){
                errorMessage.setText("Há campos vazios no formulário");
            }else{
                employee_repository.save(new Funcionario(name_value, cpf_value, salary_value));

                switchToGerenciar();    
            }
        } catch (NumberFormatException e) {
            errorMessage.setText("O salário deve ser um número válido.");
        } catch (UserAlreadyRegistered e) {
            // Captura a exceção personalizada e exibe a mensagem
            errorMessage.setText(e.getMessage());
        } catch (Exception e) {
            // Captura outras exceções, se necessário
            errorMessage.setText("Erro: " + e.getMessage());
        }
    }
}
