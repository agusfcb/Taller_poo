package com.mycompany.taller.Model;


import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 
 */
public class Reserva {
    private String dia;
    private String hora;
    private final String[] estadosPosibles = new String[] {"Pendiente", "Sin asistir", "Completado"};
    private String estado;
    private Mesa mesaReservada;
    private Cliente clienteReserva;
    private String idReserva;
    private static ArrayList<Reserva> listaReservas = new ArrayList<>(); 
    
    private static final String[] horarios = new String[] {"11:00", "13:00", "15:00", "20:00", "22:00", "00:00"};


    /*
    *Constructor para el cliente que hace la reserva
    */
    public Reserva(String dia, String hora, Mesa mesa, Cliente cliente) {
        this.dia = dia;
        this.hora = hora;
        this.estado = estadosPosibles[0];
        this.mesaReservada = mesa;
        this.clienteReserva = cliente;
        this.idReserva = UUID.randomUUID().toString();
        Reserva.listaReservas.add(this);
    }
    
    /*
    *Constructor para el administrador que gestiona eventos
    */
    public Reserva(String dia, String hora, Mesa mesa) {
        this.dia = dia;
        this.hora = hora;
        this.mesaReservada = mesa;
        this.idReserva = UUID.randomUUID().toString();
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
    
    public Mesa getMesaReservada() {
        return mesaReservada;
    }

    public void setMesaReservada(Mesa mesaReservada) {
        this.mesaReservada = mesaReservada;
    }

    public Cliente getClienteReserva() {
        return clienteReserva;
    }

    public void setClienteReserva(Cliente clienteReserva) {
        this.clienteReserva = clienteReserva;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    
    public static ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }   
}