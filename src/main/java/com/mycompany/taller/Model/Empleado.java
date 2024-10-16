package com.mycompany.taller.Model;
import java.util.*;

/**
 * 
 */
//Formato metodos:
    /**
     * @param String idReserva 
     * @param String rol
     * @return
     */
public class Empleado extends Usuario {
    private ArrayList<Reserva> reservas;

    /**
     * Default constructor
     */
    public Empleado() {
        super();
        this.reservas = new ArrayList<>();
    }
    /**
     * 
     * 
     * @param rol
     * @param name
     * @param tel
     * @param email
     * @param pass
     * 
     */
    public Empleado(String name, String tel, String email, String pass, String rol) {
        super(name, tel, email, pass, rol);
        this.reservas = Reserva.getListaReservas();

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
        return this.getIdUsuario();
    }
    public String getIdEmpleado() {
        return this.getIdUsuario();
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

    @Override
    public void validarUsuario(String usuario, String contrasenia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void iniciarSesion(String correo, String contrasenia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}