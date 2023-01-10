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

public class menuDisciplina implements Initializable {
    //addDisciplina
    public TextField textNume;
    public TextField textCurs;
    public TextField textSeminar;

    //Disciplina Table
    public TableView<Disciplina> tabelDiscipline;
        public TableColumn<Disciplina, String> numeDisciplina;
        public TableColumn<Disciplina, String> cursDisciplina;
        public TableColumn<Disciplina, String> seminarDisciplina;

    //deleteDisciplina
    public ChoiceBox<String> alegeDeleteDisciplina;

    public void generareTabelSiChoice() {
        try {
            List<Disciplina> tabelDiscipline = new ArrayList<>();
            DatabaseConnection connectionTabelDiscipline = new DatabaseConnection();
            Connection connectdbTabelDiscipline = connectionTabelDiscipline.getConnection();
            String querryTabelDiscipline = "select * from disciplina";
            Statement statementTabelDiscipline = connectdbTabelDiscipline.createStatement();
            ResultSet resultSetTabelDiscipline = statementTabelDiscipline.executeQuery(querryTabelDiscipline);
            while (resultSetTabelDiscipline.next()){
                tabelDiscipline.add(new Disciplina(resultSetTabelDiscipline.getString("Nume"), resultSetTabelDiscipline.getString("SalaCurs"), resultSetTabelDiscipline.getString("SalaSeminar")));
            }
            for(Disciplina disciplina: tabelDiscipline){
                this.tabelDiscipline.getItems().add(disciplina);
            }
            statementTabelDiscipline.close();

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
                alegeDeleteDisciplina.getItems().add(disciplina);
            }
            statementNumeDiscipline.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numeDisciplina.setCellValueFactory(new PropertyValueFactory<>("Nume"));
        cursDisciplina.setCellValueFactory(new PropertyValueFactory<>("SalaCurs"));
        seminarDisciplina.setCellValueFactory(new PropertyValueFactory<>("SalaSeminar"));

        generareTabelSiChoice();
    }

    public void addDisciplina(ActionEvent actionEvent) {
        if(textNume.getText().isBlank() || textSeminar.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai introdus toate datele necesare!");
            alert.showAndWait();

            tabelDiscipline.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            textNume.clear(); textCurs.clear(); textSeminar.clear();
            generareTabelSiChoice();
        }
        else {
            try {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectdb = connection.getConnection();
                PreparedStatement querry = connectdb.prepareStatement("insert into disciplina(Nume, SalaCurs, SalaSeminar) values (?, ?, ?)");
                querry.setString(1, textNume.getText());
                querry.setString(2, textCurs.getText());
                querry.setString(3, textSeminar.getText());
                querry.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Felicitari boss");
                alert.setHeaderText("Disciplina a fost adaugata!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme boss");
                alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
                alert.showAndWait();
            }

            tabelDiscipline.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            textNume.clear(); textCurs.clear(); textSeminar.clear();
            generareTabelSiChoice();
        }
    }

    public void deleteDisciplina(ActionEvent actionEvent) {
        if(alegeDeleteDisciplina.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai selectat tot ce trebuia!");
            alert.showAndWait();

            tabelDiscipline.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            generareTabelSiChoice();
        }
        else{
            try {
                DatabaseConnection connectionOrar = new DatabaseConnection();
                Connection connectdbOrar = connectionOrar.getConnection();
                Statement statementOrar = connectdbOrar.createStatement();
                String querryOrar = "delete from orar where Disciplina = '" + alegeDeleteDisciplina.getValue() + "'";
                statementOrar.executeUpdate(querryOrar);
                statementOrar.close();

                DatabaseConnection connectionDisciplina = new DatabaseConnection();
                Connection connectdbDisciplina = connectionDisciplina.getConnection();
                Statement statementDisciplina = connectdbDisciplina.createStatement();
                String querryDisciplina = "delete from disciplina where Nume = '" + alegeDeleteDisciplina.getValue() + "'";
                statementDisciplina.executeUpdate(querryDisciplina);
                statementDisciplina.close();

                DatabaseConnection connectionTema = new DatabaseConnection();
                Connection connectdbTema = connectionTema.getConnection();
                Statement statementTema = connectdbTema.createStatement();
                String querryTema = "delete from tema where Disciplina = '" + alegeDeleteDisciplina.getValue() + "'";
                statementTema.executeUpdate(querryTema);
                statementTema.close();

                DatabaseConnection connectionNota = new DatabaseConnection();
                Connection connectdbNota = connectionNota.getConnection();
                Statement statementNota = connectdbNota.createStatement();
                String querryNota = "delete from nota where Disciplina = '" + alegeDeleteDisciplina.getValue() + "'";
                statementNota.executeUpdate(querryNota);
                statementNota.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Felicitari boss");
                alert.setHeaderText("Disciplina, temele, notele si orele la disciplina respectiva au fost sterse!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme boss");
                alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
                alert.showAndWait();
            }

            tabelDiscipline.getItems().clear();
            alegeDeleteDisciplina.getItems().clear();
            generareTabelSiChoice();
        }
    }
}