package org.todeschini.tarefas.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.todeschini.tarefas.model.Tarefa;
import org.todeschini.tarefas.service.ITarefaService;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ITarefaService service;

    @FXML
    private TableView<Tarefa> tableTarefas;

    @FXML
    private TableColumn<Tarefa, Integer> colunaId;

    @FXML
    private TableColumn<Tarefa, String> colunaNome;

    @FXML
    private TableColumn<Tarefa, String> colunaResponsavel;

    @FXML
    private TableColumn<Tarefa, String> colunaStatus;

    private final ObservableList<Tarefa> tarefas = FXCollections.observableArrayList();

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtResponsavel;

    @FXML
    private TextField txtStatus;

    private void mock() {
        for (int i = 1; i < 4; i++) {
            service.salarTarefa(Tarefa.builder().nome("t" + i).responsavel("r" + i).status("s" + i).build());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mock();

        this.tableTarefas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.configColuns();
        this.refleshTableTarefas();
        this.txtID.setEditable(false);
    }

    private void refleshTableTarefas() {
        tarefas.clear(); //limpar a lista
        tarefas.addAll(service.findAll());

        tableTarefas.setItems(tarefas);
    }

    private void configColuns() {
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void salvarTarefa() {
        try {
            var tarefa = new Tarefa();
            tarefa.setNome(validaTxtFiled(txtNome));
            tarefa.setResponsavel(validaTxtFiled(txtResponsavel));
            tarefa.setStatus(validaTxtFiled(txtStatus));

            // codicao para salvar ou realizar update
            if (!txtID.getText().isEmpty()) {
                tarefa.setId(Integer.parseInt((txtID.getText())));
            }

            service.salarTarefa(tarefa);
            this.refleshTableTarefas();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void clean() {
        TextField[] txts = { txtID, txtNome, txtStatus, txtResponsavel };

        Arrays.stream(txts).forEach((txt) -> {
            txt.setText("");
        });

        refleshTableTarefas();
    }

    public void getTarefaByRowTable(){
        var tarefa = tableTarefas.getSelectionModel().getSelectedItem();

        if (tarefa != null) {
            txtID.setText(tarefa.getId().toString());
            txtNome.setText(tarefa.getNome());
            txtResponsavel.setText(tarefa.getResponsavel());
            txtStatus.setText(tarefa.getStatus());
        }

    }

    public void deletarTarefa() {
       if (txtID.getText().isEmpty()) {
           mostrarMsg("Selecione uma tarefa","Você deve selecionar uma tarefa antes de deletar");
       } else {
           service.deleteTarefaById(Integer.parseInt(txtID.getText()));
           this.clean();
       }
    }

    private String validaTxtFiled(TextField tf) {
        if (tf.getText().isEmpty()) {
            mostrarMsg("Erro validacao", "O campo "
                    + tf.getId().replace("txt", "").toLowerCase() + " deve ser preenchido");

            tf.requestFocus();

            throw new RuntimeException("Campo obrigatorio nao preenchido");
        }

        return tf.getText();
    }

    private void mostrarMsg(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
