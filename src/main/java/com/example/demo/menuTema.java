package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class menuTema implements Initializable {
    //addTema
    public ChoiceBox<String> alegeAddDisciplina;
    public TextArea textDetalii;
    public DatePicker alegeTermen;

    //Teme Table
    public TableView<Tema> tabelTeme;
        public TableColumn<Tema, String> disciplinaTema;
        public TableColumn<Tema, String> detaliiTema;
        public TableColumn<Tema, Date> termenTema;
        public TableColumn<Tema, Boolean> terminataTema;

    //deleteTema
    public ChoiceBox<String> alegeDeleteDisciplina;
    public ChoiceBox<String> alegeDeleteTema;


    public void generareTabelSiChoiceuri() {
        try {
            List<Tema> tabelTeme = new ArrayList<>();
            DatabaseConnection connectionTabelTeme = new DatabaseConnection();
            Connection connectdbTabelTeme = connectionTabelTeme.getConnection();
            String querryTabelTeme = "select * from tema";
            Statement statementTabelTeme = connectdbTabelTeme.createStatement();
            ResultSet resultSetTabelTeme = statementTabelTeme.executeQuery(querryTabelTeme);
            while(resultSetTabelTeme.next()) {
                tabelTeme.add(new Tema(resultSetTabelTeme.getString("Disciplina"), resultSetTabelTeme.getString("DetaliiTema"), resultSetTabelTeme.getDate("Termen"), resultSetTabelTeme.getBoolean("Terminata")));
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
            String querryDeleteNumeDiscipline = "select Disciplina from tema where terminata = 0 group by Disciplina";
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
        alegeDeleteDisciplina.setOnAction(this::setDeteteTema);

        alegeTermen.setOnAction(e->{
            LocalDate myDate = alegeTermen.getValue();
            String format = myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            if(myDate.isBefore(LocalDate.now())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Probleme boss");
                alert.setHeaderText("Temele nu au termen in trecut.");
                alert.showAndWait();
                alegeTermen.getEditor().clear();
            }
        });
    }

    private void setDeteteTema(ActionEvent actionEvent) {
        try {
            alegeDeleteTema.getItems().clear();
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select detaliiTema from tema where terminata = 0 and disciplina like ('" + alegeDeleteDisciplina.getValue() + "')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet = statement.executeQuery(querry);
            while (resultSet.next()){
                alegeDeleteTema.getItems().add(resultSet.getString("detaliiTema"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addTema(ActionEvent actionEvent) {
        if(alegeAddDisciplina.getSelectionModel().isEmpty() || textDetalii.getText().isBlank() || alegeTermen.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai introdus toate datele necesare!");
            alert.showAndWait();

            tabelTeme.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            textDetalii.clear();
            alegeTermen.getEditor().clear();
            generareTabelSiChoiceuri();
        }
        else {
            try {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectdb = connection.getConnection();
                PreparedStatement querry = connectdb.prepareStatement("insert into tema(Disciplina, DetaliiTema, Termen, Terminata) values (?, ?, ?, ?)");
                querry.setString(1, alegeAddDisciplina.getValue());
                querry.setString(2, textDetalii.getText());
                querry.setDate(3, Date.valueOf(alegeTermen.getValue()));
                querry.setBoolean(4, false);
                querry.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitari boss");
            alert.setHeaderText("Tema a fost adaugata!");
            alert.showAndWait();

            tabelTeme.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            textDetalii.clear();
            alegeTermen.getEditor().clear();
            generareTabelSiChoiceuri();
        }
    }

    public void deleteTema(ActionEvent actionEvent) {
        if(alegeDeleteDisciplina.getSelectionModel().isEmpty() || alegeDeleteTema.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai selectat tot ce trebuia!");
            alert.showAndWait();

            tabelTeme.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            alegeDeleteTema.getItems().clear();
            generareTabelSiChoiceuri();
        }
        else{
            try {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectdb = connection.getConnection();
                PreparedStatement querry = connectdb.prepareStatement("update tema set terminata = 1 where disciplina = ? and DetaliiTema = ?");
                querry.setString(1, alegeDeleteDisciplina.getValue());
                querry.setString(2, alegeDeleteTema.getValue());
                querry.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitari boss");
            alert.setHeaderText("Tema a fost matcata ca terminata!");
            alert.showAndWait();

            tabelTeme.getItems().clear();
            alegeAddDisciplina.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            alegeDeleteTema.getItems().clear();
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
}