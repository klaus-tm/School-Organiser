package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class menuProfesor implements Initializable {
    //addProf
    public TextField textNume;
    public TextField textMail;
    public TextField textTelefon;

    //Prof Table
    public TableView<Profesor> tabelProfesori;
        public TableColumn<Profesor, String> numeProfesor;
        public TableColumn<Profesor, String> mailProfesor;
        public TableColumn<Profesor, Integer> telefonProfesor;

    //deleteProf
    public ChoiceBox<String> alegeProfesor;

    public void generareTabelSiChoice() {
        try {
            List<Profesor> tabelProfesori = new ArrayList<>();
            DatabaseConnection connectionTabelProfesori = new DatabaseConnection();
            Connection connectdbTabelProfesori = connectionTabelProfesori.getConnection();
            String querryTabelProfesori = "select * from profesor";
            Statement statementTabelProfesori = connectdbTabelProfesori.createStatement();
            ResultSet resultSetTabelProfesori = statementTabelProfesori.executeQuery(querryTabelProfesori);
            while (resultSetTabelProfesori.next()) {
                tabelProfesori.add(new Profesor(resultSetTabelProfesori.getString("Nume"), resultSetTabelProfesori.getString("Mail"), resultSetTabelProfesori.getString("Telefon")));
            }
            for(Profesor profesor: tabelProfesori) {
                this.tabelProfesori.getItems().add(profesor);
            }
            statementTabelProfesori.close();

            List<String> numeProfesori = new ArrayList<>();
            DatabaseConnection connectionNumeProfesori = new DatabaseConnection();
            Connection connectdbNumeProfesori = connectionNumeProfesori.getConnection();
            String querryNumeProfesori = "select Nume from profesor";
            Statement statementNumeProfesori = connectdbNumeProfesori.createStatement();
            ResultSet resultSetNumeProfesori = statementNumeProfesori.executeQuery(querryNumeProfesori);
            while (resultSetNumeProfesori.next()) {
                numeProfesori.add(resultSetNumeProfesori.getString("Nume"));
            }
            for(String numeProfesor : numeProfesori) {
                alegeProfesor.getItems().add(numeProfesor);
            }
            statementNumeProfesori.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numeProfesor.setCellValueFactory(new PropertyValueFactory<>("Nume"));
        mailProfesor.setCellValueFactory(new PropertyValueFactory<>("Mail"));
        telefonProfesor.setCellValueFactory(new PropertyValueFactory<>("Telefon"));

        generareTabelSiChoice();
    }

    public void addProf(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            PreparedStatement querry = connectdb.prepareStatement("insert into profesor(Nume, Mail, Telefon) values (?, ?, ?)");
            querry.setString(1, textNume.getText());
            querry.setString(2, textMail.getText());
            querry.setString(3, textTelefon.getText());
            querry.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabelProfesori.getItems().clear();
        alegeProfesor.getItems().clear();
        textNume.clear(); textMail.clear(); textTelefon.clear();
        generareTabelSiChoice();
    }

    public void deleteProf(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            Statement statement = connectdb.createStatement();
            String querry = "delete from profesor where Nume = '" + alegeProfesor.getValue() + "'";
            statement.executeUpdate(querry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabelProfesori.getItems().clear();
        alegeProfesor.getItems().clear();
        generareTabelSiChoice();
    }
}