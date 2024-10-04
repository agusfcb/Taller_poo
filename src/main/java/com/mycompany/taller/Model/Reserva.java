package com.mycompany.taller.Model;


import java.util.*;
import java.time.LocalDate;

/**
 * 
 */
public class Reserva {

    /**
     * Default constructor
     */
    public Reserva() {
    }

    /**
     * 
     */
    private Date diaOcupado;

    /**
     * 
     */
    private Date horaOcupada;

    /**
     * 
     */
    private String comentarios;

    /**
     * 
     */
    private String idReserva;

    /**
     * 
     */
    private boolean asistencia;

    /**
     * @return
     */
    public String leerComentarios() {
        // TODO implement here
        return "";
    }

    /**
     * 
     */
    private void mandarRecordatorio() {
        // TODO implement here
    }

    /**
     * 
     */
    private void multa() {
        // TODO implement here
    }

    /**
     * @param DateTime fecha 
     * @param DateTime hora
     */
    public void verDisponibilidad(LocalDate fecha, LocalDate hora) {
        // TODO implement here
    }

}