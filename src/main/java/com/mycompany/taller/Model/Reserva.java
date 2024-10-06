package com.mycompany.taller.Model;


import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 */
public class Reserva {
    private String dia;
    private String hora;
    private final String[] estadosPosibles = new String[] {"Pendiente", "Sin asistir", "Completado"};
    private String estado;
    private Mesa mesaAux;
    private Cliente clienteAux;
    private static ArrayList<Reserva> listaReservas = new ArrayList<>(); 
    
    private static final String[] horarios = new String[] {"11:00", "13:00", "15:00", "20:00", "22:00", "00:00"};

    public Reserva(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
        this.estado = estadosPosibles[0];
        Reserva.listaReservas.add(this);
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}