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
            String querryDeleteNumeDiscipline = "select Disciplina from nota";
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
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disciplinaNota.setCellValueFactory(new PropertyValueFactory<>("NumeDisciplina"));
        detaliiNota.setCellValueFactory(new PropertyValueFactory<>("DetaliiNota"));
        Nota.setCellValueFactory(new PropertyValueFactory<>("Nota"));
        peste5Nota.setCellValueFactory(new PropertyValueFactory<>("Peste5"));

        generareTabelSiChoiceuri();
    }

    public void addNota(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            PreparedStatement querry = connectdb.prepareStatement("insert into nota(Disciplina, DetaliiNota, Nota, Peste5) values (?, ?, ?, ?)");
            querry.setString(1, alegeAddDisciplina.getValue());
            querry.setString(2, textDetalii.getText());
            querry.setDouble(3, Double.parseDouble(textNota.getText()));
            querry.setBoolean(4, new Nota(alegeAddDisciplina.getValue(), textDetalii.getText(), Double.parseDouble(textNota.getText())).isPeste5());
            querry.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabelNote.getItems().clear();
        alegeAddDisciplina.getItems().clear();
        alegeDeleteDisciplina.getItems().clear();
        textDetalii.clear(); textNota.clear();
        generareTabelSiChoiceuri();
    }

    public void deleteNota(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            Statement statement = connectdb.createStatement();
            String querry = "delete from nota where Disciplina = '" + alegeDeleteDisciplina.getValue() + "'";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabelNote.getItems().clear();
        alegeAddDisciplina.getItems().clear();
        alegeDeleteDisciplina.getItems().clear();
        generareTabelSiChoiceuri();
    }
}