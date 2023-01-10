package com.example.demo;

import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class menuProfesorTest {

    @org.junit.jupiter.api.Test
    void addProf() {
        try{
            String nume = "";
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nume from profesor where nume like('Carmen %')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                nume = resultSet.getString("Nume");
            }
            Assertions.assertEquals("Carmen Tiru", nume);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void deleteProf() {
        try{
            String nume = "";
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select nume from profesor where nume like('Carmen %')";
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