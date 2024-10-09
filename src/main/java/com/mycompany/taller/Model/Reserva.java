package com.mycompany.taller.Model;


import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 
 */
public class Reserva {
    
    private String dia;
    private String hora;
    private String estadoAsist;
    private String cantidadComensales;
    private String comentarios;
    private Mesa mesaReservada;
    private Cliente clienteReserva;
    private TarjetaCredito tarjeta;
    private String idReserva;
    private static ArrayList<Reserva> listaReservas = new ArrayList<>(); 
    
    private final String[] estadosPosibles = new String[] {"Pendiente", "Sin asistir", "Completado", "Cancelado"};
    private static final String[] horarios = new String[] {"11:00", "13:00", "15:00", "20:00", "22:00", "00:00"};


    /*
    *Constructor para el cliente que hace la reserva
    */
    public Reserva(String dia, String hora, String coment, Mesa mesa, Cliente cliente, TarjetaCredito tarjeta) {
        this.dia = dia;
        this.hora = hora;
        this.estadoAsist = estadosPosibles[0];
        this.comentarios = coment;
        this.mesaReservada = mesa;
        this.clienteReserva = cliente;
        this.tarjeta = tarjeta;
        this.idReserva = UUID.randomUUID().toString();
        Reserva.listaReservas.add(this);
        cliente.addReserva(this);
        mesa.agregarReserva(this);
    }
    
    /*
    * Por si se vuelve opcional agregar la cantidad de comensales
    */
    public Reserva(String dia, String hora, String coment, String cantComensales, Mesa mesa, Cliente cliente, TarjetaCredito tarjeta) {
        this.dia = dia;
        this.hora = hora;
        this.estadoAsist = estadosPosibles[0];
        this.comentarios = coment;
        this.cantidadComensales = cantComensales;
        this.mesaReservada = mesa;
        this.clienteReserva = cliente;
        this.tarjeta = tarjeta;
        this.idReserva = UUID.randomUUID().toString();
        Reserva.listaReservas.add(this);
        cliente.addReserva(this);
        mesa.agregarReserva(this);
    }
    
    /*
    *Constructor para el administrador que gestiona eventos
    */
    public Reserva(String dia, String hora, Mesa mesa) {
        this.clienteReserva = null;
        this.estadoAsist = this.estadosPosibles[0];
        this.dia = dia;
        this.hora = hora;
        this.mesaReservada = mesa;
        this.idReserva = UUID.randomUUID().toString();
        Reserva.listaReservas.add(this);
        mesa.agregarReserva(this);
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstadoAsist() {
        return estadoAsist;
    }

    public void setEstado(String estado) {
        this.estadoAsist = estado;
    }

    public String getCantidadComensales() {
        return cantidadComensales;
    }

    public void setCantidadComensales(String cantidadComensales) {
        this.cantidadComensales = cantidadComensales;
    }
    
    public Mesa getMesaReservada() {
        return mesaReservada;
    }

    public void setMesaReservada(Mesa mesaReservada) {
        this.mesaReservada = mesaReservada;
    }

    public Cliente getClienteReserva() {
        return clienteReserva;
    }

    public void setClienteReserva(Cliente clienteReserva) {
        this.clienteReserva = clienteReserva;
    }

    public TarjetaCredito getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaCredito tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
    
    public static ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }
    
    /**
    * Metodo de uso administrativo para ver todas las reservas, posiblemente deba colocarlo como metodo de Administrador
    * @param
    * @return ArrayList<ArrayList<String>>
    * 
    */
    
    public ArrayList<ArrayList<String>> historialReservas() {
        ArrayList<Reserva> reservas = Reserva.getListaReservas();
        ArrayList<String> datosReserva = new ArrayList<>();
        ArrayList<ArrayList<String>> listadoImprimir = new ArrayList<>();
        
        // FALTA UN METODO QUE ORDENE TODAS LAS RESERVAS, LA LISTA RESERVAS DEBE ESTAR ORDENADA
        for (Reserva ext : reservas) {
            String name = ext.getClienteReserva().getNombre();
            String idReserv = ext.getIdReserva();
            String fechaR = ext.getDia();
            String horaR = ext.getHora();
            String estado = ext.getEstadoAsist();
            String cantComen = ext.getCantidadComensales();
            datosReserva.add(name);
            datosReserva.add(idReserv);
            datosReserva.add(fechaR);
            datosReserva.add(horaR);
            datosReserva.add(estado);
            datosReserva.add(cantComen);
            listadoImprimir.add(datosReserva);
        }
        return listadoImprimir;
    }
    
    @Override
    public String toString() {
        return "RESERVA: " + idReserva + "\nCliente: " + clienteReserva + "\nDia: " + dia + "\nHora: " + hora + "\nMesa reservada: " + mesaReservada + "\nCantidadComensales: " + cantidadComensales + "\nEstado de asistencia: " + estadoAsist + "\nComentarios: " + comentarios;
    }
// PENDIENTE ORDENAR LA LISTA DE RESERVAS
//    public ArrayList<ArrayList<String>> verHistorial(){}
// PENDIENTE desglozar reservas asistidas, pendientes y canceladas por fecha


}