package com.mycompany.taller.Model;
import java.util.*;

/**
 * 
 * @author Agustin y Juan
 */
public class Maitre extends Empleado {

    private String permisos = "Ver reservas, ver comentarios de reservas";
    
    public Maitre() {
        super();
    }
    
    public Maitre(String name, String tel, String email, String pass, String genero) {
        super(name, tel, email, pass, "Maitre", genero);
    }
    public Maitre(String name, String tel, String email, String pass, String genero, String idUsuario){
        super(name, tel, email, pass, "Maitre", genero, idUsuario);
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    

    /**
     * Metodo para ver los comentarios de las reservas con respecto al ID
     * @param idReserva
     * @return String
    */
    @Override
    public String verComentarios(String idReserva) {
        for(Reserva reserva : getReservas()) {
            if (reserva.getIdReserva().equals(idReserva)) {
                return "Comentarios de la reserva " + idReserva + ": " + reserva.getComentarios();
            }
        }
        return "Reserva con ID " + idReserva + " no encontrada.";
    }

}