package com.mycompany.taller.Model;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 */
public class Cliente extends Usuario {
    
    private ArrayList<Reserva> agendaReservas;
    
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
    
    /**
     * Metodo para agregar una reserva al historial del cliente
     * @param res Reserva creada recientemente
     */
    public void addReserva(Reserva res){
        this.agendaReservas.add(res);
    }
    
    
    /**
     * Metodo publico para ver las mesas disponiles
     * @param fecha1 Fecha de interes
     * @param hora1 Hora de interes
     * @param capacidad Capacidad de la mesa de interes
     * @return 
     */
    public ArrayList<Mesa> verMesaDisponible(LocalDate fecha1, LocalTime hora1, String capacidad){
        ArrayList<Mesa> coincidenciaBusqueda = this.buscarMesaDisponible(fecha1, hora1, capacidad);
        return coincidenciaBusqueda;
    }
    
    
    /** Metodo privado para encontrar mesas disponibles
     * @param String fecha1
     * @param String hora1
     * @param String capacidad
     * @return ArrayList<Mesa>
     */
    private ArrayList<Mesa> buscarMesaDisponible(LocalDate fecha1, LocalTime hora1, String capacidad) {
        // Control de que no se busque en una fecha anterior al mismo dia que se realiza la reserva
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isBefore(fecha1)){
            return null;
        }
        
        // Comienzo del algoritmo de busqueda
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas();
        ArrayList<Reserva> reservaDia = new ArrayList<>();
        ArrayList<Mesa> mesasTotales = Mesa.getMesasTot();
        ArrayList<Mesa> coincidenciaBusqueda = new ArrayList<>();
        
        reservaDia = this.filtroDia(listaReservas, fecha1);
        coincidenciaBusqueda = this.filtroHoraMesa(mesasTotales, reservaDia, hora1, capacidad);
        
        return coincidenciaBusqueda;
    }

    /**
     * Metodo para ver filtrar por dia las reservas
     * @param ArrayList<Reserva> listaReservas
     * @param String fecha
     * @return ArrayList<Reserva>
     */
    private ArrayList<Reserva> filtroDia(ArrayList<Reserva> listaReservas, LocalDate fecha) {
        
        ArrayList<Reserva> filtroFecha = new ArrayList<>();
        boolean vacio = true;
        
        for (Reserva extraer : listaReservas) {
            if (extraer.getDia().toString().equals(fecha.toString())) {
                vacio = false;
                filtroFecha.add(extraer);
            }
        }
        if (vacio){
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
    private ArrayList<Mesa> filtroHoraMesa(ArrayList<Mesa> mesasTotales, ArrayList<Reserva> listaAux, LocalTime hora, String capacidad) {
        ArrayList<Mesa> mesasCapacidad = new ArrayList<>();
        for (Mesa extraerM : mesasTotales) {
            if (extraerM.getCapacidad().equals(capacidad)){
                mesasCapacidad.add(extraerM);
            }
        }
        
        for (Reserva extraer2 : listaAux) {
            if (extraer2.getHora().toString().equals(hora.toString())){
                if (extraer2.getMesaReservada().getCapacidad().equals(capacidad)){
                    mesasCapacidad.remove(extraer2.getMesaReservada());
                }
            }
        }
        return mesasCapacidad;
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
    

    @Override
    public void validarUsuario(String usuario, String contrasenia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void iniciarSesion(String correo, String contrasenia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}