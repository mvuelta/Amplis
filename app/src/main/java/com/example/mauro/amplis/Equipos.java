package com.example.mauro.amplis;

import android.widget.ImageView;

/*
public class Equipos {

    private int id;
    private String marca;
    private String modelo;
    private int potencia;
    private String descripcion;

    //private ImageView logo;

    public Equipos(){
        super(); //Se agrego para el ArrayAdapter
    }
    public Equipos(int id, String modelo, String marca, int potencia, String descripcion){
        super();//Se agrego para el ArrayAdapter
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.potencia = potencia;
        this.descripcion = descripcion;
        //this.logo = logo;
    }

    public int getId(){ return id; }
    public String getMarca(){
        return marca;
    }
    public String getModelo(){
        return modelo;
    }
    public int getPotencia(){
        return potencia;
    }
    public String getDescripcion(){
        return descripcion;
    }

    public void setId(int id){ this.id = id; }
    public void setMarca(String marca){ this.marca = marca; }
    public void setModelo(String modelo){ this.modelo = modelo; }
    public void setPotencia(int potencia){
        this.potencia = potencia;
    }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
    //public void setLogo(ImageView logo) { this.logo = logo; }
}
*/

public class Equipos {

    private int id;
    private String marca;
    private String modelo;
    private int potencia;
    private String descripcion;

    public Equipos() {
    }

    public Equipos(int id, String modelo, String marca, int potencia, String descripcion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}