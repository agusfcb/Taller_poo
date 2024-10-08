package com.mycompany.taller.Model;
import java.util.*;
import java.time.LocalDate;

/**
 * 
 */
public class Cliente extends Usuario {

    private ArrayList<Reserva> agendaReservas = new ArrayList<>();
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
        super(name, tel, email, pass);
    }
    
    /** Metodo para encontrar mesas disponibles
     * @param String fecha1
     * @param String hora1
     * @param String capacidad
     * @return ArrayList<Mesa>
     */
    public ArrayList<Mesa> verMesaDisponible(String fecha1, String hora1, String capacidad){
        //aca debe existir un control de formato de fecha, hora y capacidad
        
        ArrayList<Mesa> coincidenciaBusqueda = this.buscarMesaDisponible(fecha1, hora1, capacidad);
        return coincidenciaBusqueda;
    }
    
    
    /** Metodo privado para encontrar mesas disponibles
     * @param String fecha1
     * @param String hora1
     * @param String capacidad
     * @return ArrayList<Mesa>
     */
    private ArrayList<Mesa> buscarMesaDisponible(String fecha1, String hora1, String capacidad) {
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas();
        ArrayList<Reserva> reservaDia = new ArrayList<>();
        ArrayList<Mesa> mesasTotales = Mesa.getMesasTot();
        ArrayList<Mesa> coincidenciaBusqueda = new ArrayList<>();
        
        reservaDia = this.filtroDia(listaReservas, fecha1);
        coincidenciaBusqueda = this.filtroHora(mesasTotales, reservaDia, hora1, capacidad);
        
        return coincidenciaBusqueda;
    }

    /**
     * Metodo para ver filtrar por dia las reservas
     * @param ArrayList<Reserva> listaReservas
     * @param String fecha
     * @return ArrayList<Reserva>
     */
    private ArrayList<Reserva> filtroDia(ArrayList<Reserva> listaReservas, String fecha) {
        ArrayList<Reserva> filtroFecha = new ArrayList<>();
        boolean vacioCompleto = true;
        
        for (Reserva extraer : listaReservas) {
            if (extraer.getDia().equals(fecha)) {
                vacioCompleto = false;
                filtroFecha.add(extraer);
            }
        }
        if (vacioCompleto){
            return null;
        } else {
        return filtroFecha;
        }
    }
    
    /**
     * Metodo para filtrar mesas disponibles por hora y capadidad de la mesa
     * @param ArrayList<Mesa> mesasTotales
     * @param ArrayList<Reserva> listaAux
     * @param String hora
     * @param String capacidad
     * @return ArrayList<Mesa>
     */
    private ArrayList<Mesa> filtroHora(ArrayList<Mesa> mesasTotales, ArrayList<Reserva> listaAux, String hora, String capacidad) {
        ArrayList<Mesa> mesasCapacidad = new ArrayList<>();
        for (Mesa extraerM : mesasTotales) {
            if (extraerM.getCapacidad().equals(capacidad)){
                mesasCapacidad.add(extraerM);
            }
        }
        
        for (Reserva extraer2 : listaAux) {
            if (extraer2.getHora().equals(hora)){
                if (extraer2.getMesaReservada().getCapacidad().equals(capacidad)){
                    mesasCapacidad.remove(extraer2.getMesaReservada());
                }
            }
        }
        return mesasCapacidad;
    }

    /** Metodo para crear la reserva
     * @param String fecha 
     * @param String hora 
     * @param Mesa mesa
     * @return void
     */
    public void crearReserva(String fecha, String hora, Mesa mesa) {
        Reserva nuevaReserva = new Reserva(fecha, hora, mesa, this);
        agendaReservas.add(nuevaReserva);
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
    public boolean modificarReserva(String idRes, String fecha, String hora, String capacidad) {
        boolean confirmacion = this.cancelarReserva(idRes);
        ArrayList<Mesa> disponible = this.buscarMesaDisponible(fecha, hora, capacidad);
        return confirmacion;
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
    
    /**
     * @return ArrayList<Reservas>
     */
    public ArrayList<ArrayList<String>> verHistorial() {
        ArrayList<String> datosReserva = new ArrayList<>();
        ArrayList<ArrayList<String>> listadoImprimir = new ArrayList<>();
        
        ArrayList<Reserva> listaReserva = this.agendaReservas;
        
        for (Reserva ext : listaReserva) {
            String name = ext.getClienteReserva().getNombre();
            String email = ext.getClienteReserva().getCorreo();
            String tel = ext.getClienteReserva().getTelefono();
            String gen = ext.getClienteReserva().getGenero();
            datosReserva.add(name);
            datosReserva.add(email);
            datosReserva.add(tel);
            datosReserva.add(gen);
            listadoImprimir.add(datosReserva);
        }
        return listadoImprimir;
    }
    
    @Override
    public boolean validarUsuario(String usuario, String contrasenia){
        return false;
    }

    @Override
    boolean iniciarSesion(String correo, String contrasenia) {
        return false;
    }
    
}