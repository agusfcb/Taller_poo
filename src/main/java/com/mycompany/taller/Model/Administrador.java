package com.mycompany.taller.Model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrador extends Empleado{

    private ArrayList<Reserva> listaReservaActualizada = new ArrayList<>();
    private ArrayList<Reserva> listaEventos = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();

    public Administrador(String name, String tel, String email, String pass) {
        super(name, tel, email, pass, "Administrador");
        
    }

    /**
     * 
     * @param idUsuario
     * @param nuevoRol
     * @return 
     */
    private boolean cambiarRol(String idUsuario, String nuevoRol) {
        ArrayList<Empleado> empleados = Empleado.getListaEmpleados();
        for (Usuario extUsu : empleados){
            if(extUsu.getIdUsuario().equals(idUsuario)){
                extUsu.setRol(nuevoRol);
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param idUsuario
     * @param nuevoRol
     * @return 
     */
    public boolean editarRol(String idUsuario, String nuevoRol) {
        return this.cambiarRol(idUsuario, nuevoRol);
    }

    
    /**
     * Método para actualizar la apertura del restaurante
     * @param nuevoHorario 
     */
    public void actualizarApertura(ArrayList<LocalTime> nuevoHorario) {
        Reserva.setHorarios(nuevoHorario);
    }

    /**
     * Método para actualizar el cierre del restaurante
     * @param nuevoHorarioCierre
     */
    public void actualizarCierre(ArrayList<LocalTime> nuevoHorarioCierre) {
        Reserva.setHorarios(nuevoHorarioCierre);
    }
    /**
     * 
     * @param fechaInicio
     * @param fechaHoraCierre 
     */
    public void editarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
        
    }
    /**
     * 
     */
    public void configurarFranjaHoraria() {
        
    }
    /**
     * 
     * @param res 
     */
    public void addReserva(Reserva res) {
        this.listaReservas.add(res);
    }
    /**
     * 
     * @return 
     */
    public ArrayList<Reserva> getListaReservas() {
        return Reserva.getListaReservas();  
    }
    /**
     * 
     * @return 
     */
    public ArrayList<Reserva> getListaReservaActualizada() {
        return listaReservaActualizada;
    }
    /**
     * La lista de reservas se debe actualizar cuando lo solicite el Administrador (conectado a la persistencia)
     * @param listaReservaActualizada 
     */
    public void setListaReservaActualizada(ArrayList<Reserva> listaReservaActualizada) {
        this.listaReservaActualizada = listaReservaActualizada;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Reserva> getListaEventos() {
        return listaEventos;
    }
    
    /**
     * Metodo que agrega la reserva asociada a un evento
     * @param Res 
     */
    public void addReservaEvento(Reserva res){
        this.listaEventos.add(res);
    }
    
    public boolean crearEmpleado(String name, String tel, String email, String pass, String rol){
        switch(rol){
            case "Maitre":
                Maitre nuevoMaitre = new Maitre(name, tel, email, pass);
                return true;
            case "Mesero":
                Mesero nuevoMesero = new Mesero(name, tel, email, pass);
                return true;
            case "Recepcionista":
                Recepcionista nuevoRecep = new Recepcionista(name, tel, email, pass);
                return true;
            case "Administrador":
                Administrador nuevoAdmin = new Administrador(name, tel, email, pass);
            default:
                return false;
        }
    }
    
}
