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
    public Recepcionista(String name, String tel, String email, String pass, ArrayList<Reserva> reservas, String rol, String permiso, String idUsuario) {
        this.registrarUsuario(name, tel, tel, name);
        super(reservas, rol, permiso, idUsuario);
    }
    public ArrayList<Reserva> buscarReservar(LocalDateTime fecha, LocalDateTime hora) {
        
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