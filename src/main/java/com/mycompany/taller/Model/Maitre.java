package com.mycompany.taller.Model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Clase para representar al maitre y las funciones que puede realizar
 * @author Agustin y Juan
 */
public class Maitre extends Empleado {

    private String permisos = "Ver reservas, ver comentarios de reservas";
    
    public Maitre() {
        super();
    }
    
    public Maitre(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Maitre", genero);
    }
    public Maitre(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero, String idUsuario){
        super(name, tel, fechaCumple, email, pass, "Maitre", genero, idUsuario);
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