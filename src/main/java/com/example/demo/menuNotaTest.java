package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class menuNotaTest {

    @Test
    void addNota() {
        try{
            double nota = 0;
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nota from nota where disciplina like('TGC%')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                nota = resultSet.getDouble("Nota");
            }
            Assertions.assertEquals(7, nota);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteNota() {
        try{
            double nota = 0;
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nota from nota where disciplina like('DPPD%')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                nota = resultSet.getDouble("Nota");
            }
            Assertions.assertEquals(0, nota);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}