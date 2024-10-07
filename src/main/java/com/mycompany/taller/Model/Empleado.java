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
    private String rol;
    private String permiso;
    /**
     * Default constructor
     */
    public Empleado() {
        super();
        this.reservas = new ArrayList<>();
        this.rol = "Rol Desconocido";
        this.permiso = "Permiso Desconocido";
    }
    /**
     * 
     * @param reservas
     * @param name
     * @param tel
     * @param email
     * @param pass
     * @param rol
     * @param permiso
     * @param idUsuario 
     */
    public Empleado(ArrayList<Reserva> reservas, String name, String tel, String email, String pass, String rol, String permiso, String idUsuario) {
        super(name, tel, email, pass, idUsuario);
        this.reservas = reservas;
        this.rol = rol;
        this.permiso = permiso;
        this.registrarUsuario(name, tel, email, pass);
    }
    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getIdEmpleado() {
        return super.getIdUsuario();
    }
    public String getPermiso() {
        return permiso;
    }
    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }
    
    public void agregarReservas(Reserva r) {
        this.reservas.add(r);
    }
    public String devolverRol(String idEmpleado) {
        if (super.getIdUsuario().equals(idEmpleado)) {
            return this.rol;
        }
        return "Empleado no encontrado";
    }
    public String mostrarRol(Empleado empleado) {
        return "ID: " + empleado.getIdEmpleado() + ", Rol: " + empleado.getRol();
    }
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