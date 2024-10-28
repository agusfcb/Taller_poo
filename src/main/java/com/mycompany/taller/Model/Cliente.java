package com.mycompany.taller.Model;
import com.mycompany.taller.Model.Reserva;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase para representar al cliente y las funciones que puede realizar
 * @author Agustin y Juan
 */

public class Cliente extends Usuario {
    
    //El atributo auxiliar permite respetar un mapeo entre objetos mediante conversiones
    private ArrayList<String> reservasIds;
    private transient ArrayList<Reserva> agendaReservas;
    private String premisos = "Realizar reservas individuales, ver historial de reservas propias, cancelar reservas, editar datos";
    
    private static final ArrayList<String> opcionesCambios = new ArrayList<>(Arrays.asList("Nombre","Telefono","Correo","Contrasenia","Genero"));
    
    /**
     * Default constructor
     */
    public Cliente() {
        super();
        this.agendaReservas = new ArrayList<Reserva>();
        this.setReservasIds(conversorStringIds(this.getAgendaReservas()));
    }
    /*
     * Constructor parametrizado
     */
    public Cliente(String name, String tel, String fechaCumple, String email, String pass, String genero){
        super(name, tel, fechaCumple, email, pass, "Cliente", genero);
        this.agendaReservas = new ArrayList<Reserva>();
        this.setReservasIds(conversorStringIds(this.getAgendaReservas()));
    }
    
    /*
     * Constructor para la persistencia
     */
    public Cliente(String name, String tel, String fechaCumple, String email, String pass, String genero, String idUser){
        super(name, tel, fechaCumple, email, pass, "Cliente", genero, idUser);
        this.agendaReservas = new ArrayList<Reserva>();
        this.setReservasIds(conversorStringIds(this.getAgendaReservas()));
    }
    
    
    public String getPremiso() {
        return premisos;
    }

    public void setPremiso(String premiso) {
        this.premisos = premiso;
    }

    public ArrayList<String> getReservasIds() {
        return reservasIds;
    }

    public void setReservasIds(ArrayList<String> reservasIds) {
        this.reservasIds = reservasIds;
    }

    public ArrayList<Reserva> getAgendaReservas() {
        return agendaReservas;
    }

    public void setAgendaReservas(ArrayList<Reserva> agendaReservas) {
        this.agendaReservas = agendaReservas;
        this.setReservasIds(conversorStringIds(this.getAgendaReservas()));
    }

    public String getPremisos() {
        return premisos;
    }

    public void setPremisos(String premisos) {
        this.premisos = premisos;
    }
    
    
    /**
     * Metodo para agregar una reserva al historial del cliente
     * @param res Reserva creada recientemente
     */
    public void addReserva(Reserva res){
        this.agendaReservas.add(res);
        this.addReservaId(res);
    }
    
    public void addReservaId(Reserva reserv){
        this.reservasIds.add(reserv.getIdReserva());
    
    
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
    public void crearReserva(String fecha, String hora, String coment, Integer cantidad, Mesa mesa, TarjetaCredito tarjeta) {
        Reserva nuevaReserva = new Reserva(fecha, hora, coment, cantidad, mesa, this, tarjeta);
    }
    
    /**
     * Metodo para cancelar una reserva determinada
     * @param idReserva 
     * @return boolean
     */
    public boolean cancelarReserva(String idReserva) {
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
                break;
            case "Telefono":
                this.setCorreo(argumento);
                break;
            case "Correo":
                this.setTelefono(argumento);
                break;
            case "Contrasenia":
                //Falta un metodo para validar la contrasenia
                this.setContrasenia(argumento);
                break;
            case "Genero":
                this.setGenero(argumento);
                break;
            default:
                break;
        }
        return true;
    }
    
    /** Devuelve el historial de reservas del cliente
     * @return ArrayList<Reservas>
     */
    public ArrayList<ArrayList<String>> verHistorial() {
        ArrayList<String> datosReserva = new ArrayList<>();
        ArrayList<ArrayList<String>> listadoImprimir = new ArrayList<>();
        
        ArrayList<Reserva> listaReserva = this.agendaReservas;
                
        for (Reserva ext : listaReserva) {
            String idRes = ext.getIdReserva().toString();
            String diaR = ext.getDia().toString();
            String horaR = ext.getHora().toString();
            String mesaR = ext.getMesaReservada().getIdMesa();
            
            datosReserva.add(idRes);
            datosReserva.add(diaR);
            datosReserva.add(horaR);
            datosReserva.add(mesaR);
            listadoImprimir.add(datosReserva);
        }
        return listadoImprimir;
    }
 
    public ArrayList<Reserva> conversorReservasId(ArrayList<String> listaIds){
        ArrayList<Reserva> listaRes = new ArrayList<>();
        for (String extId : this.getReservasIds()){
            for(Reserva extRes : Reserva.getListaReservas()){
                if(extId.equals(extRes.getIdReserva())){
                    listaRes.add(extRes);
                }          
            }
        }
        return listaRes;
    }
            
            
    public ArrayList<String> conversorStringIds(ArrayList<Reserva> reservas){
        ArrayList<String> listaString = new ArrayList<>();
        for (Reserva extRes : this.getAgendaReservas()){
            listaString.add(extRes.getIdReserva());
        }
        return listaString;
    }
}
