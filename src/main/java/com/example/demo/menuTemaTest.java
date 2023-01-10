package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class menuTemaTest {

    @Test
    void addTema() {
        try{
            String detalii = "";
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select DetaliiTema from tema where disciplina like('SDA%') and termen = '2023-01-09'";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                detalii = resultSet.getString("DetaliiTema");
            }
            Assertions.assertEquals("Proiect structuri de date", detalii);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void deleteTema() {
        try{
            String detalii = "";
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querry = "select DetaliiTema from tema where disciplina like('DPPD%') and termen = '2023-01-09'";
            Statement statement = connectdb.createStatement();
            ResultSet resultSet= statement.executeQuery(querry);
            while(resultSet.next()){
                detalii = resultSet.getString("DetaliiTema");
            }
            Assertions.assertEquals("", detalii);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}