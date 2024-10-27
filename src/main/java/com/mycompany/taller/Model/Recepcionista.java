package com.mycompany.taller.Model;
import java.time.*;
import java.util.*;
/**
 * Clase para representar al recepcionista y las funciones que puede realizar
 * @author Agustin y Juan
 */
public class Recepcionista extends Empleado {

    private String permisos = "Ver reservas del dia, Ver comentarios de reservas.";
    
    /**
     * Default Constructor
     */
    public Recepcionista() {
        super();
    }

    /**
     * Constructor parametrizado
     * @param name
     * @param tel
     * @param email
     * @param pass 
     */
    public Recepcionista(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Recepcionista", genero);
    }
    //Corregido: saque el parametro String rol, ya que esta escrito "Recepcionista" no lo necesita en el constructor
    public Recepcionista(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero, long idUsuario) {
        super(name, tel, fechaCumple, email, pass, "Recepcionista", genero, idUsuario);
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    
    /**
     * Metodo para buscar listas de reservas por una fecha y hora especifica
     * @param fecha
     * @param hora
     * @return Array<Reserva>
     */
    public ArrayList<Reserva> buscarReservas(LocalDate fecha, LocalTime hora) {
        // Ordena las reservas por fecha
        Reserva.ordenarFechaReservas();
        
        // Filtrar las reservas para la fecha y hora especifica
        ArrayList<Reserva> reservasFecha = new ArrayList<>();
        for(Reserva reserva : Reserva.getListaReservas()) {
            if (reserva.getDia().equals(fecha)) {
                reservasFecha.add(reserva);
            }
        }
        
        // Ordena por hora las reservas filtradas
        reservasFecha = Reserva.ordenarPorHora(reservasFecha);
        
        // Filtra las reservas de esa fecha por la hora especifica
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();
        for (Reserva reserva : reservasFecha) {
            if (reserva.getHora().equals(hora)) {
                reservasEncontradas.add(reserva);
            }
        }
        return reservasEncontradas;

    }
    /**
     * Metodo para tomar los datos de una fecha de la reserva
     * @param fecha
     * @param hora 
     */
    public void tomarDatosFecha(LocalDate fecha, LocalTime hora) {
        System.out.println("Fecha y hora recibidas: " + fecha + "" + hora);
    }
    /**
     * Metodo para buscar las reservas por ID
     * @param idReserva
     * @return Reserva
     */
    private Reserva buscarId(String idReserva) {
        for(Reserva reserva : this.getReservas()) {
            if (reserva.getIdReserva().equals(idReserva)) {
                return reserva;
            }
        }
        return null;
    }
    /**
     * Metodo para tomar el ID de una reserva
     * @param idReserva
     * @return Reserva
     */
    public Reserva tomarID(String idReserva) {
        return buscarId(idReserva);
    }
    /**
     * Metodo para cambiar el estado de la reserva según el ID
     * @param id
     * @param nuevoEstado 
     */
    public void cambiarEstadoReserva(String id, String nuevoEstado) {
        Reserva reserva = buscarId(id);
        if (reserva != null) {
            ArrayList<String> estadosPosibles = Reserva.getListaEstados();
            boolean estadoValido = false;
            for(String estado : estadosPosibles) {
                if(estado.equals(nuevoEstado)) {
                    estadoValido = true;
                    break;
                }
            }
            if (estadoValido) {
                reserva.setEstado(nuevoEstado);
                System.out.println("Estado cambiado a: " + nuevoEstado);
            } else {
                System.out.println("Estado no cambiado a: " + nuevoEstado);
            }
        } else {
            System.out.println("Reserva no encontrada");
        }
    }
    /**
     * Metodo para confirmar la asistencia por ID
     * @param id 
     */
    public void confirmarAsistencia(String id) {
        Reserva reserva = buscarId(id);
        if (reserva != null) {
            String estadoActual = reserva.getEstadoAsist();
            if (estadoActual.equals("Completado") || estadoActual.equals("Cancelado")) {
                System.out.println("No se puede confirmar asistencia porque la reserva ya esta completada o fue cancelada");
            } else if (estadoActual.equals("Pendiente")){
                reserva.setEstado("Completado");
                System.out.println("Asistencia confirmada. El estado es ahora: Completado");
            } else {
                System.out.println("No se puede confirmar asistencia en el estado: " + estadoActual);
            }
        } else {
            System.out.println("Reserva no encontrada");
        }
    }
    /**
     * Metodo privado para buscar el nombre del cliente en una lista de reservas
     * @param nombre
     * @param listRes
     * @return Reserva
     */
    private Reserva buscarNom(String nombre, ArrayList<Reserva> listRes) {
        for(Reserva reserva : listRes) {
            if (reserva.getClienteReserva().getNombre().toLowerCase().equalsIgnoreCase(nombre.toLowerCase())) {
                return reserva;
            }
        }
        return null;
    }
    /**
     * Metodo para tomar un nombre del cliente en una lista de reservas
     * @param nombre
     * @param listRes
     * @return Reserva
     */
    public Reserva tomarNombre(String nombre, ArrayList<Reserva> listRes) {
        return buscarNom(nombre, listRes);
    }
    /**
     * Metodo para ver los comentarios de las reservas con respecto al ID
     * @param idReserva
     * @return String
    */
    @Override
    public String verComentarios(String idReserva) {
        for(Reserva reserva : getReservas()) {
            if (reserva.getIdReserva().equals(idReserva)) {
                return "Comentarios de la reserva " + idReserva + ": " + reserva.getComentarios();
            }
        }
        return "Reserva con ID " + idReserva + " no encontrada.";
    }

}
