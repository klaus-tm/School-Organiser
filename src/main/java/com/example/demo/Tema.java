package com.example.demo;

public class Tema {
    private String numeDisciplina;
    private String detaliiTema;
    private String termen;
    private Boolean terminata;

    public Tema(String numeDisciplina, String detaliiTema, String termen, Boolean terminata){
        this.numeDisciplina = numeDisciplina;
        this.detaliiTema = detaliiTema;
        this.termen = termen;
        this.terminata = terminata;
    }

    public String getNumeDisciplina() {
        return numeDisciplina;
    }

    public String getDetaliiTema() {
        return detaliiTema;
    }

    public String getTermen() {
        return termen;
    }

    public boolean isTerminata() {
        return terminata;
    }

    public void setNumeDisciplina(String numeDisciplina) {
        this.numeDisciplina = numeDisciplina;
    }

    public void setDetaliiTema(String detaliiTema) {
        this.detaliiTema = detaliiTema;
    }

    public void setTermen(String termen) {
        this.termen = termen;
    }

    public void setTerminata(Boolean terminata) {
        this.terminata = terminata;
    }
}