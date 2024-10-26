package com.mycompany.taller.Model;
import java.util.*;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
/**
 * 
 */
public class Reportes {
    private ArrayList<Reserva> reservas;
    private ArrayList<Administrador> administradores;
    private ESTACION estacion;
    
    //Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
    public Reportes(){
        this.reservas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.estacion = null;
    }
    public Reportes(ArrayList<Reserva> reservas, ArrayList<Administrador> administradores, ESTACION estacion) {
        this.reservas = reservas;
        this.administradores = administradores;
        this.estacion = estacion;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }
    public ESTACION getEstacion() {
        return estacion;
    }
    public void setEstacion(ESTACION estacion) {
        this.estacion = estacion;
    }
    /**
     * Metodo privado para contar las reservas de un dia especifico
     * @param fecha
     * @return Integer el numero de reservas en esa fecha
     */
    private Integer contarReservasDia(LocalDate fecha) {
        int contador = 0;
        for(Reserva reserva : reservas) {
            if(reserva.getDia().equals(fecha)){
                contador++;
            }
        }
        return contador;
    }
    /**
     * Metodo para mostrar las reservas 
     * @param fecha
     * @return String
     */
    public String mostrarReservasDia(LocalDate fecha) {
        Integer totalReservas = contarReservasDia(fecha);
        String detalles = "Reservas del dia " + fecha + ":\n";
        detalles += "Total de reservas: " + totalReservas + ":\n";
        
        for(Reserva reserva : reservas){
            if(reserva.getDia().equals(fecha)){
                detalles += reserva.toString() + "\n";
            }
        }
        
        return detalles;
    }
    /**
     * 
     * @param fecha
     * @return 
     */
    private Integer contarReservasSemana(LocalDate fecha) {
        int contador = 0;
        
        // Obtener inicio de la semana
        LocalDate inicioSemana = fecha.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        
        // Obtener fin de la semana
        LocalDate finSemana = fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        
        for(Reserva reserva : reservas){
            if(!reserva.getDia().isBefore(inicioSemana) && !reserva.getDia().isAfter(finSemana)){
                contador++;
            }
        }
        return contador;
        
    }
    
    
    public String mostrarReservasSemana(LocalDate fecha) {
        Integer totalReservas = contarReservasSemana(fecha);
        String detalles = "Reservas para la semana " + fecha + ":\n";
        detalles += "Total de reservas: " + totalReservas + "\n";

        // Obtener el inicio de la semana
        LocalDate inicioSemana = fecha.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        // Obtener el fin de la semana
        LocalDate finSemana = fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        for (Reserva reserva : reservas) {
            // Mostrar reservas que caen dentro de la semana
            if (!reserva.getDia().isBefore(inicioSemana) && !reserva.getDia().isAfter(finSemana)) {
                detalles += reserva.toString() + "\n";
            }
        }

        return detalles;
    }
    /**
     * Metodo para contar las reservas por mes
     * @param fecha
     * @return Integer
     */
    private Integer contarReservasMes(LocalDate fecha) {
        int contador = 0;
        
        // Devuelve el valor del mes
        int mes = fecha.getMonthValue();
        
        for(Reserva reserva : reservas) {
            if(reserva.getDia().getMonthValue() == mes){
                contador++;
            }
        }
        return contador;  
    }
    /**
     * 
     * @param fecha
     * @return 
     */
    public String mostrarReservasMes(LocalDate fecha) {
        Integer totalReservas = contarReservasMes(fecha);
        int mes = fecha.getMonthValue();
        String detalles = "Reservas para el mes " + mes + ": " + totalReservas + "\n";
        
        for(Reserva reserva : reservas) {
            if(reserva.getDia().getMonthValue() == mes) {
                detalles += reserva.toString() + "\n";
            }
        }
        return detalles;
    }
    
    /**
     * Metodo privado para buscar las reservas futuras de un cliente
     * @param clie
     * @return ArrayList<Reserva>
     */
    private ArrayList<Reserva> buscarReservasFuturasCliente(Cliente clie) {
        ArrayList<Reserva> reservasFuturas = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
        
        for(Reserva reserva : reservas) {
            if(reserva.getClienteReserva().equals(clie) && reserva.getDia().isAfter(fechaActual)) {
                reservasFuturas.add(reserva);
            }
        }
        return reservasFuturas;
    }
    
