/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Agus
 */
public class Evento {
    private LocalDate fechaEvento;
    private LocalTime horarioDesde;
    private LocalTime horarioHasta;
    private ArrayList<Reserva> reservaEvento;
    
    public Evento(){}
    
    public Evento(LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin, ArrayList<Reserva> reserEvent) {
        this.fechaEvento = fechaEvento;
        this.horarioDesde = horaInicio;
        this.horarioHasta = horaFin;
    }

    
    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public LocalTime getHorarioDesde() {
        return horarioDesde;
    }

    public void setHorarioDesde(LocalTime horarioDesde) {
        this.horarioDesde = horarioDesde;
    }

    public LocalTime getHorarioHasta() {
        return horarioHasta;
    }

    public void setHorarioHasta(LocalTime horarioHasta) {
        this.horarioHasta = horarioHasta;
    }
    
    public void addReserva(Reserva res){
        this.reservaEvento.add(res);
    }
    
    /**
     * Metodo para crear reservas sobre un evento
     * @param fecha Fecha del evento
     * @param horaI Hora de inicio del evento
     * @param horaF Hora de fin del evento
     * @param ubicacion Ubicacion de las mesas que se bloquearan
     * @return Devuelve la lista de las nuevas reservas creadas para el evento
     */
    public ArrayList<Reserva> crearEventoPorUbicacion(LocalDate fecha, LocalTime horaI, LocalTime horaF, String ubicacion){
        
        ArrayList<LocalTime> horarioUtil = (ArrayList<LocalTime>) Reserva.getHorariosEventos().clone(); 

        ArrayList<Mesa> mesas = Mesa.getMesasTot();
        ArrayList<Mesa> mesasUbicacion = new ArrayList<>();
        
        ArrayList<Reserva> reservasNuevas = new ArrayList<>();
        //Se filtran todas las mesas de una ubicacion
        for (Mesa copiar : mesas) {
            if (copiar.getUbicacion().equals(ubicacion)){
                mesasUbicacion.add(copiar);
            }
        }

        for (LocalTime horaU : horarioUtil){
            if (horaU.isBefore(horaI) | horaU.isAfter(horaF)){
                horarioUtil.remove(horaU);
            }
        }
        for (Mesa mesaRes : mesasUbicacion) {
            for (LocalTime horaRes : horarioUtil){
                Reserva nuevaRes = new Reserva(fecha, horaRes, mesaRes);
                reservasNuevas.add(nuevaRes);
            }
        }
        return reservasNuevas;
    }
    
    /**
     * Metodo para crear reservas sobre un evento
     * @param fecha Fecha del evento
     * @param horaI Hora de inicio del evento
     * @param horaF Hora de fin del evento
     * @param numerosMesa Array de los numeros de cada mesa a reservar
     * @return Devuelve la lista de las nuevas reservas creadas para el evento
     */
    private ArrayList<Reserva> crearEventoPorMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas){
        ArrayList<Mesa> mesas = Mesa.getMesasTot();
        ArrayList<Mesa> mesasUbicacion = new ArrayList<>();
        ArrayList<LocalTime> horarioUtil = (ArrayList<LocalTime>) Reserva.getHorariosEventos().clone();
        ArrayList<Reserva> reservasNuevas = new ArrayList<>();
        
        for (Mesa copiar : mesas) {
            for (String num : numerosMesas){
                if (copiar.getNumero().equals(num)){
                    mesasUbicacion.add(copiar);
                }
            }
        }
        for (LocalTime horaU : horarioUtil){
            if (horaU.isBefore(horaI) | horaU.isAfter(horaF)){
                horarioUtil.remove(horaU);
            }
        }
        for (Mesa mesaRes : mesasUbicacion){
            for(LocalTime horaRes : horarioUtil){
                Reserva nuevaReserva = new Reserva(fecha, horaRes, mesaRes);
                reservasNuevas.add(nuevaReserva);
            }
        }
        return reservasNuevas;
    }

        
    /**
     * Metodo publico para crear eventos por ubicaciones completas disponibles
     * @param fecha
     * @param horaI
     * @param horaF
     * @param NumerosMesas 
     */
    public void crearEventoConMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, String ubicacion){
        this.reservaEvento = crearEventoPorUbicacion(fecha, horaI, horaF, ubicacion);
    }
    
    /**
     * Metodo publico para crear eventos por mesas disponibles
     * @param fecha
     * @param horaI
     * @param horaF
     * @param NumerosMesas 
     */
    public void crearEventoConMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> NumerosMesas){
        this.reservaEvento = this.crearEventoPorMesas(fecha, horaI, horaF, NumerosMesas);
    }

}
