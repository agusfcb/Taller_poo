/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 *
 * @author juanm
 */
public class Administrador extends Empleado {

    private ArrayList<Reserva> listaEventos = new ArrayList<>();

    private String idUsuario;
    private String rol;
    private ArrayList<Reserva> listaReservas = new ArrayList<>();

    public Administrador(String name, String tel, String email, String pass) {
        super(name, tel, email, pass, "Administrador");
    }

    public boolean cambiarRol(String idUsuario, String nuevoRol) {
        return true;
    }

    public boolean editarRol(String idUsuario, String nuevoRol) {
        return true;
    }


    public void actualizarApertura(LocalDateTime fechaInicio, LocalDateTime fechaHoraApertura) {
       
    }

    public void actualizarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
        
    }

    public void editarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
      
    }

    public void configurarFranjaHoraria() {
     
    }

    public void addReserva(Reserva res) {
        this.listaReservas.add(res);
     

    }

    public ArrayList<Reserva> getListaReservas() {
        return Reserva.getListaReservas();  
    }
    
    /**
     * 
     * @param Res 
     */
    public void addReservaEvento(Reserva res){
        this.listaEventos.add(res);
    }
}
