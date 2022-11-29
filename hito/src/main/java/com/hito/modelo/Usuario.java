package com.hito.modelo;

import java.util.ArrayList;

public class Usuario {
    private String user;
    private String password;
    private ArrayList<String> recomendaciones;

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
        this.recomendaciones = new ArrayList<String>();
    }
    public Usuario(){
        this.user="";
        this.password="";
        this.recomendaciones = new ArrayList<String>();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getRecomendaciones() {
        return recomendaciones;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRecomendaciones(ArrayList<String> recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
}
