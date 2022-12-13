package com.example.demo;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController implements Initializable {
    public Label exit;
    public StackPane contentArea;
    public JFXButton menuHome;
    public JFXButton menuProf;
    public JFXButton menuDisciplina;
    public JFXButton menuTema;
    public JFXButton menuNota;
    public JFXButton menuOrar;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);

        }catch (IOException ex){
            Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void home(javafx.event.ActionEvent actionEvent) throws IOException{
        menuHome.setOnMouseClicked(mouseEvent -> {
            menuHome.setStyle("-fx-background-color:  #A5F1E9");
            menuProf.setStyle("-fx-background-color:  #7FE9DE");
            menuDisciplina.setStyle("-fx-background-color:  #7FE9DE");
            menuTema.setStyle("-fx-background-color:  #7FE9DE");
            menuNota.setStyle("-fx-background-color:  #7FE9DE");
            menuOrar.setStyle("-fx-background-color:  #7FE9DE");
        });
        Parent fxml = FXMLLoader.load(getClass().getResource("home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void profesor(javafx.event.ActionEvent actionEvent) throws IOException{
        menuProf.setOnMouseClicked(mouseEvent -> {
            menuProf.setStyle("-fx-background-color:  #A5F1E9");
            menuHome.setStyle("-fx-background-color:  #7FE9DE");
            menuDisciplina.setStyle("-fx-background-color:  #7FE9DE");
            menuTema.setStyle("-fx-background-color:  #7FE9DE");
            menuNota.setStyle("-fx-background-color:  #7FE9DE");
            menuOrar.setStyle("-fx-background-color:  #7FE9DE");
        });
        Parent fxml = FXMLLoader.load(getClass().getResource("profesor.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void disciplina(javafx.event.ActionEvent actionEvent) throws IOException{
        menuDisciplina.setOnMouseClicked(mouseEvent -> {
            menuDisciplina.setStyle("-fx-background-color:  #A5F1E9");
            menuProf.setStyle("-fx-background-color:  #7FE9DE");
            menuHome.setStyle("-fx-background-color:  #7FE9DE");
            menuTema.setStyle("-fx-background-color:  #7FE9DE");
            menuNota.setStyle("-fx-background-color:  #7FE9DE");
            menuOrar.setStyle("-fx-background-color:  #7FE9DE");
        });
        Parent fxml = FXMLLoader.load(getClass().getResource("disciplina.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void tema(javafx.event.ActionEvent actionEvent) throws IOException{
        menuTema.setOnMouseClicked(mouseEvent -> {
            menuTema.setStyle("-fx-background-color:  #A5F1E9");
            menuProf.setStyle("-fx-background-color:  #7FE9DE");
            menuDisciplina.setStyle("-fx-background-color:  #7FE9DE");
            menuHome.setStyle("-fx-background-color:  #7FE9DE");
            menuNota.setStyle("-fx-background-color:  #7FE9DE");
            menuOrar.setStyle("-fx-background-color:  #7FE9DE");
        });
        Parent fxml = FXMLLoader.load(getClass().getResource("tema.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void nota(javafx.event.ActionEvent actionEvent) throws IOException{
        menuNota.setOnMouseClicked(mouseEvent -> {
            menuNota.setStyle("-fx-background-color:  #A5F1E9");
            menuProf.setStyle("-fx-background-color:  #7FE9DE");
            menuDisciplina.setStyle("-fx-background-color:  #7FE9DE");
            menuTema.setStyle("-fx-background-color:  #7FE9DE");
            menuHome.setStyle("-fx-background-color:  #7FE9DE");
            menuOrar.setStyle("-fx-background-color:  #7FE9DE");
        });
        Parent fxml = FXMLLoader.load(getClass().getResource("nota.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void orar(javafx.event.ActionEvent actionEvent) throws IOException{
        menuOrar.setOnMouseClicked(mouseEvent -> {
            menuOrar.setStyle("-fx-background-color:  #A5F1E9");
            menuProf.setStyle("-fx-background-color:  #7FE9DE");
            menuDisciplina.setStyle("-fx-background-color:  #7FE9DE");
            menuTema.setStyle("-fx-background-color:  #7FE9DE");
            menuNota.setStyle("-fx-background-color:  #7FE9DE");
            menuHome.setStyle("-fx-background-color:  #7FE9DE");
        });
        Parent fxml = FXMLLoader.load(getClass().getResource("orar.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}