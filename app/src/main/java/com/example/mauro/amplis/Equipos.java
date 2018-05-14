package com.example.mauro.amplis;

public class Equipos {

    private String marca;
    private String modelo;
    private int potencia;
    private String descripcion;

    public Equipos(){}
    public Equipos(String modelo, String marca, int potencia, String descripcion){
        setModelo(modelo);
        setMarca(marca);
        setPotencia(potencia);
        setDescripcion(descripcion);
    }

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

    public void setMarca(String marca){ this.marca = marca; }
    public void setModelo(String modelo){ this.modelo = modelo; }
    public void setPotencia(int potencia){
        this.potencia = potencia;
    }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }
}
