package com.sysvet.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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
import models.Consulta;

public class InicioController implements Initializable {

    public class TableRow {
        private Consulta consulta;

        public TableRow(Consulta consulta) {
            this.consulta = consulta;
        };

        public Date getData() {
            return this.consulta.getData();
        };

        public Time getHora() {
            return this.consulta.getHora();
        };

        public String getDescricao() {
            return this.consulta.getDescricao();
        };

        public String getClientepet() {
            return this.consulta.getClientepet();
        };

        public String getResponsavel() {
            return this.consulta.getResponsavel();
        };

        public ImageView getCheckIcon() {
            return new ImageView(
                    new Image(
                            App.class.getResourceAsStream("/images/icon_check.png")));
        }

        public Consulta getConsulta() {
            return this.consulta;
        }
    }

    @FXML
    private TableColumn<TableRow, Date> data;

    @FXML
    private TableColumn<TableRow, Time> hora;

    @FXML
    private TableColumn<TableRow, String> descricao;

    @FXML
    private TableColumn<TableRow, String> clientepet;

    @FXML
    private TableColumn<TableRow, String> responsavel;

    @FXML
    private TableColumn<TableRow, ImageView> checkIcon;

    @FXML
    private TableView<TableRow> table;

    @FXML
    void newEmployee(MouseEvent event) {

    }

    ObservableList<TableRow> registros = FXCollections.observableArrayList(
            new TableRow(
                    new Consulta(java.sql.Date.valueOf("2024-01-01"), java.sql.Time.valueOf("08:00:00"), "Consulta 1",
                            "Vitor / Lacoste", "David Rios")),
            new TableRow(
                    new Consulta(java.sql.Date.valueOf("2024-01-01"), java.sql.Time.valueOf("08:30:00"), "Consulta 2",
                            "Vitor / Junin", "Nícolle")),
            new TableRow(
                    new Consulta(java.sql.Date.valueOf("2024-01-01"), java.sql.Time.valueOf("09:00:00"), "Consulta 3",
                            "Vitor / Negão", "Helen")),
            new TableRow(
                    new Consulta(java.sql.Date.valueOf("2024-01-01"), java.sql.Time.valueOf("09:30:00"), "Consulta 4",
                            "Vitor / Robson", "Victor")),
            new TableRow(
                    new Consulta(java.sql.Date.valueOf("2024-01-01"), java.sql.Time.valueOf("10:00:00"), "Consulta 5",
                            "Vitor / Gordo Bala", "Vitor Pereira")));

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
        data.setCellValueFactory(new PropertyValueFactory<TableRow, Date>("data"));
        hora.setCellValueFactory(new PropertyValueFactory<TableRow, Time>("hora"));
        descricao.setCellValueFactory(new PropertyValueFactory<TableRow, String>("descricao"));
        clientepet.setCellValueFactory(new PropertyValueFactory<TableRow, String>("clientepet"));
        responsavel.setCellValueFactory(new PropertyValueFactory<TableRow, String>("responsavel"));
        checkIcon.setCellValueFactory(new PropertyValueFactory<TableRow, ImageView>("checkIcon"));

        checkIcon.setCellFactory(column -> new TableCell<TableRow, ImageView>() {
            private final ImageView checkIcon = new ImageView(
                    new Image(getClass().getResourceAsStream("/images/icon_check.png")));

            {
                checkIcon.setFitWidth(30);
                checkIcon.setFitHeight(30);
                checkIcon.setCursor(Cursor.HAND);

                checkIcon.setOnMouseClicked(event -> {
                    TableRow row = getTableView().getItems().get(getIndex());
                    System.out.println("Deletando: " + row.getConsulta().getDescricao());
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
                    HBox hBox = new HBox(checkIcon);
                    hBox.setAlignment(Pos.CENTER);
                    setGraphic(hBox);
                }
            }
        });

        table.setItems(registros);
        table.setSelectionModel(null);
    }

}
