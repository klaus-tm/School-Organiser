package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class menuHome implements Initializable {
    public TableView<Orar> tabelOrar;
        public TableColumn<Orar, String> disciplinaOrar;
        public TableColumn<Orar, String> profesorOrar;
        public TableColumn<Orar, String> salaOrar;
        public TableColumn<Orar, String> oraOrar;

    public TableView<Tema> tabelTeme;
        public TableColumn<Tema, String> disciplinaTeme;
        public TableColumn<Tema, String> temaTeme;
        public TableColumn<Tema, String> termenTeme;

    public ChoiceBox<String> alegeDisciplina;

    public TableView<Nota> tabelNote;
        public TableColumn<Nota, Double> notaNote;
        public TableColumn<Nota, Boolean> peste5Note;

    public String getZi(){
        String zi = "";
        LocalDate date = LocalDate.now();
        if(date.getDayOfWeek().equals(DayOfWeek.MONDAY))
            zi = "Luni";
        else if(date.getDayOfWeek().equals(DayOfWeek.TUESDAY))
            zi = "Marti";
        else if(date.getDayOfWeek().equals(DayOfWeek.WEDNESDAY))
            zi = "Miercuri";
        else if(date.getDayOfWeek().equals(DayOfWeek.THURSDAY))
            zi = "Joi";
        else if(date.getDayOfWeek().equals(DayOfWeek.FRIDAY))
            zi = "Vineri";
        return zi;
    }
    public void generareTabeleSiChoice() {
        try {
            List<Orar> tabelOre = new ArrayList<>();
            String zi = getZi();
            DatabaseConnection connectionTabelOre = new DatabaseConnection();
            Connection connectdbTabelOre = connectionTabelOre.getConnection();
            String querryTabelOre = "select * from orar where zi like('"+ zi + "')";
            Statement statementTabelOre = connectdbTabelOre.createStatement();
            ResultSet resultSetTabelOre = statementTabelOre.executeQuery(querryTabelOre);
            while(resultSetTabelOre.next()) {
                tabelOre.add(new Orar(resultSetTabelOre.getString("Profesor"), resultSetTabelOre.getString("Disciplina"), resultSetTabelOre.getString("Sala"), resultSetTabelOre.getString("Zi"), resultSetTabelOre.getInt("Ora")));
            }
            for(Orar ora: tabelOre){
                this.tabelOrar.getItems().add(ora);
            }
            statementTabelOre.close();

            List<Tema> tabelTeme = new ArrayList<>();
            DatabaseConnection connectionTabelTeme = new DatabaseConnection();
            Connection connectdbTabelTeme = connectionTabelTeme.getConnection();
            String querryTabelTeme = "select * from tema where (extract(WEEK from Termen) in(week(curdate())))";
            Statement statementTabelTeme = connectdbTabelTeme.createStatement();
            ResultSet resultSetTabelTeme = statementTabelTeme.executeQuery(querryTabelTeme);
            while(resultSetTabelTeme.next()) {
                tabelTeme.add(new Tema(resultSetTabelTeme.getString("Disciplina"), resultSetTabelTeme.getString("DetaliiTema"), resultSetTabelTeme.getDate("Termen"), resultSetTabelTeme.getBoolean("Terminata")));
            }
            for(Tema tema: tabelTeme) {
                this.tabelTeme.getItems().add(tema);
            }
            statementTabelTeme.close();

            List<String> numeDiscipline = new ArrayList<>();
            DatabaseConnection connectionNumeDiscipline = new DatabaseConnection();
            Connection connectdbNumeDiscipline = connectionNumeDiscipline.getConnection();
            String querryNumeDiscipline = "select Nume from disciplina";
            Statement statementNumeDiscipline = connectdbNumeDiscipline.createStatement();
            ResultSet resultSetNumeDiscipline = statementNumeDiscipline.executeQuery(querryNumeDiscipline);
            while (resultSetNumeDiscipline.next()){
                numeDiscipline.add(resultSetNumeDiscipline.getString("Nume"));
            }
            for(String disciplina: numeDiscipline){
                alegeDisciplina.getItems().add(disciplina);
            }
            statementNumeDiscipline.close();
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disciplinaOrar.setCellValueFactory(new PropertyValueFactory<>("NumeDisciplina"));
        profesorOrar.setCellValueFactory(new PropertyValueFactory<>("NumeProfesor"));
        salaOrar.setCellValueFactory(new PropertyValueFactory<>("Sala"));
        oraOrar.setCellValueFactory(new PropertyValueFactory<>("OraTabel"));

        disciplinaTeme.setCellValueFactory(new PropertyValueFactory<>("NumeDisciplina"));
        temaTeme.setCellValueFactory(new PropertyValueFactory<>("DetaliiTema"));
        termenTeme.setCellValueFactory(new PropertyValueFactory<>("TermenTabel"));

        notaNote.setCellValueFactory(new PropertyValueFactory<>("Nota"));
        peste5Note.setCellValueFactory(new PropertyValueFactory<>("Peste5"));

        generareTabeleSiChoice();

        alegeDisciplina.setOnAction(this::setTabelNote);
    }

    private void setTabelNote(ActionEvent actionEvent) {
        try {
            tabelNote.getItems().clear();
            List<Nota> tabelNote = new ArrayList<>();
            DatabaseConnection connectionTabelNote = new DatabaseConnection();
            Connection connectdbTabelNote = connectionTabelNote.getConnection();
            String querryTabelNote = "select * from nota where disciplina like('" + alegeDisciplina.getValue() + "" +
                    "%')";
            Statement statementTabelNote = connectdbTabelNote.createStatement();
            ResultSet resultSetTabelNote = statementTabelNote.executeQuery(querryTabelNote);
            while(resultSetTabelNote.next()) {
                tabelNote.add(new Nota(resultSetTabelNote.getString("Disciplina"), resultSetTabelNote.getString("DetaliiNota"), resultSetTabelNote.getDouble("Nota")));
            }
            for(Nota nota: tabelNote) {
                this.tabelNote.getItems().add(nota);
            }
            statementTabelNote.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }
}