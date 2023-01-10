package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class menuDisciplinaTest {

    @Test
    void addDisciplina() {
        try{
            String nume = "";
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nume from disciplina where nume like('retelistica%')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                nume = resultSet.getString("Nume");
            }
            Assertions.assertEquals("retelistica", nume);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteDisciplina() {
        try{
            String nume = "";
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nume from disciplina where nume like('DPPD%')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                nume = resultSet.getString("Nume");
            }
            Assertions.assertEquals("", nume);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}