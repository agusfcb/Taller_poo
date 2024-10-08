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
    private String numero;
    private String capacidad;
    private String ubicacion;
    private ArrayList<Reserva> reservasMesa = new ArrayList<>();
    private static ArrayList<Mesa> mesasExistentes = new ArrayList<>();

    public Mesa() {
    }
    
    public Mesa(String numero, String capacidad, String ubicacion) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        Mesa.mesasExistentes.add(this);
    }

    public String getNumero() {
        return numero;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public static ArrayList<Mesa> getMesasTot(){
        return mesasExistentes;
    }
    
    public void agregarReserva(Reserva resv){
        this.reservasMesa.add(resv);
    }
    
}