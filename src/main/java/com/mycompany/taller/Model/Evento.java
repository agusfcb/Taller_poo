package com.mycompany.taller.Model;

import com.mycompany.taller.Model.Administrador;
import java.io.Serializable;
import com.mycompany.taller.Model.Mesa;
import com.mycompany.taller.Model.Reserva;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 *Clase para representar al Evento y las funciones asociadas
 * @author Agustin y Juan
 */

public class Evento implements Serializable {

    private long idEvento;

    private String idReserva;

    private String nombreEvento;
    private LocalDate fechaEvento;
    private LocalTime horarioDesde;
    private LocalTime horarioHasta;
    

    private Administrador adminEvento;

    private ArrayList<Reserva> reservaEvento = new ArrayList<>();
    
    
    public Evento(){}
    
    /**
     * Constructor con mesas de una misma ubicacion
     * @param nombre nombre
     * @param fechaEvento fecha del evento
     * @param horaInicio fecha del primer turno
     * @param horaFin fecha del ultimo turno
     * @param adminEv administrador que crea el evento
     * @param ubic ubicacion de un sector de mesas
     */
    public Evento(String nombre, LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin, String ubic, Administrador adminEv) {
        this.nombreEvento = nombre;
        this.fechaEvento = fechaEvento;
        this.horarioDesde = horaInicio;
        this.horarioHasta = horaFin;
        this.adminEvento = adminEv;
        this.crearEventoUbicacion(fechaEvento, horaFin, horaFin, ubic, adminEv);
    }
    
    /**
     * Constructor por lista de mesas de diferentes ubicaciones
     * @param nombre nombre
     * @param fechaEvento fecha del evento
     * @param horaInicio fecha del primer turno
     * @param horaFin fecha del ultimo turno
     * @param adminEv administrador que crea el evento
     * @param numerosMesas numero de mesas 
     */
    public Evento(String nombre, LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin, ArrayList<String> mesasElegidas ,Administrador adminEv) {
        this.nombreEvento = nombre;
        this.fechaEvento = fechaEvento;
        this.horarioDesde = horaInicio;
        this.horarioHasta = horaFin;
        this.adminEvento = adminEv;
        this.crearEventoMesas(fechaEvento, horaInicio, horaFin, mesasElegidas, adminEv);
    }
    
    /**
     * Constructor para la persistencia
     * @param nombre nombre
     * @param fechaEvento fecha del evento
     * @param horaInicio hora del primer turno
     * @param horaFin hora del ultimo turno
     * @param adminEv administrador que crea el evento
     * @param listaReservasEvento lista de reservas asociadas al evento
     * @param listaMesasEvento lista de mesas asociadas al evento
     */
    public Evento(String nombre, LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin,Administrador adminEv, ArrayList<Reserva> listaReservasEvento){
        this.nombreEvento = nombre;
        this.fechaEvento = fechaEvento;
        this.horarioDesde = horaInicio;
        this.horarioHasta = horaFin;
        this.adminEvento = adminEv;
        this.setReservas(listaReservasEvento);
    }    

    public long getIdEvento() {
        return idEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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

    public Administrador getAdminEvento() {
        return adminEvento;
    }

    public void setAdminEvento(Administrador adminEvento) {
        this.adminEvento = adminEvento;
    }

    public ArrayList<Reserva> getReservaEvento() {
        return reservaEvento;
    }
            
    /**
     * Metodo para agregar reservas a la lista de reservas del evento. Uso en creacion de reservas
     * @param res objeto del tipo Reserva para agregar a la lista de reservas del evento
     */
    public void agregarReservaEvento(Reserva res){
        this.reservaEvento.add(res);
    }
    
    /**
     * Metodo para setear toda la lista de reservas del evento. Uso principal con la persistencia
     * @param listaReser arraylist de objetos del tipo Mesa
     */
    public void setReservas(ArrayList<Reserva> listaReser) {
        this.reservaEvento = listaReser;
    }
    
    /**
     * Metodo para obtener las mesas disponibles para el evento
     * @param fechaEvento fecha del evento
     * @param horaInicio hora de inicio
     * @param horaFin hora de finalizacion
     * @return arraylist de las mesas disponibles
     */
    public ArrayList<Mesa> verMesaDisponibleParaEvento(LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin) {
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas();
        ArrayList<Reserva> reservaDia = new ArrayList<>();
        ArrayList<Reserva> reservasFechaHora = new ArrayList<>();
        ArrayList<Mesa> mesasDisponibles = new ArrayList<>();
        
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isBefore(fechaEvento)) {
            return null;
        }
        
        reservaDia = this.filtroDia(listaReservas, fechaEvento);
        //Si no hay reservas dicho dia, estan todas las mesas y horarios disponibles
        if (reservaDia.equals(null)){
            return mesasDisponibles;
        }
        
        reservasFechaHora = this.filtroHora(reservaDia, horaInicio, horaFin);
        
        for(Mesa extMesa : Mesa.getMesasExistentes()){
            boolean control = true;
            for (Reserva extReserv : reservasFechaHora){
                if(extReserv.getMesaReservada().getIdMesa().equals(extMesa.getIdMesa())){
                    control = false;
                }
            }
            if(control){
                mesasDisponibles.add(extMesa);
            }
        }
        return mesasDisponibles;
    }

