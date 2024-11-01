package com.sysvet.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import com.sysvet.App;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MedicamentosController {

    @FXML
    private TableView<?> listOfMeds;

    @FXML
    private TableColumn<?, ?> med;

    @FXML
    private TableColumn<?, ?> qntd;

    @FXML
    private TextField medName;

    @FXML
    private TextField medQntd;

    @FXML
    void addMed(MouseEvent event) {

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
}
