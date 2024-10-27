package com.mycompany.taller.Model;
import java.time.LocalDate;
import java.time.LocalTime;
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
    
    public Maitre(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Maitre", genero);
    }
    public Maitre(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero, long idUsuario){
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
     * @param idReserva id de la reserva de la cual se quiere ver el comentario
     * @return String del comentario de la reserva
    */
    @Override
    public String verComentarios(long idReserva) {
        for(Reserva reserva : getReservas()) {
            if (String.valueOf(reserva.getIdReserva()).equals(String.valueOf(idReserva))) {
                return "Comentarios de la reserva " + idReserva + ": " + reserva.getComentarios();
            }
        }
        return "Reserva con ID " + idReserva + " no encontrada.";
    }

}