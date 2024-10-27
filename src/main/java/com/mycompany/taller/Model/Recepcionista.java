package com.mycompany.taller.Model;
import java.time.*;
import java.util.*;

/**
 * 
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

    public Recepcionista(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Recepcionista", genero);
    }

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
     * Metodo para buscar las reservas por una fecha y hora especifica
     * @param fecha fecha de las reservas
     * @param hora hora de las reservas
     * @return array de las reservas de una fecha y hora
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
     * @param fecha fecha
     * @param hora hora
     */
    public void tomarDatosFecha(LocalDate fecha, LocalTime hora) {
        System.out.println("Fecha y hora recibidas: " + fecha + "" + hora);
    }
    /**
     * Metodo para buscar las reservas por ID
     * @param idReserva id de la reserva a buscar
     * @return objeto del tipo Reserva
     */
    private Reserva buscarId(long idReserva) {
        for(Reserva reserva : this.getReservas()) {
            if (String.valueOf(reserva.getIdReserva()).equals(String.valueOf(idReserva))) {
                return reserva;
            }
        }
        return null;
    }
    
    /**
     * Metodo para tomar el ID de una reserva
     * @param idReserva id de la reserva
     * @return objeto del tipo Reserva
     */
    public Reserva tomarID(long idReserva) {
        return buscarId(idReserva);
    }
    
    /**
     * Metodo para cambiar el estado de la reserva según el ID
     * @param id id de la reserva
     * @param nuevoEstado nuevo estado de la reserva
     */
    public void cambiarEstadoReserva(long id, String nuevoEstado) {
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
     * @param id id de la reserva
     */
    public void confirmarAsistencia(long id) {
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
     * @param nombre nombre del cliente11
     * @param listRes array de reservas filtrada por dia y hora para realizar la busqueda
     * @return objeto de tipo Reserva que se encontro asociado al cliente
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
     * @param nombre nombre del cliente
     * @param listRes array filtrado por dia y hora
     * @return objeto del tipo Reserva asociado al cliente
     */
    public Reserva tomarNombre(String nombre, ArrayList<Reserva> listRes) {
        return buscarNom(nombre, listRes);
    }
    /**
     * Metodo para ver los comentarios de las reservas con respecto al ID
     * @param idReserva id de la reserva
     * @return String del comentario de la reserva
    */
    @Override
    public String verComentarios(long idReserva) {
        for(Reserva reserva : getReservas()) {
            if (reserva.getIdReserva().equals(idReserva)) {
                return "Comentarios de la reserva " + idReserva + ": " + reserva.getComentarios();
            }
        }
        return "Reserva con ID " + idReserva + " no encontrada.";
    }

}
