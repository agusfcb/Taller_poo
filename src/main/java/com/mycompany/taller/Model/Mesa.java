package com.mycompany.taller.Model;


import java.util.*;
import java.lang.Integer;

/**
 * 
 */
//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
public class Mesa {
    private Integer numero;
    private Integer capacidad;
    private String ubicacion;
    private static ArrayList<Mesa> mesasExistentes = new ArrayList<>();

    public Mesa() {
    }
    
    public Mesa(Integer numero, Integer capacidad, String ubicacion) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        Mesa.mesasExistentes.add(this);
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}