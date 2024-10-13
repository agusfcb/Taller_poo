package com.mycompany.taller.Model;


import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 
 */
public class Reserva {
    
    
    private LocalDate dia;
    private LocalTime hora;
    private String estadoAsist;
    private String cantidadComensales;
    private String comentarios;
    private String multaAusencia;
    private Mesa mesaReservada;
    private Usuario clienteReserva;
    private TarjetaCredito tarjeta;
    private String idReserva;
    private static ArrayList<Reserva> listaReservas = new ArrayList<>(); 
    
    public static final String[] estadosPosibles = new String[] {"Pendiente", "Sin asistir", "Completado", "Cancelado"};
    private static String[] horarios = new String[] {"11:00", "13:00", "15:00", "20:00", "22:00", "00:00"};
    private static String[] horariosAdmin = new String[] {"09:00", "11:00", "13:00", "15:00", "20:00", "22:00", "00:00", "02:00"};
    
    /*
    * Constructor para el cliente que hace la reserva
    */
    public Reserva(String dia, String horaSt, String coment, Mesa mesa, Cliente cliente, TarjetaCredito tarjeta) {
        // Formato Año-Mes-Dia
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //Paseo a LocalDate
        LocalDate fechaRes = LocalDate.parse(dia, formatterDate);
        

        // Formato de la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        // Parsear el String a LocalTime
        LocalTime hora = LocalTime.parse(horaSt, formatter);
        
        this.dia = fechaRes;
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
        tarjeta.agregarReser(this);
    }
    
    /*
    *Constructor para el administrador que gestiona eventos
    */
    public Reserva(LocalDate dia, LocalTime hora, Mesa mesa, Usuario admin) {
        this.clienteReserva = admin;
        this.estadoAsist = this.estadosPosibles[0];
        this.dia = dia;
        this.hora = hora;
        this.mesaReservada = mesa;
        this.clienteReserva = admin;
        this.idReserva = UUID.randomUUID().toString();
        Reserva.listaReservas.add(this);
        mesa.agregarReserva(this);
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(String dia) {
        // Formato Año-Mes-Dia
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //Paseo a LocalDate
        LocalDate diaNuevo = LocalDate.parse(dia, formatterDate);
        this.dia = diaNuevo;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(String hora) {
        // Formato de la hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        // Parsear el String a LocalTime
        LocalTime horaNueva = LocalTime.parse(hora, formatter);
        this.hora = horaNueva;
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

    public Usuario getClienteReserva() {
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
    
    public static String[] getListaEstados(){
        return estadosPosibles;
    }
    
    public void agregarMulta(){
        this.multaAusencia = "u$d 50";
    }
    public static ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }
    
    
    /* Metodo de ordenamiento por fecha. Este metodo debe inicirse cada dia de forma automatica
    *
    */
    public static void ordenarFechaReservas(){
        ArrayList<Reserva> listaOrdenar = Reserva.getListaReservas();
        ArrayList<Reserva> listaAux;
        LocalDate primerRes;
        LocalDate comparaRes;
        Reserva aux1;
        Reserva aux2;
        int pivote = (listaOrdenar.size()/2);
        boolean ordenado = false;

        while (pivote > 0) {
            ordenado = true;
            for (int i = 0; i+pivote < listaOrdenar.size(); i++) {
                aux1 = listaOrdenar.get(i);
                aux2 = listaOrdenar.get(i+pivote);
            
                primerRes = listaOrdenar.get(i).getDia();
                comparaRes = listaOrdenar.get(i+pivote).getDia();
            
                //Parseo a LocalDate (se puede optimizar, esto es a fin de comprension de pasos
            
                if (primerRes.isAfter(comparaRes)){
                    listaOrdenar.set(i, aux2);
                    listaOrdenar.set(i+pivote, aux1);
                    ordenado = false;
                }
            }
            pivote = pivote / 2;
            if(ordenado && (pivote == 0)){
                break;
            }
        }
    }
    
    
    /**
    * Metodo de uso administrativo para ver todas las reservas, posiblemente deba colocarlo como metodo de Administrador
    * @param
    * @return ArrayList<ArrayList<String>>
    * 
    */
    public ArrayList<String> historialReservas(String fechaDesde, String fechaHasta) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fechaDesde, formatter);
        LocalDate fechaFin = LocalDate.parse(fechaHasta, formatter);
        
        Reserva.ordenarFechaReservas();
        ArrayList<Reserva> reservas = Reserva.getListaReservas();
        
        ArrayList<String> listadoImprimir = new ArrayList<>();
        
        for (Reserva ext : reservas) {
            if (!ext.getDia().isBefore(fechaInicio)) {
                if (!ext.getDia().isAfter(fechaFin)) { 
                    listadoImprimir.add(ext.toString());
                }
            }
        }
        return listadoImprimir;
    }
    
    @Override
    public String toString() {
        return "RESERVA: " + idReserva + "\nCliente: " + clienteReserva + "\nDia: " + dia + "\nHora: " + hora + "\nMesa reservada: " + mesaReservada + "\nCantidadComensales: " + cantidadComensales + "\nEstado de asistencia: " + estadoAsist + "\nComentarios: " + comentarios;
    }
    
    
    /**
     * Este es un metodo que se colocara directamente luego del loggin de forma que tome la fecha cada dia
     * Al iniciar este metodo buscara todas las reservas del dia anterior que no tuvieron asistencia y les asignara una multa
     * @param ArrayList<Reserva> la lista de todas las reservas del dia anterior al dia actual
     * @return void
     */
    public static void multa(ArrayList<Reserva> listaFiltradaFecha){
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaReserva;
        String sinAsist = Reserva.getListaEstados()[1];
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Reserva extReser : listaFiltradaFecha){
            fechaReserva = extReser.getDia();
            if(fechaReserva.isAfter(fechaActual)){
                if(extReser.getEstadoAsist().equals(sinAsist)){
                    extReser.agregarMulta();
                }  
            }
        }
        
    }
    
}