    /**
     * Metodo para ver filtrar por dia las reservas
     *
     * @param ArrayList<Reserva> listaReservas
     * @param String fecha
     * @return ArrayList<Reserva>
     */
    private static ArrayList<Reserva> filtroDia(ArrayList<Reserva> listaReservas, LocalDate fecha) {

        ArrayList<Reserva> filtroFecha = new ArrayList<>();
        boolean vacio = true;

        for (Reserva extraer : listaReservas) {
            if (extraer.getDia().toString().equals(fecha.toString())) {
                vacio = false;
                filtroFecha.add(extraer);
            }
        }
        if (vacio) {
            return null;
        } else {
            return filtroFecha;
        }
    }

    /**
     * 
     * @param ubi
     * @param listaAux
     * @param horaDesde
     * @param horaHasta
     * @return 
     */
    private static ArrayList<Reserva> filtroHora(ArrayList<Reserva> listaAux, LocalTime horaDesde, LocalTime horaHasta) {
        ArrayList<LocalTime> horariosEspeciales = Reserva.getHorariosEventos();
        ArrayList<LocalTime> horarioEvento = new ArrayList<>();
        ArrayList<Reserva> reservasHora = new ArrayList<>();
        
        for (LocalTime horaExt : horariosEspeciales){
            if(!horaExt.isBefore(horaDesde) && !horaExt.isAfter(horaHasta)){
                horarioEvento.add(horaExt);
            }
        }

        for(Reserva extReserva : listaAux) {
            for (LocalTime extHora : horarioEvento){
                if(extReserva.getHora().toString().equals(extHora.toString())){
                    reservasHora.add(extReserva);
                }    
            }
        }
        return reservasHora;
    }
    
    //METODOS PARA RESERVAS DE EVENTO POR UBICACION
    /**
     * Metodo publico para comprobar si todas las mesas de una ubicacion estan disponibles en un dia y horario eespecifico
     * @param fechaE fecha del evento
     * @param horaInicio hora del primer turno
     * @param horaFin hora del ultimo turno
     * @param ubicacion ubicacion
     * @return true si la ubicacion completa esta disponible
     */
    public static boolean controlUbicacion(LocalDate fechaE, LocalTime horaInicio, LocalTime horaFin, String ubicacion){
        return Evento.comprobarUbicacionDisponible(fechaE, horaInicio, horaFin, ubicacion);
    }
    
    /**
     * Metodo que comprueba si hay reservas en una ubicacion con parametros del dia y rango horario
     * @param fechaE fecha del evento
     * @param horaInicio hora del primer turno
     * @param horaFin hora del ultimo turno
     * @param ubicacion ubicacion
     * @return true si la ubicacion completa esta disponible
     */
    private static boolean comprobarUbicacionDisponible(LocalDate fechaE, LocalTime horaInicio, LocalTime horaFin, String ubicacion) {
        
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas();
        ArrayList<Reserva> reservaDia = new ArrayList<>();
        ArrayList<Reserva> reservasFechaHora = new ArrayList<>();
        
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isBefore(fechaE)) {
            return false;
        }
        
