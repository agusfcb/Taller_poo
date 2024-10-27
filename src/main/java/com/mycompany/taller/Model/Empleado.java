package com.mycompany.taller.Model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


/**
 * 
 * @author Agustin y Juan
 */
public class Empleado extends Usuario {
    
    private ArrayList<Reserva> reservas;
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    /**
     * Default constructor
     */
    public Empleado() {
        super();
        this.reservas = new ArrayList<>();
    }

    public Empleado(String name, String tel, LocalDate fechaCumple, String email, String pass, String rol, String genero) {
        super(name, tel, fechaCumple, email, pass, rol, genero);
        this.reservas = Reserva.getListaReservas();
        Empleado.listaEmpleados.add(this);

    }

    public Empleado(String name, String tel, LocalDate fechaCumple, String email, String pass, String rol, String genero, long idUsuario){
        super(name, tel, fechaCumple, email, pass, rol, genero, idUsuario);
        
    }
    
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getRolEmpleado() {
        return this.getRol();
    }
    public long getIdEmpleado() {
        return this.getIdUsuario();
    }

    public static ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public static void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        Empleado.listaEmpleados = listaEmpleados;
    }
    
    /**
     * Metodo para mostrar el rol del empleado (Administrador, Maitre, Recepcionista o Mesero)
     * @param empleado objeto del tipo Empleado
     * @return devuelve un string del rol del empleado
     */
    public String mostrarRol(Empleado empleado) {
        return "ID: " + empleado.getIdEmpleado() + ", Rol: " + empleado.getRolEmpleado();
    }
    
    /**
     * Metodo para ver los comentarios de las reservas con respecto al ID
     * @param idReserva id de la reserva de la cual se quiere ver los comentarios
     * @return String del comentario de la reserva
    */
    public String verComentarios(long idReserva) {
        for(int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if (String.valueOf(reserva.getIdReserva()).equals(String.valueOf(idReserva))) {
                return "Comentarios de la reserva " + idReserva + ": " + reserva.getComentarios();
            }
        }
        return "Reserva con ID " + idReserva + " no encontrada.";
    }
    
}