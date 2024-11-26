package com.sysvet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;

import com.sysvet.App;

import context.ConsultasContext;
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
import repositories.ConsultasRepository;
import utils.hibernateSessionFactorySingleton;

public class ConsultasController implements Initializable {

    public class TableRow {
        private Consulta consulta;

        public TableRow(Consulta consulta) {
            this.consulta = consulta;
        };

        public String getData() {
            return this.consulta.getData();
        };

        public String getHora() {
            return this.consulta.getHora();
        };

        public String getCliente() {
            return this.consulta.getCliente();
        };

        public String getPet() {
            return this.consulta.getPet();
        };


        public ImageView getEditIcon() {
            return new ImageView(
                    new Image(
                            App.class.getResourceAsStream("/images/pencil-line.png")));
        }

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
    private TableColumn<TableRow, String> data;

    @FXML
    private TableColumn<TableRow, String> hora;

    @FXML
    private TableColumn<TableRow, String> cliente;

    @FXML
    private TableColumn<TableRow, String> pet;

    @FXML
    private TableColumn<TableRow, ImageView> editIcon;

    @FXML
    private TableColumn<TableRow, ImageView> checkIcon;

    @FXML
    private TableView<TableRow> table;

    @FXML
    void newEmployee(MouseEvent event) {

    }

    @FXML
    void switchToConsultasForm() throws IOException {
        App.setRoot("/view/consultasForm");
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
    private void switchToTrocarSenha() throws IOException {
        App.setRoot("/view/trocarsenha");
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

    SessionFactory sessionFactory = hibernateSessionFactorySingleton.getSessionFactory();

    ConsultasRepository consultasRepositorio = new ConsultasRepository(sessionFactory);

    ObservableList<TableRow> registros = FXCollections.observableArrayList();

    List<Consulta> consultas = consultasRepositorio.findAll();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        consultas.forEach(consulta -> {
            registros.add(
                    new TableRow(consulta));
        });

        data.setCellValueFactory(new PropertyValueFactory<TableRow, String>("data"));
        hora.setCellValueFactory(new PropertyValueFactory<TableRow, String>("hora"));
        cliente.setCellValueFactory(new PropertyValueFactory<TableRow, String>("cliente"));
        pet.setCellValueFactory(new PropertyValueFactory<TableRow, String>("pet"));
        editIcon.setCellValueFactory(new PropertyValueFactory<TableRow, ImageView>("editIcon"));
        checkIcon.setCellValueFactory(new PropertyValueFactory<TableRow, ImageView>("checkIcon"));

        editIcon.setCellFactory(column -> new TableCell<TableRow, ImageView>() {
            private final ImageView editIcon = new ImageView(
                    new Image(App.class.getResourceAsStream("/images/pencil-line.png")));

            {
                editIcon.setFitWidth(30);
                editIcon.setFitHeight(30);
                editIcon.setCursor(Cursor.HAND);

                editIcon.setOnMouseClicked(event -> {
                    TableRow row = getTableView().getItems().get(getIndex());

                    ConsultasContext.getInstance().setEmployee(row.getConsulta());
                    ConsultasContext.getInstance().setEdit(true);

                    try {
                        switchToConsultasForm();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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

        checkIcon.setCellFactory(column -> new TableCell<TableRow, ImageView>() {
            private final ImageView checkIcon = new ImageView(
                    new Image(getClass().getResourceAsStream("/images/icon_check.png")));

            {
                checkIcon.setFitWidth(30);
                checkIcon.setFitHeight(30);
                checkIcon.setCursor(Cursor.HAND);

                checkIcon.setOnMouseClicked(event -> {
                    TableRow row = getTableView().getItems().get(getIndex());

                    registros.remove(row);
                    consultasRepositorio.delete(row.getConsulta().getId());
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