    /**
     * Metodo para mostrar las reservas futuras de un cliente si es que tiene
     * @param clie
     * @return String
     */
    public String mostrarReservasFuturasCliente(Cliente clie) {
        ArrayList<Reserva> reservasFuturas = buscarReservasFuturasCliente(clie);
        String detalles = "Reservas futuras para el cliente " + clie.getNombre() + ":\n";
        
        for(Reserva reserva : reservasFuturas) {
            detalles += reserva.toString() + ":\n";
        }
        // Operador ternario 
        return reservasFuturas.isEmpty() ? "No hay reservas futuras para este cliente" : detalles;
    }
    /**
     * Metodo privado para buscar el historial de reservas de un cliente
     * @param clie
     * @return 
     */
    private ArrayList<String> buscarHistorialReservas(Cliente clie) {
        ArrayList<String> historialReservas = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
    
        // Definir la fecha de inicio y fin para el historial (por ejemplo, desde el inicio del año hasta la fecha actual)
        LocalDate inicio = LocalDate.of(fechaActual.getYear(), 1, 1);
        LocalDate fin = fechaActual;

        // Llamar al método estático para obtener todas las reservas en el rango de fechas
        ArrayList<String> reservasEnRango = Reserva.historialReservas(inicio, fin);
    
        // Filtrar las reservas para el cliente específico
        for (String reservaString : reservasEnRango) {
            // Suponiendo que tu método toString() de Reserva incluye el nombre del cliente
            if (reservaString.contains(clie.getNombre())) { // Comprobar si el nombre del cliente está en la cadena
                historialReservas.add(reservaString); // Agregar la representación en cadena de la reserva
            }
        }
        return historialReservas;
    }
    /**
     * 
     * @param clie
     * @return String
     */
    public String mostrarHistorialReservas(Cliente clie){

        ArrayList<String> historialReservas = buscarHistorialReservas(clie);
        
        if(historialReservas.isEmpty()) {
           return "No hay reservas en el historial para el cliente " + clie.getNombre() + ".";
        }
        
        String detalles = "Historial de reservas para el cliente " + clie.getNombre() + ":\n";
        
        for(String reservaString : historialReservas){
            detalles += reservaString + "\n";
        }
        return detalles;
    }
        
    /**
     * 
     * @return 
     */
    private Cliente clienteMasAsistencia() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Integer> conteos = new ArrayList<>();
        
        for(Reserva reserva : reservas) {
            Cliente cliente = (Cliente) reserva.getClienteReserva();
            int index = clientes.indexOf(cliente);
            
            if(index != -1){
                conteos.set(index, conteos.get(index) + 1);
            } else {
                clientes.add(cliente);
                conteos.add(1);
            }
        }
        Cliente clienteMasAsistente = null;
        int maxReservas = 0;
        
