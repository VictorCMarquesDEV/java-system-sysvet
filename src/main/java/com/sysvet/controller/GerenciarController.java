package com.sysvet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sysvet.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Funcionario;

public class GerenciarController implements Initializable{
    @FXML
    private TableColumn<Funcionario, String> cpf;

    @FXML
    private TableColumn<Funcionario, String> name;

    @FXML
    private TableColumn<Funcionario, Double> salary;

    @FXML
    private TableView<Funcionario> table;
    
    @FXML
    void newEmployee(MouseEvent event) {

    }

    ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50),
        new Funcionario("Vítor Pereira Fontes","000.000.000-01",1000.50)
    );

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("nome"));
        cpf.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("cpf"));
        salary.setCellValueFactory(new PropertyValueFactory<Funcionario,Double>("salario"));

        table.setItems(funcionarios);
    }
}