        reservaDia = filtroDia(listaReservas, fechaE);
        //Si no hay reservas dicho dia, estan todas las mesas y horarios disponibles
        if (reservaDia.equals(null)){
            return true;
        }
        
        reservasFechaHora = filtroHora(reservaDia, horaInicio, horaFin);
        //Si no hay reservas en el horario del evento, se pueden reservar todas las mesas
        if(reservasFechaHora.equals(null)){
            return true;
        }
        //Control que no exista reservas en la mesa de una ubicacion
        for (Reserva extRes : reservasFechaHora){
            if(extRes.getMesaReservada().getUbicacion().equals(ubicacion)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodo para crear reservas sobre un evento
     * @param fecha Fecha del evento
     * @param horaI Hora de inicio del evento
     * @param horaF Hora de fin del evento
     * @param ubicacion Ubicacion de las mesas que se bloquearan
     */
    public void crearEventoPorUbicacion(LocalDate fecha, LocalTime horaI, LocalTime horaF, String ubicacion, Administrador admin){
        
        
        ArrayList<LocalTime> horarioUtil = (ArrayList<LocalTime>) Reserva.getHorariosEventos().clone(); 

        ArrayList<Mesa> mesas = Mesa.getMesasExistentes();
        ArrayList<Mesa> mesasUbicacion = new ArrayList<>();
        
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
                Reserva nuevaRes = new Reserva(fecha.toString(), horaRes.toString(), mesaRes, admin);
                this.agregarReservaEvento(nuevaRes);
            }
        }
    }
    
    /**
     * Metodo para crear reservas sobre un evento
     * @param fecha Fecha del evento
     * @param horaI Hora de inicio del evento
     * @param horaF Hora de fin del evento
     * @param numerosMesa Array de los numeros de cada mesa a reservar
     * @return Devuelve la lista de las nuevas reservas creadas para el evento
     */
    private void crearEventoPorMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas, Administrador admin){
        ArrayList<Mesa> mesas = Mesa.getMesasExistentes();
        ArrayList<Mesa> mesasUbicacion = new ArrayList<>();
        ArrayList<LocalTime> horarioUtil = (ArrayList<LocalTime>) Reserva.getHorariosEventos().clone();
        
        for (Mesa copiar : mesas) {
            for (String num : numerosMesas){
                if (copiar.getIdMesa().equals(num)){
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
                Reserva nuevaReserva = new Reserva(fecha.toString(), horaRes.toString(), mesaRes, admin);
                this.agregarReservaEvento(nuevaReserva);
            }
        }
    }

    
    /**
     * Metodo publico para crear eventos por ubicaciones completas disponibles
     * @param fecha fecha del evento
     * @param horaI hora del primer turno
     * @param horaF hora de ultimo turno
     * @param NumerosMesas numero de mesas elegidas
     */
    public void crearEventoUbicacion(LocalDate fecha, LocalTime horaI, LocalTime horaF, String ubicacion, Administrador adminE){
        this.crearEventoPorUbicacion(fecha, horaI, horaF, ubicacion, adminE);
    }
    
    
    /**
     * Metodo publico para crear eventos por mesas disponibles
     * @param fecha fecha del evento
     * @param horaI hora del primer turno
     * @param horaF hora de ultimo turno
     * @param NumerosMesas numero de mesas elegidas
     */
    public void crearEventoMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas, Administrador adminE){
        this.crearEventoPorMesas(fecha, horaI, horaF, numerosMesas, adminE);
    }

    /**
     * Metodo que libera las mesas asociadas
     */
    private void elimEvento(){
        for (Reserva extRes : this.getReservaEvento()){
            extRes.setComentarios("Eliminado/Cancelado");
            extRes.setEstado("Cancelado");
            //Se remueve la reserva del registro que mantiene la mesa
            extRes.getMesaReservada().removerReserva(extRes);            
            //Se remueve la mesa de la reserva
            extRes.setMesaReservada(null);

        }
    }
    
    /**
     * Metodo publico para eliminar eventos
     */
    public void eliminarEvento(){
        this.elimEvento();
    }
    
    public void setIdEvento(long idReserva) {
        this.idEvento = idReserva;
    }
}
