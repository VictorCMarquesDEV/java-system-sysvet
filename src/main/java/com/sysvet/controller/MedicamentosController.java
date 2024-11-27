package com.sysvet.controller;

import com.sysvet.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Medicamentos;
import org.hibernate.SessionFactory;
import repositories.medicamentoRepository;
import utils.hibernateSessionFactorySingleton;

import java.io.IOException;
import java.util.List;

public class MedicamentosController {

    @FXML
    private TableView<Medicamentos> medTable;

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

        // Adicionar dados de exemplo (se necessário)
        registros.add(new Medicamentos("Paracetamol", 20));
        registros.add(new Medicamentos("Ibuprofeno", 15));

        // Vincular lista de medicamentos à tabela
        medTable.setItems(registros);
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