        for(int i = 0; i < clientes.size(); i++) {
            if(conteos.get(i) > maxReservas){
                maxReservas = conteos.get(i);
                clienteMasAsistente = clientes.get(i);
            }
        }
        return clienteMasAsistente;
    }
    /**
     * 
     * @return 
     */
    public String mostrarClienteMasAsistencia() {
        Cliente cliente = clienteMasAsistencia();
        
        if (cliente == null) {
            return "No hay clientes registrados con reservas";
        }
        int reservasDelCliente = 0;
        for(Reserva reserva : reservas){
            if(reserva.getClienteReserva().equals(cliente)){
                reservasDelCliente++;
            }
        }
        return "El cliente con más asistencias es: " + cliente.getNombre() + " con " + reservasDelCliente + " reservas.";
    }
    /**
     * Metodo privado que devuelve a los clientes que han realizado reservas pero no han asistido en el ultimo año
     * @return 
     */
    private ArrayList<Cliente> clientesSinAsistenciaAño() {
        ArrayList<Cliente> clientesSinAsistencia = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        LocalDate haceUnAño = hoy.minusYears(1);
    
        for (Reserva reserva : Reserva.getListaReservas()) {
            if (reserva.getEstadoAsist().equals("Sin asistir") && 
                (reserva.getDia().isAfter(haceUnAño) && reserva.getDia().isBefore(hoy))) {
            
                Cliente cliente = (Cliente) reserva.getClienteReserva();
                if (!clientesSinAsistencia.contains(cliente)) {
                clientesSinAsistencia.add(cliente);
                }
            }
        }   
        return clientesSinAsistencia;   
    }
    
    public String mostrarClientesSinAsistencia() {
        ArrayList<Cliente> clientes = clientesSinAsistenciaAño();
        String detalles = "Clientes sin asistencia en el ultimo año:\n";
        
        for(Cliente cliente : clientes){
            detalles += cliente.getNombre() + "\n";
        }
        return detalles;
    }
    /**
     * 
     * @param desde
     * @param hasta
     * @return 
     */
    private ArrayList<Reserva> buscarListaReservasRango(LocalDate desde, LocalDate hasta) {
        ArrayList<Reserva> reservasEnRango = new ArrayList<>();
        for (Reserva reserva : Reserva.getListaReservas()) {
            LocalDate fechaReserva = reserva.getDia();
            if ((fechaReserva.isAfter(desde) || fechaReserva.equals(desde)) && (fechaReserva.isBefore(hasta) || fechaReserva.equals(hasta))) {
                reservasEnRango.add(reserva);
            }
        }
        return reservasEnRango;
    }
    /**
     * 
     * @param desde
     * @param hasta
     * @return 
     */
    public ArrayList<String> mostrarListaReservasRango(LocalDate desde, LocalDate hasta) {
        ArrayList<Reserva> reservasEnRango = buscarListaReservasRango(desde, hasta);
        ArrayList<String> detallesReservas = new ArrayList<>();

        for (Reserva reserva : reservasEnRango) {
            String detalle = "Mesa: " + reserva.getMesaReservada().getNumero() +
                         ", Fecha: " + reserva.getDia() +
                         ", Hora de Inicio: " + reserva.getHora() +
                         ", Hora de Fin: " + reserva.getHora().plusHours(2) + // Ejemplo, asumiendo duración de 2 horas
                         ", Cliente: " + reserva.getClienteReserva().getNombre() +
                         ", Capacidad de Comensales: " + reserva.getCantidadComensales();
            detallesReservas.add(detalle);
        }
        return detallesReservas;
    }
    /**
     * 
     * @return String
     */
    public String MostrarEstacionConMasClientes() {
        int primavera = 0;
        int verano = 0;
        int otoño = 0;
        int invierno = 0;
        String estacionConMasClientes;
        
        for (Reserva reserva : Reserva.getListaReservas()) { // Suponiendo que tienes acceso a todas las reservas
            LocalDate fecha = reserva.getDia();
            Month mes = fecha.getMonth();

            // Clasificamos la reserva en la estación correspondiente
            if (mes == Month.DECEMBER || mes == Month.JANUARY || mes == Month.FEBRUARY) {
                verano += 1;
            } else if (mes == Month.MARCH || mes == Month.APRIL || mes == Month.MAY) {
                otoño += 1;
            } else if (mes == Month.JUNE || mes == Month.JULY || mes == Month.AUGUST) {
                invierno += 1;
            } else if (mes == Month.SEPTEMBER || mes == Month.OCTOBER || mes == Month.NOVEMBER) {
                primavera += 1;
            }
        }
        // Determinar la estación con más clientes
        int maxClientes = Math.max(Math.max(primavera, verano), Math.max(otoño, invierno));
        
        if (maxClientes == verano) {
            estacionConMasClientes = "Verano";
        } else if (maxClientes == otoño) {
            estacionConMasClientes = "Otoño";
        } else if (maxClientes == invierno) {
            estacionConMasClientes = "Invierno";
        } else {
            estacionConMasClientes = "Primavera";
        }
        
        return "La estacion con mas clientes es: " + estacionConMasClientes;
    }
}
