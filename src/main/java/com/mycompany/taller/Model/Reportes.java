package com.mycompany.taller.Model;
import java.util.*;
import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * Clase para representar los reportes y las funciones asociadas
 * @author Agustin y Juan
 */
public class Reportes {
    private ArrayList<Reserva> reservas;
    private ArrayList<Administrador> administradores;
    private ESTACION estacion;
    
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
     * @param fecha fecha de interes
     * @return devuelve el numero de reservas en esa fecha
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
     * @param fecha fecha de interes
     * @return devuelve la cantidad de reservas en formato String para presentar
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
     * Metodo para contar reservas de toda una semana
     * @param fecha fecha de algun dia de la semana de interes
     * @return devuelve el resultado del conteo de las reservas de la semana de interes
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
    
    /**
     * Metodo para mostrar las reservas de la semana
     * @param fecha fecha de un dia de la semana de interes
     * @return devuelve un String para presentar los resultados del conteo
     */
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
     * @param fecha fecha de un dia cualquiera del mes de interes
     * @return devuelve un entero resultado del conteo
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
     * Metodo para mostrar las reservas del mes
     * @param fecha fecha de un dia cualquiera del mes de interes
     * @return devuelve un String en formato para presentar los resultados del conteo
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
     * @param clie objeto de tipo Cliente
     * @return devuelve un array de las reservas futuras del cliente
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
     * @param clie objeto del tipo Cliente
     * @return devuelve un String en formato para presentar el resutlado del conteo
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
     * @param clie objeto del tipo cliente
     * @return devuelve un array con las reservas en formato String de un cliente en el año en transcurso
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
     * Metodo publico para mostrar el historial de reservas de un cliente
     * @param idClie id del cliente
     * @return devuelve un String para presentar las reservas del cliente
     */
    public String mostrarHistorialReservas(long idClie){
        Cliente clie = null;
        for (Usuario extCliente : Usuario.getListaUsuarios()){
            if(String.valueOf(extCliente.getIdUsuario()).equals(String.valueOf(idClie))){
                clie = (Cliente) extCliente;
                break;
            }
        }
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
     * Metodo privado para encontrar al cliente con mas asistencias
     * @return devuelve un objeto del tipo Cliente resultado de la busqueda
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
     * Metodo publico para buscar al cliente con mas asistencia
     * @return devuelve un String para presentar al cliente con mas asistencia
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
     * @return devuelve un array de objetos de tipo Cliente
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
    
    /**
     * Metodo publico para mostrar a los clientes sin asistencias confimadas aun en el transcurso del año
     * @return devuelve un String para presentar la lista de clientes sin asistencias confirmadas
     */
    public String mostrarClientesSinAsistencia() {
        ArrayList<Cliente> clientes = clientesSinAsistenciaAño();
        String detalles = "Clientes sin asistencia en el ultimo año:\n";
        
        for(Cliente cliente : clientes){
            detalles += cliente.getNombre() + "\n";
        }
        return detalles;
    }
    
    /**
     * Metodo privado para buscar las reservas en un rango de fecha
     * @param desde fecha de inicio de la busqueda
     * @param hasta fecha de finalizacion de la busqueda
     * @return devuelve un array de las reservas en el rango de fechas
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
     *Metodo publico para buscar las reservas en un rango de fechas
     * @param desde fecha limite inferior
     * @param hasta fecha limite superior
     * @return devuelve un array de String en formato para presentar las reservas en el rango de fechas
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
     * Metodo publico para presentar la estacion con mas clientes
     * @return devuelve un String con formato para presentar la estacion con mas clientes
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
