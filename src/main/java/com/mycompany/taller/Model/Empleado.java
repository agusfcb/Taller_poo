package com.mycompany.taller.Model;
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
    /**
     * 
     * Constructor parametrizado
     * @param rol
     * @param name
     * @param tel
     * @param email
     * @param pass
     * 
     */
    public Empleado(String name, String tel, LocalTime fechaCumple, String email, String pass, String rol, String genero) {
        super(name, tel, fechaCumple, email, pass, rol, genero);
        this.reservas = Reserva.getListaReservas();
        Empleado.listaEmpleados.add(this);

    }
    /**
     * 
     * @param name
     * @param tel
     * @param email
     * @param pass
     * @param rol
     * @param genero
     * @param idUsuario 
     */
    public Empleado(String name, String tel, LocalTime fechaCumple, String email, String pass, String rol, String genero, String idUsuario){
        super(name, tel, fechaCumple, email, pass, rol, genero, idUsuario);
        
    }
    
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
    /*
    private boolean esRolValido(String rol) {
        for(String r : Roles) {
            if (r.equals(rol)) {
                return true;
            }
        }
        return false;
    }
    */
    
   /*
    public void setRol(String rol) {
        if (esRolValido(rol)) {
            this.rol = rol;
        }
    }
    */
    public String getRolEmpleado() {
        return this.getRol();
    }
    public String getIdEmpleado() {
        return this.getIdUsuario();
    }

    public static ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public static void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        Empleado.listaEmpleados = listaEmpleados;
    }
    
    public void agregarReservas(Reserva r) {
        this.reservas.add(r);
    }
    /**
     * Metodo para mostrar el rol del empleado (Administrador, Maitre, Recepcionista o Mesero)
     * @param empleado
     * @return String
     */
    public String mostrarRol(Empleado empleado) {
        return "ID: " + empleado.getIdEmpleado() + ", Rol: " + empleado.getRolEmpleado();
    }
    
    /**
     * Metodo para ver los comentarios de las reservas con respecto al ID
     * @param idReserva
     * @return String
    */
    public String verComentarios(String idReserva) {
        for(int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if (reserva.getIdReserva().equals(idReserva)) {
                return "Comentarios de la reserva " + idReserva + ": " + reserva.getComentarios();
            }
        }
        return "Reserva con ID " + idReserva + " no encontrada.";
    }
    
}