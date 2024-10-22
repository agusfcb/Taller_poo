package com.mycompany.taller.Model;
import com.mycompany.taller.Model.Reserva;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 */
public class Cliente extends Usuario {
    
    private ArrayList<Reserva> agendaReservas;
    private String premisos = "Realizar reservas individuales, ver historial de reservas propias, cancelar reservas, editar datos";
    
    private static final ArrayList<String> opcionesCambios = new ArrayList<>(Arrays.asList("Nombre","Telefono","Correo","Contrasenia","Genero"));
    
    /**
     * Default constructor
     */
    public Cliente() {
        super();
    }
    /*
     * Constructor parametrizado
     */
    public Cliente(String name, String tel, String email, String pass){
        super(name, tel, email, pass, "Cliente");
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
    
    
    /** Metodo para crear la reserva
     * @param String fecha Fecha de la reserva
     * @param String hora Hora de la reserva
     * @param String coment Comentarios de la reserva
     * @param String cantidad Cantidad de comensales
     * @param Mesa mesa Mesa de la reserva
     * @return void
     */
    public void crearReserva(LocalDate fecha, LocalTime hora, String coment, Integer cantidad,  Mesa mesa, TarjetaCredito tarjeta) {
        Reserva nuevaReserva = new Reserva(fecha, hora, coment, cantidad, mesa, this, tarjeta);
    }
    
    /**
     * Metodo para cancelar una reserva determinada
     * @param idReserva 
     * @return boolean
     */
    public boolean cancelarReserva(String idReserva) {
        try {for (Reserva ext : this.agendaReservas){
            if (ext.getIdReserva().equals(idReserva)) {
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
     * @param String idRes es la id de la reserva
     * @param String fecha fecha de la nueva reserva se usa para crear una nueva reserva
     * @param String hora de la nueva reserva se usa para crear una nueva reserva
     * @param String capacidad se usa para crear una nueva reserva
     * @return boolean
     */
    public boolean modificarReserva(Reserva res, String option, ArrayList<Object> argument) {
        switch (option){
            case "A":
                //Modificar cantidad de comensales
                res.setCantidadComensales(argument.indexOf(0));
                return true;
            case "B":
                LocalDate fechaNueva = argument.indexOf(0);
                LocalTime horaNueva = argument.indexOf(1);
                res.setDia(fechaNueva);
                res.setHora(horaNueva);
                return true;
            case "C":
                Mesa mesaNueva = argument.indexOf(0);
                res.setMesaReservada(mesaNueva);
                //Modificar la mesa tiene que buscar mesas disponibles en la misma fecha y hora
                return true;
            default:
                return false;
        }
    }

    /** 
     * Metodo para actualizar los datos del cliente
     * @param String option
     * @param String argumento
     * @return
     */
    public boolean actualizarInformacion(String option, String argumento) {
        switch (String.valueOf(option)) {
            case "Nombre":
                this.cambiarNombre(argumento);
                break;
            case "Telefono":
                this.cambiarCorreo(argumento);
                break;
            case "Correo":
                this.cambiarTelefono(argumento);
                break;
            case "Contrasenia":
                //Falta un metodo para validar la contrasenia
                this.cambiarContrasenia(argumento);
                break;
            case "Genero":
                this.cambiarGenero(argumento);
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