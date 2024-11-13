package com.sysvet.controller;

import com.sysvet.App;
import javafx.collections.FXCollections;
import com.sysvet.controller.MedicamentosController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Medicamentos;

import java.io.IOException;

public class MedicamentosController {

    @FXML
    private TableView<Medicamentos> medTable;

    @FXML
    private TableColumn<Medicamentos, String> name;

    @FXML
    private TableColumn<Medicamentos, Integer> quantity;


    // Lista de medicamentos (simulação)
    private ObservableList<Medicamentos> medicamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar colunas da tabela
        name.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        // Adicionar dados de exemplo
        medicamentos.add(new Medicamentos("Paracetamol",20));
        medicamentos.add(new Medicamentos("Ibuprofeno", 15));

        // Vincular lista de medicamentos à tabela
        medTable.setItems(medicamentos);
    }

    @FXML
    void switchToCadastro() throws IOException {
        App.setRoot("/view/cadastro");
    }

    @FXML
    void switchToConsultas() throws IOException {
        App.setRoot("/view/consultas");
    }

    @FXML
    void switchToGerenciar() throws IOException {
        App.setRoot("/view/gerenciar");
    }

    @FXML
    void switchToGerenciarForm() throws IOException {
        App.setRoot("/view/gerenciarForm");
    }

    @FXML
    void switchToInicio() throws IOException {
        App.setRoot("/view/inicio");
    }

    @FXML
    void switchToLogin() throws IOException {
        App.setRoot("/view/login");
    }

    @FXML
    void switchToMedicamentos() throws IOException {
        App.setRoot("/view/medicamentos");
    }
}
