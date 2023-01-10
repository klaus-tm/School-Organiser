package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import jfxtras.scene.control.agenda.Agenda;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;



public class menuOrar implements Initializable {

    public ChoiceBox<String> alegeProfesor;
    public ChoiceBox<String> alegeDisciplina;
    public ChoiceBox<String> alegeSala;
    public ChoiceBox<String> alegeZi;
    public ChoiceBox<String> alegeOra;
    public Agenda orar;
    List<String> zile = new ArrayList<String>(){{
        add("Luni"); add("Marti"); add("Miercuri"); add("Joi"); add("Vineri");
    }};
    List<String> ore = new ArrayList<String>(){{
        add("8:00-9:30"); add("9:40-11:10"); add("11:20-12:50"); add("13:00-14:30"); add("14:40-16:10"); add("16:20-17:50"); add("18:00-19:30"); add("19:40-21:10");
    }};
    public void generareOrarSiChoice() {
        try {
            List<Orar> Ore = new ArrayList<>();
            DatabaseConnection connectionOre = new DatabaseConnection();
            Connection connectdbOre = connectionOre.getConnection();
            String querryOre = "select * from orar";
            Statement statementOre = connectdbOre.createStatement();
            ResultSet resultSetOre = statementOre.executeQuery(querryOre);
            while(resultSetOre.next()) {
                Ore.add(new Orar(resultSetOre.getString("Profesor"), resultSetOre.getString("Disciplina"), resultSetOre.getString("Sala"), resultSetOre.getString("Zi"), resultSetOre.getInt("Ora")));
            }
            for(Orar ora: Ore) {
                Integer minute = ora.getOra()%100 + 30, hour = ora.getOra()/100 + 1;
                if(minute > 60){
                    minute -= 60;
                    hour += 1;
                }
                if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.MONDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+4).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+4).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
                else if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.TUESDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
                else if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
                else if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.THURSDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
                else if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-4).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-4).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
                else if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-5).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-5).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-4).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-4).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()-1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
                else if(orar.getDisplayedLocalDateTime().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                    if(ora.getZi().equals("Luni"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+1).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Marti"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+2).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );

                    else if(ora.getZi().equals("Miercuri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+3).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Joi"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+4).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+4).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                    else if(ora.getZi().equals("Vineri"))
                        orar.appointments().add(
                                new Agenda.AppointmentImplLocal()
                                        .withStartLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+5).atTime(ora.getOra()/100, ora.getOra()%100))
                                        .withEndLocalDateTime(LocalDate.of(orar.getDisplayedLocalDateTime().getYear(), orar.getDisplayedLocalDateTime().getMonth(), orar.getDisplayedLocalDateTime().getDayOfMonth()+5).atTime(hour, minute))
                                        .withDescription(ora.getNumeProfesor())
                                        .withSummary(ora.getNumeDisciplina() + " (" + ora.getSala() + ")")
                        );
                }
            }
            statementOre.close();

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

            for(String zi: zile){
                alegeZi.getItems().add(zi);
            }

            for(String ora: ore){
                alegeOra.getItems().add(ora);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orar.setAllowDragging(false);
        orar.setAllowResize(false);
        orar.setLocale(Locale.ROOT);
        alegeDisciplina.setOnAction(this::setSala);

        generareOrarSiChoice();
    }

    private void setSala(ActionEvent actionEvent) {
        try {
            alegeSala.getItems().clear();
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            String querryCurs = "select SalaCurs from disciplina where nume like('" + alegeDisciplina.getValue() + "')";
            String querrySeminar = "select SalaSeminar from disciplina where nume like('" + alegeDisciplina.getValue() + "')";
            Statement statement = connectdb.createStatement();
            ResultSet resultSetCurs = statement.executeQuery(querryCurs);
            while (resultSetCurs.next()){
                if(resultSetCurs.getString("SalaCurs").isBlank())
                    break;
                alegeSala.getItems().add(resultSetCurs.getString("SalaCurs") + "-curs");
            }
            ResultSet resultSetSeminar = statement.executeQuery(querrySeminar);
            while (resultSetSeminar.next()){
                alegeSala.getItems().add(resultSetSeminar.getString("SalaSeminar") + "-seminar");
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }
    }

    public void addScedule(ActionEvent actionEvent) {
        if(alegeProfesor.getSelectionModel().isEmpty() || alegeDisciplina.getSelectionModel().isEmpty() || alegeSala.getSelectionModel().isEmpty() || alegeZi.getSelectionModel().isEmpty() || alegeOra.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("N-ai selectat tot ce trebuia!");
            alert.showAndWait();

            orar.appointments().clear();
            alegeProfesor.getItems().clear();
            alegeDisciplina.getItems().clear();
            alegeSala.getItems().clear();
            alegeZi.getItems().clear();
            alegeOra.getItems().clear();
            generareOrarSiChoice();
        }
        else {
            try {
                String interval = alegeOra.getValue();
                String [] splitStart =interval.split("-");
                String [] oraStart=splitStart[0].split(":");
                Integer start = Integer.parseInt(oraStart[0]) * 100 + Integer.parseInt(oraStart[1]);
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectdb = connection.getConnection();
                PreparedStatement querry = connectdb.prepareStatement("insert into orar(Profesor, Disciplina, Sala, Zi, Ora) values (?, ?, ?, ?, ?)");
                querry.setString(1, alegeProfesor.getValue());
                querry.setString(2, alegeDisciplina.getValue());
                querry.setString(3, alegeSala.getValue());
                querry.setString(4, alegeZi.getValue());
                querry.setInt(5, start);
                querry.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Felicitari boss");
                alert.setHeaderText("Ora a fost adaugata!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Probleme boss");
                alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
                alert.showAndWait();
            }

            orar.appointments().clear();
            alegeProfesor.getItems().clear();
            alegeDisciplina.getItems().clear();
            alegeSala.getItems().clear();
            alegeZi.getItems().clear();
            alegeOra.getItems().clear();
            generareOrarSiChoice();
        }
    }

    public void deleteSchedule(ActionEvent actionEvent) {
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectdb = connection.getConnection();
            Statement statement = connectdb.createStatement();
            String querry = "truncate table orar";
            statement.executeUpdate(querry);
            statement.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Felicitari boss");
            alert.setHeaderText("Orarul a fosr sters!");
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probleme boss");
            alert.setHeaderText("A intervenit o problema! Operatiunea a fost anulata!");
            alert.showAndWait();
        }

        orar.appointments().clear();
        alegeProfesor.getItems().clear();
        alegeDisciplina.getItems().clear();
        alegeSala.getItems().clear();
        alegeZi.getItems().clear();
        alegeOra.getItems().clear();
        generareOrarSiChoice();
    }
}