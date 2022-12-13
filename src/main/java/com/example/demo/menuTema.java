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
import java.util.*;

public class menuTema implements Initializable {
    //addTema
    public ChoiceBox<String> alegeAddDisciplina;
    public TextArea textDetalii;
    public TextField textTermen;

    //Teme Table
    public TableView<Tema> tabelTeme;
        public TableColumn<Tema, String> disciplinaTema;
        public TableColumn<Tema, String> detaliiTema;
        public TableColumn<Tema, String> termenTema;
        public TableColumn<Tema, Boolean> terminataTema;

    //deleteTema
    public ChoiceBox<String> alegeDeleteDisciplina;
    public TextField textDeleteTermen;

    public void generareTabelSiChoiceuri() {
        try {
            List<Tema> tabelTeme = new ArrayList<>();
            DatabaseConnection connectionTabelTeme = new DatabaseConnection();
            Connection connectdbTabelTeme = connectionTabelTeme.getConnection();
            String querryTabelTeme = "select * from tema";
            Statement statementTabelTeme = connectdbTabelTeme.createStatement();
            ResultSet resultSetTabelTeme = statementTabelTeme.executeQuery(querryTabelTeme);
            while(resultSetTabelTeme.next()) {
                tabelTeme.add(new Tema(resultSetTabelTeme.getString("Disciplina"), resultSetTabelTeme.getString("DetaliiTema"), resultSetTabelTeme.getString("Termen"), resultSetTabelTeme.getBoolean("Terminata")));
            }
            for(Tema tema: tabelTeme) {
                this.tabelTeme.getItems().add(tema);
            }
            statementTabelTeme.close();

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
            String querryDeleteNumeDiscipline = "select Disciplina from tema where terminata = 0";
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
        disciplinaTema.setCellValueFactory(new PropertyValueFactory<>("NumeDisciplina"));
        detaliiTema.setCellValueFactory(new PropertyValueFactory<>("DetaliiTema"));
        termenTema.setCellValueFactory(new PropertyValueFactory<>("Termen"));
        terminataTema.setCellValueFactory(new PropertyValueFactory<>("Terminata"));

        generareTabelSiChoiceuri();
    }

    public void addTema(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            PreparedStatement querry = connectdb.prepareStatement("insert into tema(Disciplina, DetaliiTema, Termen, Terminata) values (?, ?, ?, ?)");
            querry.setString(1, alegeAddDisciplina.getValue());
            querry.setString(2, textDetalii.getText());
            querry.setString(3, textTermen.getText());
            querry.setBoolean(4, false);
            querry.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabelTeme.getItems().clear();
        alegeAddDisciplina.getItems().clear();
        alegeDeleteDisciplina.getItems().clear();
        textDetalii.clear(); textTermen.clear();
        generareTabelSiChoiceuri();
    }

    public void deleteTema(ActionEvent actionEvent) {
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            PreparedStatement querry = connectdb.prepareStatement("update tema set terminata = 1 where disciplina = ? and termen = ?");
            querry.setString(1, alegeDeleteDisciplina.getValue());
            querry.setString(2, textDeleteTermen.getText());
            querry.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabelTeme.getItems().clear();
        alegeAddDisciplina.getItems().clear();
        alegeDeleteDisciplina.getItems().clear();
        textDeleteTermen.clear();
        generareTabelSiChoiceuri();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    DatabaseConnection connection = new DatabaseConnection();
                    Connection connectdb = connection.getConnection();
                    Statement statement = connectdb.createStatement();
                    String querry = "delete from tema where terminata = 1";
                    statement.executeUpdate(querry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tabelTeme.getItems().clear();
                generareTabelSiChoiceuri();
            }
        };
        timer.schedule(task, 10000);
    }
}