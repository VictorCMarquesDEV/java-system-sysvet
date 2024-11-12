package com.sysvet.controller;
import com.sysvet.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class MedicamentosController {

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> salary;

    @FXML
    private TableView<?> table;

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
