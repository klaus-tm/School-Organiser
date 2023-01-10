package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class menuNota implements Initializable {
    //addNota
    public ChoiceBox<String> alegeAddDisciplina;
    public TextArea textDetalii;
    public TextField textNota;

    //Note Table
    public TableView<Nota> tabelNote;
        public TableColumn<Nota, String> disciplinaNota;
        public TableColumn<Nota, String> detaliiNota;
        public TableColumn<Nota, Double> Nota;
        public TableColumn<Nota, Boolean> peste5Nota;

    //deleteNota
    public ChoiceBox<String> alegeDeleteDisciplina;
    public ChoiceBox<Double> alegeDeleteNota;

    public void generareTabelSiChoiceuri() {
        try {
            List<Nota> tabelNote = new ArrayList<>();
            DatabaseConnection connectionTabelNote = new DatabaseConnection();
            Connection connectdbTabelNote = connectionTabelNote.getConnection();
            String querryTabelNote = "select * from nota";
            Statement statementTabelNote = connectdbTabelNote.createStatement();
            ResultSet resultSetTabelNote = statementTabelNote.executeQuery(querryTabelNote);
            while(resultSetTabelNote.next()) {
                tabelNote.add(new Nota(resultSetTabelNote.getString("Disciplina"), resultSetTabelNote.getString("DetaliiNota"), resultSetTabelNote.getDouble("Nota")));
            }
            for(Nota nota: tabelNote) {
                this.tabelNote.getItems().add(nota);
            }
            statementTabelNote.close();

            List<String> addNumeDiscipline = new ArrayList<>();
            DatabaseConnection connectionAddNumeDiscipline = new DatabaseConnection();
            Connection connectdbAddNumeDiscipline = connectionAddNumeDiscipline.getConnection();
            String querryAddNumeDiscipline = "select Nume from disciplina";
            Statement statementAddNumeDiscipline = connectdbAddNumeDiscipline.createStatement();
            ResultSet resultSetAddNumeDiscipline = statementAddNumeDiscipline.executeQuery(querryAddNumeDiscipline);
            while (resultSetAddNumeDiscipline.next()) {
                addNumeDiscipline.add(resultSetAddNumeDiscipline.getString("Nume"));
            }
            for(String addNumeDisciplina : addNumeDiscipline) {
                alegeAddDisciplina.getItems().add(addNumeDisciplina);

            }
            statementAddNumeDiscipline.close();

            List<String> deleteNumeDiscipline = new ArrayList<>();
            DatabaseConnection connectionDeleteNumeDiscipline = new DatabaseConnection();
            Connection connectdbDeleteNumeDiscipline = connectionDeleteNumeDiscipline.getConnection();
            String querryDeleteNumeDiscipline = "select Disciplina from nota group by Disciplina";
            Statement statementDeleteNumeDiscipline = connectdbDeleteNumeDiscipline.createStatement();
            ResultSet resultSetDeleteNumeDiscipline = statementDeleteNumeDiscipline.executeQuery(querryDeleteNumeDiscipline);
            while (resultSetDeleteNumeDiscipline.next()) {
                deleteNumeDiscipline.add(resultSetDeleteNumeDiscipline.getString("Disciplina"));
            }
            for(String deleteNumeDisciplina : deleteNumeDiscipline) {
                alegeDeleteDisciplina.getItems().add(deleteNumeDisciplina);

            }
            statementDeleteNumeDiscipline.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disciplinaNota.setCellValueFactory(new PropertyValueFactory<>("NumeDisciplina"));
        detaliiNota.setCellValueFactory(new PropertyValueFactory<>("DetaliiNota"));
        Nota.setCellValueFactory(new PropertyValueFactory<>("Nota"));
        peste5Nota.setCellValueFactory(new PropertyValueFactory<>("Peste5"));

        generareTabelSiChoiceuri();
        alegeDeleteDisciplina.setOnAction(this::setDeleteNota);
    }

    private void setDeleteNota(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nota from nota where disciplina like ('" + alegeDeleteDisciplina.getValue() + "')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()){
                alegeDeleteNota.getItems().add(resultSet.getDouble("nota"));
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }

    public void addNota(ActionEvent actionEvent) {
        if(alegeAddDisciplina.getSelectionModel().isEmpty() || textDetalii.getText().isBlank() || textNota.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai introdus toate datele necesare!");
            alert.showAndWait();

            tabelNote.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            textDetalii.clear(); textNota.clear();
            generareTabelSiChoiceuri();
        }
        else {
            try {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectdb = connection.getConnection();
                PreparedStatement querry = connectdb.prepareStatement("insert into nota(Disciplina, DetaliiNota, Nota, Peste5) values (?, ?, ?, ?)");
                querry.setString(1, alegeAddDisciplina.getValue());
                querry.setString(2, textDetalii.getText());
                querry.setDouble(3, Double.parseDouble(textNota.getText()));
                querry.setBoolean(4, new Nota(alegeAddDisciplina.getValue(), textDetalii.getText(), Double.parseDouble(textNota.getText())).isPeste5());
                querry.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Felicitari boss");
                alert.setHeaderText("Nota a fost adaugata!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme boss");
                alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
                alert.showAndWait();
            }

            tabelNote.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            textDetalii.clear(); textNota.clear();
            generareTabelSiChoiceuri();
        }
    }

    public void deleteNota(ActionEvent actionEvent) {
        if(alegeAddDisciplina.getSelectionModel().isEmpty() || alegeDeleteNota.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai selectat tot ce trebuia!");
            alert.showAndWait();

            tabelNote.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            alegeDeleteNota.getItems().clear();
            generareTabelSiChoiceuri();
        }
        else{
            try {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectdb = connection.getConnection();
                PreparedStatement querry = connectdb.prepareStatement("delete from nota where Disciplina = ? and nota = ?");
                querry.setString(1, alegeDeleteDisciplina.getValue());
                querry.setDouble(2, alegeDeleteNota.getValue());
                querry.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Felicitari boss");
                alert.setHeaderText("Nota a fost stearsa!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme boss");
                alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
                alert.showAndWait();
            }

            tabelNote.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            alegeDeleteNota.getItems().clear();
            generareTabelSiChoiceuri();
        }
    }
}