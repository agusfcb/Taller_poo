package com.mycompany.taller.Model;
import java.util.*;
import java.time.LocalDate;

/**
 * 
 */
public class Cliente extends Usuario {

    private ArrayList<Reserva> agendaReservas = new ArrayList<>();
    
    /**
     * Default constructor
     */
    public Cliente() {
        super();
    }
    /*
     * Constructor parametrizado
     */
    public Cliente(String name, String tel, String email, String pass){
        this.registrarUsuario(name, tel, email, pass);
    }
    
    /**
     * @param Date fecha 
     * @param Date hora 
     * @return
     */
    
    private ArrayList<String> buscarMesaDisponible(String fecha1, String hora1) {
        ArrayList<Reserva> listaAuxReservas = new ArrayList<>();
        listaAuxReservas = Reserva.GetListaReservas();
        
        ArrayList<String> filtroFecha = new ArrayList<>();
        ArrayList<String> filtroHora = new ArrayList<String>();
        ArrayList<Mesa> mesasDisponibles;
        
        for (Reserva extraer : listaAuxReservas) {
            filtroFecha.add(extraer.getDia());
        }
        if (!filtroFecha.contains(fecha)) {
        // Si no hay ninguna reserva en esa fecha todas las ubicaciones estan libres a cualquier hora
            mesasDisponibles = Mesa.mesasExistentes.clone();
            return mesasDisponibles;
        }
        else {
            //PENDIENTE FILTRAR CUANDO HAY OCUPACION EN DICHO DIA
            }
        }
    }

    /**
     * @param Date fecha 
     * @param Date hora
     */
    public ArrayList<Mesa> verMesasDisponibles(LocalDate fecha, LocalDate hora){

    }

    /**
     * @param DateTime fecha 
     * @param DateTime hora 
     * @param Mesa mesa
     */
    public void crearReservar(String fecha, String hora, Mesa mesa) {
        
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