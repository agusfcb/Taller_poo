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
    
    /** Metodo para filtrar mesas disponibles por dia
     * @param String fecha 
     * @param String hora 
     * @return ArrayList<String>
     */
    
    private ArrayList<Mesa> buscarMesaDisponible(String fecha1, String hora1) {
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas();
        ArrayList<Reserva> reservaDia = new ArrayList<>();
        ArrayList<Mesa> mesasDisponibles = Mesa.getMesasTot();
        ArrayList<Mesa> mesasTotales = Mesa.getMesasTot();
        
        reservaDia = this.buscarMesaDia(listaReservas, fecha1);
        mesasDisponibles = this.buscarMesasOcupadas(mesasTotales, reservaDia, hora1);
        return mesasDisponibles;
    }

    /*
    * Metodo para filtrar mesas disponibles por dia
    */
    
    private ArrayList<Reserva> buscarMesaDia(ArrayList<Reserva> listaReservas, String fecha) {
        ArrayList<Reserva> filtroFecha = new ArrayList<>();
        boolean vacioCompleto = true;
        
        for (Reserva extraer : listaReservas) {
            if (extraer.getDia().equals(fecha)) {
                vacioCompleto = false;
                filtroFecha.add(extraer);
            }
        }
        if (vacioCompleto){
            return null;
        } else {
        return filtroFecha;
        }
    }
    /*
    * Metodo para filtrar mesas disponibles por hora
    */
    
    private ArrayList<Mesa> buscarMesasOcupadas(ArrayList<Mesa> mesasTotales, ArrayList<Reserva> listaAux, String hora) {
        ArrayList<String> filtroHora = new ArrayList<>();
        ArrayList<Mesa> mesasLibres = new ArrayList<>();
        
        // Copia profunda manual
        for (Mesa mesaAdd : mesasTotales){
            mesasLibres.add(mesaAdd);
        }
        
        boolean horarioVacio = true;
        
        for (Reserva extraer2 : listaAux) {
            if (extraer2.getHora().equals(hora)){
                mesasLibres.remove(extraer2.getMesaReservada());
                horarioVacio = false;
            }
        }
        if (horarioVacio) {
            return mesasTotales;
        }else {
            return mesasLibres;
        }
    }
    
    
    /**
     * @param Date fecha 
     * @param Date hora
     */
    public ArrayList<Mesa> verMesasDisponibles(LocalDate fecha, LocalDate hora){
        //Pendiente para visualizacion o entrega de datos
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