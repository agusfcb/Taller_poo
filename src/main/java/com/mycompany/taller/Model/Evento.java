package com.mycompany.taller.Model;

import com.mycompany.taller.Model.Mesa;
import com.mycompany.taller.Model.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Agus
 */
public class Evento {
    
    private String nombreEvento;
    private LocalDate fechaEvento;
    private LocalTime horarioDesde;
    private LocalTime horarioHasta;
    private Administrador adminEvento;
    private ArrayList<String> mesasEvento = new ArrayList<>();
    private ArrayList<Reserva> reservaEvento = new ArrayList<>();
    
    
    public Evento(){}
    
    
    public Evento(String nombre, LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin, String ubic, Administrador admin) {
        this.nombreEvento = nombre;
        this.fechaEvento = fechaEvento;
        this.horarioDesde = horaInicio;
        this.horarioHasta = horaFin;
        this.adminEvento = admin;
        
        this.crearEventoPorUbicacion(fechaEvento, horaFin, horaFin, ubic, admin);
    }
    
    public Evento(String nombre, LocalDate fechaEvento, LocalTime horaInicio, LocalTime horaFin) {
        this.nombreEvento = nombre;
        this.fechaEvento = fechaEvento;
        this.horarioDesde = horaInicio;
        this.horarioHasta = horaFin;
    }
    

    //AGREGAR METODO PARA LA PERSISTENCIA
    
    


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

    public void setReservaEvento(ArrayList<Reserva> reservaEvento) {
        this.reservaEvento = reservaEvento;
    }

    public String getUbicacionEvento() {
        return ubicacionEvento;
    }

    public void setUbicacionEvento(String ubicacionEvento) {
        this.ubicacionEvento = ubicacionEvento;
    }
    
    
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
        
        for(Mesa extMesa : Mesa.getMesasTot()){
            boolean control = true;
            for (Reserva extReserv : reservasFechaHora){
                if(extReserv.getMesaReservada().getNumero().equals(extMesa.getNumero())){
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
     * Metodo para comprobar si todas las mesas de una ubicacion estan disponibles en un dia y horario eespecifico
     * @param fechaE
     * @param horaInicio
     * @param horaFin
     * @param ubicacion
     * @return 
     */
    public static boolean controlUbicacion(LocalDate fechaE, LocalTime horaInicio, LocalTime horaFin, String ubicacion){
        return Evento.comprobarUbicacionDisponible(fechaE, horaInicio, horaFin, ubicacion);
    }
    
    /**
     * Metodo que comprueba si hay reservas en una ubicacion con parametros del dia y rango horario
     * @param fechaE
     * @param horaInicio
     * @param horaFin
     * @param ubicacion
     * @return 
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
     * @return Devuelve la lista de las nuevas reservas creadas para el evento
     */
    public ArrayList<Reserva> crearEventoPorUbicacion(LocalDate fecha, LocalTime horaI, LocalTime horaF, String ubicacion, Administrador admin){
        
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
                Reserva nuevaRes = new Reserva(fecha, horaRes, mesaRes, admin);
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
    private ArrayList<Reserva> crearEventoPorMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas, Administrador admin){
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
                Reserva nuevaReserva = new Reserva(fecha, horaRes, mesaRes, admin);
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
    public void crearEventoUbicacion(LocalDate fecha, LocalTime horaI, LocalTime horaF, String ubicacion, Administrador adminE){
        this.reservaEvento = crearEventoPorUbicacion(fecha, horaI, horaF, ubicacion, adminE);
    }
    
    
    /**
     * Metodo publico para crear eventos por mesas disponibles
     * @param fecha
     * @param horaI
     * @param horaF
     * @param NumerosMesas 
     */
    public void crearEventoConMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas, Administrador adminE){
        this.reservaEvento = this.crearEventoPorMesas(fecha, horaI, horaF, numerosMesas, adminE);
    }

}
