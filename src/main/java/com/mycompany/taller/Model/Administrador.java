package com.mycompany.taller.Model;

import com.mycompany.taller.Model.Reserva;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Administrador extends Empleado{

    private ArrayList<Reserva> listaEventos = new ArrayList<>();

    public Administrador(ArrayList<Reserva> reservas, String name, String tel, String email, String pass) {
        super(reservas, name, tel, email, pass, "Administrador");
        
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

    public void configurarFranjaHoraria(LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime horaInicio, LocalDateTime horaFin) {
     
    }

    public void addReserva(Reserva res) {
     
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
