package com.mycompany.taller.Model;
import com.mycompany.taller.Model.Reserva;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Clase para representar al cliente y las funciones que puede realizar
 * @author Agustin y Juan
 */
public class Cliente extends Usuario {
    
    private ArrayList<Reserva> agendaReservas;
    private String premisos = "Realizar reservas individuales, ver historial de reservas propias, cancelar reservas, editar datos";
    
    private static final ArrayList<String> opcionesCambios = new ArrayList<>(Arrays.asList("Nombre","Telefono","Correo","Contrasenia","Genero"));
    

    public Cliente() {
        super();
    }

    public Cliente(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero){
        super(name, tel, fechaCumple, email, pass, "Cliente", genero);
        this.agendaReservas = new ArrayList<Reserva>();
    }
    
    
    public Cliente(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero, long idUser){
        super(name, tel, fechaCumple, email, pass, "Cliente", genero, idUser);
        this.agendaReservas = new ArrayList<Reserva>();
    }
    
    
    public String getPremiso() {
        return premisos;
    }

    public void setPremiso(String premiso) {
        this.premisos = premiso;
    }
    
    /**
     * Metodo para agregar una reserva al historial del cliente
     * @param res Reserva creada recientemente
     */
    public void addReserva(Reserva res){
        this.agendaReservas.add(res);
    }
    
    /** 
     * Metodo para crear la reserva
     * @param fecha fecha Fecha de la reserva
     * @param hora hora Hora de la reserva
     * @param coment Comentarios de la reserva
     * @param cantidad Cantidad de comensales
     * @param mesa es la mesa de la reserva
     * @param tarjeta tarjeta de credito
     */
    public void crearReserva(LocalDate fecha, LocalTime hora, String coment, Integer cantidad, Mesa mesa, TarjetaCredito tarjeta) {
        Reserva nuevaReserva = new Reserva(fecha, hora, coment, cantidad, mesa, this, tarjeta);
    }
    
    /**
     * Metodo para cancelar una reserva determinada
     * @param idReserva id de la reserva
     * @return true si se cancelo con exito o false si no se cancelo la reserva
     */
    public boolean cancelarReserva(long idReserva) {
        try {for (Reserva ext : this.agendaReservas){
            if (String.valueOf(ext.getIdReserva()).equals(String.valueOf(idReserva))) {
                ext.setEstado("Cancelado");
                ext.getMesaReservada().removerReserva(ext);
                ext.setMesaReservada(null);
                return true;
            }
        }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Metodo para modificar una reserva
     * @param res reserva a modificar
     * @param option opcion de modificacion
     * @param argument argumento o argumentos
     * @return true si se logra modificar, de lo contrario false
     */
    public boolean modificarReserva(Reserva res, String option, ArrayList<Object> argument) {
        switch (option){
            case "A":
                //Modificar cantidad de comensales, el limite es el limite de la misma mesa
                if (res.getMesaReservada().getCapacidad().equals(argument.get(0))){
                    res.setCantidadComensales((Integer) argument.get(0));
                } else if ((Integer)argument.get(0) < res.getMesaReservada().getCapacidad()){
                    res.setCantidadComensales((Integer) argument.get(0));
                }
                return true;
            case "B":
                //Modificador de comentarios
                String comentarioNuevo = argument.get(0).toString();
                res.setComentarios(comentarioNuevo);
                return true;
            case "C":
                //Modificar la mesa tiene que buscar mesas disponibles en la misma fecha y hora
                Mesa mesaNueva = (Mesa) argument.get(0);
                res.setMesaReservada(mesaNueva);
                return true;
            default:
                return false;
        }
    }

    /** 
     * Metodo para actualizar los datos del cliente
     * @param option opcion elegida para realizar el cambio
     * @param argumento argumento con el cual se realiza el cambio
     * @return true si se realizo con un cambio, en caso contrario false
     */
    public boolean actualizarInformacion(String option, String argumento) {
        switch (String.valueOf(option)) {
            case "Nombre":
                this.setNombre(argumento);
                return true;
            case "Telefono":
                this.setCorreo(argumento);
                return true;
            case "Correo":
                this.setTelefono(argumento);
                return true;
            case "Contrasenia":
                //Falta un metodo para validar la contrasenia
                this.setContrasenia(argumento);
                return true;
            case "Genero":
                this.setGenero(argumento);
                return true;
            default:
                return false;
        }
    }
    
    /** Devuelve el historial de reservas del cliente
     * @return array de arrays con la informacion de cada reserva del cliente lista para presentar
     */
    public ArrayList<ArrayList<String>> verHistorial() {
        ArrayList<String> datosReserva = new ArrayList<>();
        ArrayList<ArrayList<String>> listadoImprimir = new ArrayList<>();
        
        ArrayList<Reserva> listaReserva = this.agendaReservas;
                
        for (Reserva ext : listaReserva) {
            String idRes = ext.getIdReserva().toString();
            String diaR = ext.getDia().toString();
            String horaR = ext.getHora().toString();
            String mesaR = ext.getMesaReservada().getNumero();
            
            datosReserva.add(idRes);
            datosReserva.add(diaR);
            datosReserva.add(horaR);
            datosReserva.add(mesaR);
            listadoImprimir.add(datosReserva);
        }
        return listadoImprimir;
    }
    
}