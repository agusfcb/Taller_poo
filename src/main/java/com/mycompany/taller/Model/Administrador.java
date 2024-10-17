/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Administrador extends Empleado{

    private ArrayList<Reserva> listaReservaActualizada = new ArrayList<>();
    private ArrayList<Reserva> listaEventos = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();

    public Administrador(String name, String tel, String email, String pass) {
        super(name, tel, email, pass, "Administrador");
        
    }

    /*
     * 
     * @param idUsuario
     * @param nuevoRol
     * @return 
     */
    public boolean cambiarRol(String idUsuario, String nuevoRol) {
        return true;
    }
    /**
     * 
     * @param idUsuario
     * @param nuevoRol
     * @return 
     */
    public boolean editarRol(String idUsuario, String nuevoRol) {
        return true;
    }

    /**
     * 
     * @param fechaInicio
     * @param fechaHoraApertura 
     */
    public void actualizarApertura(LocalDateTime fechaInicio, LocalDateTime fechaHoraApertura) {
       
    }

    /**
     * 
     * @param fechaInicio
     * @param fechaHoraCierre 
     */
    public void actualizarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
        
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
}

