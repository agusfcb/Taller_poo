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
    private static final String[] Roles = {"Administrador", "Maitre", "Mesero", "Recepcionista"};
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
    public Empleado(ArrayList<Reserva> reservas, String rol, String permiso, String name, String tel, String email, String pass) {
        super(name, tel, email, pass);
        this.reservas = reservas;
        this.rol = rol;
        this.permiso = permiso;
        //Nota para Juan: arriba entre parentesis esta el atributo idUduario
        // y aca falta por eso marca el error aca, igual no debe ir, borralo arriba si podes jaja
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
    private boolean esRolValido(String rol) {
        for(String r : Roles) {
            if (r.equals(rol)) {
                return true;
            }
        }
        return false;
    }
    public void setRol(String rol) {
        if (esRolValido(rol)) {
            this.rol = rol;
        }
    }

    public String getIdEmpleado() {
        return this.getIdUsuario();
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
    // Metodo para mostrar el rol(Mesero, Recepcionista, Administrador o Maitre)del empleado con su ID 
    public String mostrarRol(Empleado empleado) {
        return "ID: " + empleado.getIdEmpleado() + ", Rol: " + empleado.getRol();
    }
    
    // Metodo para ver los comentarios de las reservas con respecto a su ID
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