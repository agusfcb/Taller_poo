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
    private Mesa mesaReservada;
    private Usuario clienteReserva;
    private TarjetaCredito tarjeta;
    private String idReserva;
    private static ArrayList<Reserva> listaReservas = new ArrayList<>(); 
    private static final ArrayList<String> ubicacionesDisponibles = new ArrayList<>(Arrays.asList("Interior A", "Interior B", "Interior C", "Patio A", "Patio B"));
    public static final String[] estadosPosibles = new String[] {"Pendiente", "Sin asistir", "Completado", "Cancelado", "Evento"};
    
    // Formato de la hora
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    // Parsear el String a LocalTime
    private static ArrayList<LocalTime> horarios = new ArrayList<>(Arrays.asList(
        LocalTime.parse("11:00", formatter), 
        LocalTime.parse("13:00", formatter), 
        LocalTime.parse("15:00", formatter), 
        LocalTime.parse("20:00", formatter), 
        LocalTime.parse("22:00", formatter), 
        LocalTime.parse("00:00", formatter)));
    
   
    public static ArrayList<LocalTime> horariosEvento = new ArrayList<>(Arrays.asList(
        LocalTime.parse("09:00", formatter),
        LocalTime.parse("11:00", formatter), 
        LocalTime.parse("13:00", formatter), 
        LocalTime.parse("15:00", formatter), 
        LocalTime.parse("20:00", formatter), 
        LocalTime.parse("22:00", formatter), 
        LocalTime.parse("00:00", formatter),
        LocalTime.parse("02:00", formatter),
        LocalTime.parse("04:00", formatter)));
    
    /*
    * Constructor para el cliente que hace la reserva
    */
    public Reserva(LocalDate dia, LocalTime hora, String coment, String comensales ,Mesa mesa, Cliente cliente, TarjetaCredito tarjeta) {
        this.dia = dia;
        this.hora = hora;
        this.estadoAsist = estadosPosibles[0];
        this.comentarios = coment;
        this.cantidadComensales = comensales;
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
    public Reserva(LocalDate dia, LocalTime hora, Mesa mesa, Administrador admin) {
        this.clienteReserva = admin;
        this.estadoAsist = this.estadosPosibles[4];
        this.dia = dia;
        this.hora = hora;
        this.mesaReservada = mesa;
        this.cantidadComensales = "Indefinido";
        this.comentarios = "Evento";
        this.idReserva = UUID.randomUUID().toString();
        admin.addReservaEvento(this);
        Reserva.listaReservas.add(this);
        mesa.agregarReserva(this);
    }

    
    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate diaSet) {

        this.dia = diaSet;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime horaSet) {
        this.hora = horaSet;
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
    
    public static ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }
    
    public static ArrayList<LocalTime> getHorarios(){
        return Reserva.horarios;
    }
    
    /**
     * Metodo que agrega hora a la lista de horario ordinario
     * @param horaExtra hora a agregar
     */
    public void agregarHorario(LocalTime horaExtra){
        int index = 0;
        for (LocalTime compara : getHorarios()){
            if(horaExtra.toString().equals(compara.toString())){
                break;
            }
            if(horaExtra.isBefore(compara)){
                Reserva.horarios.add(index, horaExtra);
                break;
            }
            index +=1;
        }
    }
    
    /**
     * Metodo que quita horas a la lista de horario ordinario
     * @param horaExtra hora a remover 
     */
    public void quitarHorario(LocalTime horaExtra){
        int index = 0;
        for (LocalTime compara : getHorarios()){
            if(horaExtra.toString().equals(compara.toString())){
                Reserva.horarios.remove(index);
                break;
            }
            index +=1;
        }
    }
    
    public static ArrayList<LocalTime> getHorariosEventos(){
        return Reserva.horariosEvento;
    }
    
    /**
     * Metodo de ordenamiento por fecha. Este metodo debe inicirse cada dia de forma automatica
     * Ordena la lista de reservas que se inicializa al abrir la aplicacion
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
     * Metodo para buscar todas las reservas de una hora
     * @param reservasFecha Lista de todas las reservas de una fecha determinada
     * @param hora1 hora de busqueda
     * @return lista de todas las reservas de un dia y una hora de interes
     */
    public ArrayList<Reserva> busquedaPorHora(ArrayList<Reserva> reservasFecha, LocalTime hora1) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        
        for (Reserva ext : reservasFecha){
            if(ext.getHora().toString().equals(hora1.toString())){
                resultado.add(ext);
            }
        }
        return resultado;
    }
    
    /**
    * Metodo de uso administrativo para ver todas las reservas, posiblemente deba colocarlo como metodo de Administrador
    * @param fechaDesde fecha limite inferior
    * @param fechaHasta fecha limite superior
    * @return devuelve un arraylist con formatos para imprimir todos los detalles de la reserva
    * 
    */
    public static ArrayList<String> historialReservas(LocalDate fechaDesde, LocalDate fechaHasta) {
        
        Reserva.ordenarFechaReservas();
        ArrayList<Reserva> reservas = Reserva.getListaReservas();
        
        ArrayList<String> listadoImprimir = new ArrayList<>();
        
        for (Reserva ext : reservas) {
            if ((!ext.getDia().isBefore(fechaDesde)) && (!ext.getDia().isAfter(fechaHasta))) {
                    listadoImprimir.add(ext.toString());
                }
        }
        return listadoImprimir;
    }
    
    @Override
    public String toString() {
        return "RESERVA: " + idReserva + "\nCliente: " + clienteReserva.getNombre() + "\nDia: " + dia + "\nHora: " + hora + "\nMesa reservada: " + mesaReservada + "\nCantidadComensales: " + cantidadComensales + "\nEstado de asistencia: " + estadoAsist + "\nComentarios: " + comentarios;
    }
    
}