package com.mycompany.taller.Model;
import java.util.*;
import java.time.LocalDateTime;

/**
 * 
 */
public class Recepcionista extends Empleado {
//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
    /**
     * Default Constructor
     */
    public Recepcionista() {
        super();
    }
    /**
     * Constructor parametrizado
     */
    public Recepcionista(ArrayList<Reserva> reservas, String rol, String permiso, String name, String tel, String email, String pass) {
        super(reservas, "Recepcionista", permiso, name, tel, email, pass);
    }
    public ArrayList<Reserva> buscarReservas(LocalDateTime fecha, LocalDateTime hora) {
        ArrayList<Reserva> reservaEncontrada = 
    }
    public void tomarDatosFecha(LocalDateTime fecha, LocalDateTime hora) {
    
    }
    public Reserva buscarId(String idReserva) {
        
    }
    public Reserva tomarID(String idReserva) {
    
    }
    public void cambiarEstadoReserva(String id) {
    
    }
    public void confirmarAsistencia(String id) {
    
    }
    public Reserva buscarNom(String nombre, ArrayList<Reserva> listRes) {
    
    }
    public Reserva tomarNombre(String nombre, ArrayList<Reserva> listRes) {
    
    }
}