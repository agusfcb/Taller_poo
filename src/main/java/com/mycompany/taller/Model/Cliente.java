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
    
    public Cliente(String a, String b, String c, String d){
        // super(a, b, c, d);
        // A COMPLETAR
    }



    /**
     * @param Date fecha 
     * @param Date hora 
     * @return
     */
    
    private ArrayList<LocalDate> buscarMesaDisponible(LocalDate fecha, LocalDate hora) {
        LocalDate fecha1 = fecha;
        LocalDate hora1 = hora;
        //TODAVIA QUEDA DEFINIR EL FORMATO DE LA HORA, SI STRING O LOCALDATE
        ArrayList<LocalDate> subListaHora = new ArrayList<>();
        ArrayList<LocalDate> subListaDia = new ArrayList<>():

        for (int i = 0; i < agendaReservas.size() ; i++) {
            if (agendaReservas.get(i).diaOcupado == fecha1) {
                // DEBE EXISTIR UNA LISTA STATIC FINAL CON TODOS LOS HORARIOS FIJOS
                // SE CREA UNA LISTA NUEVA RESULTADO DE RESTAR DE LA LISTA DE HORARIOS LOS HORARIOS OCUPADOS
            }
            
        }
        
        return subListaHora;
    }

    /**
     * @param Date fecha 
     * @param Date hora
     */
    public void verMesasDisponibles(LocalDate fecha, LocalDate hora){
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