package com.mycompany.taller.Model;
import java.util.*;
import java.time.LocalDateTime;

/**
 * 
 */
public class Recepcionista extends Empleado {
//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
    /**
     * Default Constructor
     */
    public Recepcionista() {
        super();
    }
    /**
     * Constructor parametrizado
     */
    /**
     * 
     * @param reservas
     * @param rol
     * @param permiso
     * @param name
     * @param tel
     * @param email
     * @param pass 
     */
    public Recepcionista(ArrayList<Reserva> reservas, String permiso, String name, String tel, String email, String pass) {
        super(reservas, "Recepcionista", permiso, name, tel, email, pass);
    }
    //Corregido: saque el parametro String rol, ya que esta escrito "Recepcionista" no lo necesita en el constructor
    
    /**
     * Metodo para buscar listas de reservas por una fecha y hora especifica
     * @param fecha
     * @param hora
     * @return Array<Reserva>
     */
    public ArrayList<Reserva> buscarReservas(String fecha, String hora) {
        ArrayList<Reserva> reservasEncontradas = new ArrayList<>();
        for(Reserva reserva : this.getReservas()) {
            if (reserva.getDia().equals(fecha) && reserva.getHora().equals(hora)) {
                reservasEncontradas.add(reserva);
            }
        }
        return reservasEncontradas;
    }
    
    public void tomarDatosFecha(String fecha, String hora) {
        System.out.println("Fecha y hora recibidas: " + fecha + "" + hora);
    }
    /**
     * Metodo para buscar las reservas por ID
     * @param idReserva
     * @return Reserva
     */
    public Reserva buscarId(String idReserva) {
        for(Reserva reserva : this.getReservas()) {
            if (reserva.getIdReserva().equals(idReserva)) {
                return reserva;
            }
        }
        return null;
    }
    /**
     * 
     * @param idReserva
     * @return Reserva
     */
    public Reserva tomarID(String idReserva) {
        return buscarId(idReserva);
    }
    /**
     * 
     * @param id
     * @param nuevoEstado 
     */
    public void cambiarEstadoReserva(String id, String nuevoEstado) {
        Reserva reserva = buscarId(id);
        if (reserva != null) {
            String[] estadosPosibles = Reserva.getListaEstados();
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
     * 
     * @param id 
     */
    public void confirmarAsistencia(String id) {
        Reserva reserva = buscarId(id);
        if (reserva != null) {
            reserva.setEstado("Completado");
        } else {
            System.out.println("Reserva no encontrada");
        }
    }
    /**
     * 
     * @param nombre
     * @param listRes
     * @return Reserva
     */
    public Reserva buscarNom(String nombre, ArrayList<Reserva> listRes) {
        for(Reserva reserva : listRes) {
            if (reserva.getClienteReserva().getNombre().equalsIgnoreCase(nombre)) {
                return reserva;
            }
        }
        return null;
    }
    /**
     * 
     * @param nombre
     * @param listRes
     * @return Reserva
     */
    public Reserva tomarNombre(String nombre, ArrayList<Reserva> listRes) {
        return buscarNom(nombre, listRes);
    }
}