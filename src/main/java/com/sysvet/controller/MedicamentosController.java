package com.sysvet.controller;

import com.sysvet.App;
import context.GerenciarContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import models.Medicamentos;
import org.hibernate.SessionFactory;
import repositories.medicamentoRepository;
import utils.hibernateSessionFactorySingleton;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.util.List;

public class MedicamentosController {

    @FXML
    private TableView<Medicamentos> medTable;

    @FXML
    private TableColumn<Medicamentos, ImageView> deleteIcon;

    @FXML
    private TableColumn<Medicamentos, ImageView> updateIcon;

    @FXML
    private TableColumn<Medicamentos, String> name;

    @FXML
    private TableColumn<Medicamentos, Integer> quantity;

    @FXML
    private TextField medName;

    @FXML
    private TextField medQntd;

    // Repositório declarado sem inicializar diretamente
    private medicamentoRepository repo;

    // Lista observável para a tabela
    ObservableList<Medicamentos> registros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializa o repositório com a SessionFactory
        SessionFactory sessionFactory = hibernateSessionFactorySingleton.getSessionFactory();
        repo = new medicamentoRepository(sessionFactory);

        // Recupera os medicamentos do banco de dados e adiciona à lista observável
        List<Medicamentos> medicamentos = repo.findAll();

        medicamentos.forEach(registros::add);

        // Configurar colunas da tabela
        name.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        updateIcon.setCellValueFactory(new PropertyValueFactory<>("updateIcon"));
        deleteIcon.setCellValueFactory(new PropertyValueFactory<>("deleteIcon"));

        // Adicionar dados de exemplo (se necessário)
        registros.add(new Medicamentos("Paracetamol", 20));
        registros.add(new Medicamentos("Ibuprofeno", 15));

        // Vincular lista de medicamentos à tabela
        medTable.setItems(registros);

        updateIcon.setCellFactory(column -> new TableCell<Medicamentos, ImageView>() {
            private final javafx.scene.image.ImageView editIcon;

            {
                javafx.scene.image.Image image;
                try {
                    image = new javafx.scene.image.Image(App.class.getResource("/images/pencil-line.png").toExternalForm());
                } catch (Exception e) {
                    System.out.println("Erro ao carregar imagem de edição: " + e.getMessage());
                    image = null;
                }
                editIcon = new javafx.scene.image.ImageView(image);
                editIcon.setFitWidth(30);
                editIcon.setFitHeight(30);
                editIcon.setCursor(Cursor.HAND);

                editIcon.setOnMouseClicked(event -> {
                    Medicamentos medicamento = getTableView().getItems().get(getIndex());
                    if (medicamento != null) {
                        // Exemplo: abrir um modal ou preencher os campos de texto para edição
                        medName.setText(medicamento.getNome());
                        medQntd.setText(String.valueOf(medicamento.getQuantidade()));

                        // Atualizar o medicamento no repositório e na tabela ao salvar as alterações
                        System.out.println("Modo de edição ativado para: " + medicamento.getNome());
                    }

                    event.consume();
                });
            }

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || editIcon.getImage() == null) {
                    setGraphic(null);
                } else {
                    setGraphic(editIcon);
                }
            }
        });


        deleteIcon.setCellFactory(column -> new TableCell<Medicamentos, ImageView>() {
            private final javafx.scene.image.ImageView deleteIcon;
            {
                javafx.scene.image.Image image;
                try {
                    image = new javafx.scene.image.Image(App.class.getResource("/images/trash.png").toExternalForm());
                } catch (Exception e) {
                    System.out.println("Erro ao carregar imagem: " + e.getMessage());
                    image = null;
                }
                deleteIcon = new javafx.scene.image.ImageView(image);
                deleteIcon.setFitWidth(30);
                deleteIcon.setFitHeight(30);
                deleteIcon.setCursor(Cursor.HAND);

                deleteIcon.setOnMouseClicked(event -> {
                    Medicamentos medicamento = getTableView().getItems().get(getIndex());
                    registros.remove(medicamento); // Remove da lista observável
                    try {
                        repo.delete(medicamento.getId());
                        System.out.println("Medicamento removido com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao remover medicamento: " + e.getMessage());
                    }

                    event.consume();
                });

            }

            @Override
            protected void updateItem(ImageView item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || deleteIcon.getImage() == null) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteIcon);
                }
            }
        });


    }

    @FXML
    void saveMeds(ActionEvent event) {
        // Obtém os valores dos campos de texto
        String name = medName.getText();
        String quantityText = medQntd.getText();

        try {
            // Converte a quantidade para um número
            int quantity = Integer.parseInt(quantityText);

            // Verifica se os campos estão preenchidos
            if (name.trim().isEmpty() || quantity <= 0) {
                System.out.println("Os campos não podem estar vazios e a quantidade deve ser maior que zero.");
            } else {
                // Cria o objeto Medicamento
                Medicamentos medicamentos = new Medicamentos(name, quantity);

                // Salva no banco de dados usando o repositório
                repo.save(medicamentos);

                // Atualiza a tabela
                registros.add(medicamentos);

                // Limpa os campos após salvar
                medName.clear();
                medQntd.clear();

                System.out.println("Medicamento salvo com sucesso!");
            }
        } catch (NumberFormatException e) {
            // Trata erro de conversão para número
            System.out.println("A quantidade deve ser um número válido.");
        } catch (Exception e) {
            // Trata outros erros inesperados
            System.out.println("Erro ao salvar medicamento: " + e.getMessage());
        }
    }

    @FXML
    void switchToCadastro() throws IOException {
        App.setRoot("/view/cadastro");
    }

    @FXML
    private void switchToTrocarSenha() throws IOException {
        App.setRoot("/view/trocarsenha");
    }

    @FXML
    private void switchToConsultas() throws IOException {
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
