package com.mycompany.taller.Model;
import java.util.*;
import java.time.LocalDate;
/**
 * 
 */
public class Cliente extends Usuario {

    private static ArrayList<Reserva> agendaReservas = new ArrayList<>();
    
    /**
     * Default constructor
     */
    public Cliente() {
        super();
    }
    
    public Cliente(String a, String b, String c, String d){
        super(a, b, c, d);
        // A COMPLETAR
    }



    /**
     * @param Date fecha 
     * @param Date hora 
     * @return
     */
    
    private ArrayList<String> buscarMesaDisponible(LocalDate fecha, LocalDate hora) {
        public LocalDate fecha1 = fecha;
        public LocalDate hora1 = hora;
        public ArrayList<String> subLista = new ArrayList<>();
        
        //Sublista debe ser una lista con los horarios disponibles

        
        /* CODIGO QUE AUN NO FUNCIONA POR PROBLEMA CON NETBEANS 
        for (int i = 0; i < agendaReservas.size() ; i++) {
            // Recorre la lista y va removiendo los horarios ocupados
        }
        */
        
        return subLista;
    }

    /**
     * @param Date fecha 
     * @param Date hora
     */
    public void verMesasDisponibles(void Date fecha, void Date hora) {
        // TODO implement here
    }

    /**
     * @param DateTime fecha 
     * @param DateTime hora 
     * @param Mesa mesa
     */
    public void crearReservar(void DateTime fecha, void DateTime hora, void Mesa mesa) {
        // TODO implement here
    }

    /**
     * @param idReserva 
     * @return
     */
    public boolean cacelarReserva(void idReserva) {
        // TODO implement here
        return false;
    }

    /**
     * @param DateTime fecha 
     * @param DateTime hora 
     * @param Mesa mesa 
     * @return
     */
    public boolean modificarReserva(void DateTime fecha, void DateTime hora, void Mesa mesa) {
        // TODO implement here
        return false;
    }

    /**
     * @param tuplaDeString 
     * @return
     */
    public boolean actualizarInformacion(void tuplaDeString) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public String verHistorial() {
        // TODO implement here
        return "";
    }

    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }

}