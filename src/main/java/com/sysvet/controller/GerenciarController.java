package com.sysvet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sysvet.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import models.Funcionario;

public class GerenciarController implements Initializable {
    public class TableRow {
        private Funcionario funcionario;

        public TableRow(Funcionario funcionario) {
            this.funcionario = funcionario;
        }

        public String getNome() {
            return this.funcionario.getNome();
        };

        public String getCpf() {
            return this.funcionario.getCpf();
        }

        public Double getSalario() {
            return this.funcionario.getSalario();
        };

        public ImageView getEditIcon() {
            return new ImageView(
                    new Image(
                            App.class.getResourceAsStream("/images/pencil-line.png")));
        }

        public ImageView getDeleteIcon() {
            return new ImageView(
                    new Image(
                            App.class.getResourceAsStream("/images/trash.png")));
        }

        public Funcionario getFuncionario() {
            return this.funcionario;
        }
    }

    @FXML
    private TableColumn<TableRow, String> cpf;

    @FXML
    private TableColumn<TableRow, String> name;

    @FXML
    private TableColumn<TableRow, Double> salary;

    @FXML
    private TableColumn<TableRow, ImageView> editIcon;

    @FXML
    private TableColumn<TableRow, ImageView> deleteIcon;

    @FXML
    private TableView<TableRow> table;

    @FXML
    void newEmployee(MouseEvent event) {

    }

    ObservableList<TableRow> registros = FXCollections.observableArrayList(
            new TableRow(
                    new Funcionario("David Rios", "000.000.000-01", 1000.50)),
            new TableRow(
                    new Funcionario("Helen Albuquerque", "000.000.000-02", 1000.50)),
            new TableRow(
                    new Funcionario("Victor Cavalvanti", "000.000.000-03", 1000.50)),
            new TableRow(
                    new Funcionario("Maria Nícolle", "000.000.000-04", 1000.50)),
            new TableRow(
                    new Funcionario("Vítor Pereira", "000.000.000-05", 1000.50)));

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
        name.setCellValueFactory(new PropertyValueFactory<TableRow, String>("nome"));
        cpf.setCellValueFactory(new PropertyValueFactory<TableRow, String>("cpf"));
        salary.setCellValueFactory(new PropertyValueFactory<TableRow, Double>("salario"));
        editIcon.setCellValueFactory(new PropertyValueFactory<TableRow, ImageView>("editIcon"));
        deleteIcon.setCellValueFactory(new PropertyValueFactory<TableRow, ImageView>("deleteIcon"));

        editIcon.setCellFactory(column -> new TableCell<TableRow, ImageView>() {
            private final ImageView editIcon = new ImageView(
                    new Image(App.class.getResourceAsStream("/images/pencil-line.png")));

            {
                editIcon.setFitWidth(30);
                editIcon.setFitHeight(30);
                editIcon.setCursor(Cursor.HAND);

                editIcon.setOnMouseClicked(event -> {
                    TableRow row = getTableView().getItems().get(getIndex());
                    System.out.println("Editando: " + row.getFuncionario().getNome());
                    event.consume();
                });
            }

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(editIcon);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        deleteIcon.setCellFactory(column -> new TableCell<TableRow, ImageView>() {
            private final ImageView deleteIcon = new ImageView(
                    new Image(getClass().getResourceAsStream("/images/trash.png")));

            {
                deleteIcon.setFitWidth(30);
                deleteIcon.setFitHeight(30);
                deleteIcon.setCursor(Cursor.HAND);

                deleteIcon.setOnMouseClicked(event -> {
                    TableRow row = getTableView().getItems().get(getIndex());
                    System.out.println("Deletando: " + row.getFuncionario().getNome());
                    registros.remove(row);
                    event.consume();
                });
            }

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(deleteIcon);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        table.setItems(registros);
        table.setSelectionModel(null);
    }
}
