package com.mycompany.taller.Model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author Agustin y Juan
 */
@Entity
public class Reserva {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private String idReserva;
    @Temporal(TemporalType.DATE)
    private LocalDate dia;
    @Temporal(TemporalType.TIME)
    private LocalTime hora;
    @Basic
    private String estadoAsist;
    private Integer cantidadComensales;
    private String comentarios;
    
    private Mesa mesaReservada;
    private Usuario clienteReserva;
    private TarjetaCredito tarjeta;
    private static ArrayList<Reserva> listaReservas = new ArrayList<>();
    public static final ArrayList<String> estadosPosibles = new ArrayList<>(Arrays.asList("Pendiente", "Sin asistir", "Completado", "Cancelado", "Evento"));
    private static ArrayList<LocalDate> diasEspeciales = new ArrayList<>();
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
    
    private static ArrayList<LocalTime> horariosDiasEspeciales = new ArrayList<>(Arrays.asList(
        LocalTime.parse("09:00", formatter),
        LocalTime.parse("11:00", formatter), 
        LocalTime.parse("13:00", formatter), 
        LocalTime.parse("15:00", formatter), 
        LocalTime.parse("20:00", formatter), 
        LocalTime.parse("22:00", formatter), 
        LocalTime.parse("00:00", formatter),
        LocalTime.parse("02:00", formatter)));
    
    /*
    * Constructor para el cliente que hace la reserva
    */
    public Reserva(LocalDate dia, LocalTime hora, String coment, Integer comensales ,Mesa mesa, Cliente cliente, TarjetaCredito tarjeta) {
        this.idReserva = UUID.randomUUID().toString();
        this.dia = dia;
        this.hora = hora;
        this.estadoAsist = estadosPosibles.get(0);
        this.comentarios = coment;
        this.cantidadComensales = comensales;
        this.mesaReservada = mesa;
        this.clienteReserva = cliente;
        this.tarjeta = tarjeta;
        Reserva.listaReservas.add(this);
        cliente.addReserva(this);
        mesa.agregarReserva(this);
        tarjeta.agregarReser(this);
    }
    
    
    /*
    *Constructor para el administrador que gestiona eventos
    */
    public Reserva(LocalDate dia, LocalTime hora, Mesa mesa, Administrador admin) {
        this.idReserva = UUID.randomUUID().toString();
        this.clienteReserva = admin;
        this.estadoAsist = this.estadosPosibles.get(4);
        this.dia = dia;
        this.hora = hora;
        this.mesaReservada = mesa;
        this.cantidadComensales = null;
        this.comentarios = "Evento";
        Reserva.listaReservas.add(this);
        mesa.agregarReserva(this);
    }

        
    /*
    * Constructor para la persistencia
    */
    public Reserva(String idReserva, LocalDate dia, LocalTime hora, String estado, String coment, Integer comensales, Mesa mesa, Cliente cliente, TarjetaCredito tarjeta) {
        this.idReserva = idReserva;
        this.dia = dia;
        this.hora = hora;
        this.estadoAsist = estado;
        this.comentarios = coment;
        this.cantidadComensales = comensales;
        this.mesaReservada = mesa;
        this.clienteReserva = cliente;
        this.tarjeta = tarjeta;
        Reserva.listaReservas.add(this);
        cliente.addReserva(this);
        mesa.agregarReserva(this);
        tarjeta.agregarReser(this);
    }
    
    /*
    *Constructor para la persistencia
    */
    public Reserva(String idReserva, LocalDate dia, LocalTime hora, String estado, Mesa mesa, Administrador admin) {
        this.idReserva = idReserva;
        this.dia = dia;
        this.hora = hora;
        this.estadoAsist = estado;
        this.mesaReservada = mesa;
        this.clienteReserva = admin;
        this.cantidadComensales = null;
        this.comentarios = "Evento";
        mesa.agregarReserva(this);
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

    public Integer getCantidadComensales() {
        return cantidadComensales;
    }

    public void setCantidadComensales(Integer cantidadComensales) {
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
    
    public static ArrayList<String> getListaEstados(){
        return estadosPosibles;
    }
    
    public static ArrayList<Reserva> getListaReservas(){
        return listaReservas;
    }

    public static void setListaReservas(ArrayList<Reserva> listaReservas) {
        Reserva.listaReservas = listaReservas;
    }
    
    

    public static ArrayList<LocalDate> getDiasEspeciales() {
        return diasEspeciales;
    }
    
    public static void addDiaEspecial(LocalDate esp){
        Reserva.diasEspeciales.add(esp);
    }
    
    public static ArrayList<LocalTime> getHorarios(LocalDate fecha){
        for (LocalDate extDia : Reserva.diasEspeciales){
            if(extDia.toString().equals(fecha.toString())){
                return Reserva.horariosDiasEspeciales;
            }
        }
        return Reserva.horarios;
    }

    public static void setHorarios(ArrayList<LocalTime> horarios) {
        Reserva.horarios = horarios;
    }

    /**
     * Metodo para cambiar los horarios en tiempo de ejecucion. Pendiente de mejorar paga guardar en persistencia los horarios
     * @param horariosDiasEspeciales 
     */
    public static void setHorariosDiasEspeciales(ArrayList<LocalTime> horariosDiasEspeciales) {
        Reserva.horariosDiasEspeciales = horariosDiasEspeciales;
    }
    
    
    /**
     * Metodo que agrega hora a la lista de horario ordinario
     * @param horaExtra hora a agregar
     */
    public static void agregarHorario(LocalTime horaExtra){
        int index = 0;
        for (LocalTime compara : Reserva.horarios){
            if(horaExtra.toString().equals(compara.toString())){
                break;
            }
            if(horaExtra.isBefore(compara) | horaExtra.isAfter(compara)){
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
        for (LocalTime compara : Reserva.getHorarios(dia)){
            if(horaExtra.toString().equals(compara.toString())){
                Reserva.horarios.remove(index);
                break;
            }
            index +=1;
        }
    }
    
    /**
     * 
     * @return 
     */
    public static ArrayList<LocalTime> getHorariosEventos(){
        return Reserva.horariosDiasEspeciales;
    }
    
    
    //Metodos de ordenamiento
    
    /**
     * Metodo de ordenamiento por fecha. Este metodo debe inicirse cada dia de forma automatica
     * Ordena la lista de reservas que se inicializa al abrir la aplicacion
     */
    public static void ordenarFechaReservas(){
        ArrayList<Reserva> listaOrdenar = Reserva.getListaReservas();
        LocalDate primerRes;
        LocalDate comparaRes;
        Reserva aux1;
        Reserva aux2;
        int pivote = (listaOrdenar.size()/2);
        int largo = listaOrdenar.size();
        boolean ordenado = false;

        while (pivote > 0) {
            ordenado = true;
            for (int i = 0; i+pivote < largo; i++) {
                aux1 = listaOrdenar.get(i);
                aux2 = listaOrdenar.get(i+pivote);
            
                primerRes = aux1.getDia();
                comparaRes = aux2.getDia();
            
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
    
    public static ArrayList<Reserva> ordenarPorHora(ArrayList<Reserva> reservasDia){
        ArrayList<Reserva> listaOrdenada = reservasDia;
        Reserva aux1;
        Reserva aux2;
        LocalTime primerRes;
        LocalTime comparaRes;
        
        int pivote = (reservasDia.size()/2);
        int longitud = reservasDia.size();
        boolean ordenado = true;
        
        while (pivote > 0) {
            ordenado = true;
            for (int i = 0; i+pivote < longitud; i++) {
                aux1 = listaOrdenada.get(i);
                aux2 = listaOrdenada.get(i+pivote);
            
                primerRes = aux1.getHora();
                comparaRes = aux2.getHora();
            
                if (primerRes.isAfter(comparaRes)){
                    reservasDia.set(i, aux2);
                    reservasDia.set(i+pivote, aux1);
                    ordenado = false;
                }
            }
            pivote = pivote / 2;
            if(ordenado && (pivote == 0)){
                break;
            }
        }
        return listaOrdenada;
    }
    
    
    // Metodos de busqueda de reservas creadas
    
    
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
    
    
    
    // METODOS PARA LA BUSQUEDA DE MESAS LIBRES
    /**
     * Metodo publico para ver las mesas disponiles
     * @param fecha1 Fecha de interes
     * @param hora1 Hora de interes
     * @param capacidad Capacidad de la mesa de interes
     * @return 
     */
    public static ArrayList<Mesa> verMesaDisponible(LocalDate fecha1, LocalTime hora1, String capacidad){
        return Reserva.buscarMesaDisponible(fecha1, hora1, capacidad);
    }
    
    
    /** Metodo privado para encontrar mesas disponibles
     * @param String fecha1
     * @param String hora1
     * @param String capacidad
     * @return ArrayList<Mesa>
     */
    private static ArrayList<Mesa> buscarMesaDisponible(LocalDate fecha1, LocalTime hora1, String capacidad) {
        // Control de que no se busque en una fecha anterior al mismo dia que se realiza la reserva
        LocalDate fechaActual = LocalDate.now();
        if (fechaActual.isBefore(fecha1)){
            return null;
        }
        
        // Comienzo del algoritmo de busqueda
        
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas();
        ArrayList<Reserva> reservaDia = new ArrayList<>();
        ArrayList<Mesa> mesasTotales = Mesa.getMesasExistentes();
        ArrayList<Mesa> coincidenciaBusqueda = new ArrayList<>();
        
        reservaDia = filtroDia(listaReservas, fecha1);
        if(reservaDia.isEmpty()){
            return mesasTotales;
        }
        coincidenciaBusqueda = filtroHoraMesa(mesasTotales, reservaDia, hora1, capacidad);
        
        return coincidenciaBusqueda;
    }

    /**
     * Metodo para ver filtrar por dia las reservas
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
        if (vacio){
            return null;
        } else {
        return filtroFecha;
        }
    }
    
    /**
     * Metodo para filtrar mesas disponibles por hora y capadidad de la mesa
     * @param ArrayList<Mesa> mesasTotales
     * @param ArrayList<Reserva> listaAux
     * @param String hora
     * @param String capacidad
     * @return ArrayList<Mesa>
     */
    private static ArrayList<Mesa> filtroHoraMesa(ArrayList<Mesa> mesasTotales, ArrayList<Reserva> listaAux, LocalTime hora, String capacidad) {
        ArrayList<Mesa> mesasCapacidad = new ArrayList<>();
        
        
        for (Mesa extraerM : mesasTotales) {
            if (extraerM.getCapacidad().equals(capacidad)){
                mesasCapacidad.add(extraerM);
            }
        }
        
        for (Reserva extraer2 : listaAux) {
            if (extraer2.getHora().toString().equals(hora.toString())){
                if (extraer2.getMesaReservada().getCapacidad().equals(capacidad)){
                    mesasCapacidad.remove(extraer2.getMesaReservada());
                }
            }
        }
        return mesasCapacidad;
    }
    
    
    /**
     * Metodo que busca todas las reservas del dia actual
     * @return 
     */
    private static ArrayList<Reserva> buscarDia(){
        ArrayList<Reserva> reservasDelDia = new ArrayList<Reserva>();
        LocalDate diaActual = LocalDate.now();
        
        for(Reserva extRes : Reserva.getListaReservas()){
            if(extRes.dia.toString().equals(diaActual.toString())){
                reservasDelDia.add(extRes);
            }
        }
        return reservasDelDia;
    }
    
    /**
     * Metodo publico que llama la logica para buscar las reservas del dia actual
     * @return 
     */
    public static ArrayList<Reserva> reservasDelDia(){
        ArrayList<Reserva> listaDelDia = Reserva.buscarDia();
        listaReservas = Reserva.ordenarPorHora(listaDelDia);
        return listaReservas;
    }
    
    @Override
    public String toString() {
        return "RESERVA: " + idReserva + "\nCliente: " + clienteReserva.getNombre() + "\nDia: " + dia + "\nHora: " + hora + "\nMesa reservada: " + mesaReservada + "\nCantidadComensales: " + cantidadComensales + "\nEstado de asistencia: " + estadoAsist + "\nComentarios: " + comentarios;
    }
    
    /**
     * Metodo para enviar correos de recordatorio
     * @param listaReservasRecordatorio una lista filtrada por aquellas reservas que le faltan 2 dias para su turno
     */
    private static void enviarCorreo(ArrayList<Reserva> listaReservasRecordatorio){
        // Configuración del servidor de correo (SMTP)
        String host = "smtp.gmail.com"; // Servidor SMTP de Gmail
        String port = "587"; // Puerto de salida SMTP
        String username = "tallerpoo2023@gmail.com"; // Tu cuenta de correo de salida
        String password = "concordiaFCAD24"; // Contraseña de la cuenta
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        
        // Autenticación
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tallerpoo2023@gmail.com")); // De
        
            for (Reserva recordatorio : listaReservasRecordatorio){
                try {// Crear el mensaje
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recordatorio.getClienteReserva().getCorreo())); // Para
                    message.setSubject("Recordatorio de reserva");
                    message.setText("Querido cliente, mediante el siguiente correo se le recuerda"
                            + " su reserva para Delicias Gourmet el dia" + recordatorio.getDia().toString() + ". Esperamos contar con su presencia."
                            + "\n En caso de no poder asistir se le recuerda que debe cancelar su reserva con hasta 24 horas de anticipacion."
                            + "\nSaludos cordiales, Delicias Gourmet.");

                    // Enviar el mensaje
                    Transport.send(message);
                } catch (Exception e){
                    continue;
                }
            }
            
        } catch (Exception e) {
            System.out.println("\n  *****  \n  Error en inicio de sesion de correo  \n  *****  \n");
        }
    }
    
    /**
     * Metodo que se debe colocar al inicio del programa cuando inicia sesion el administrador para enviar recordatorios
     */
    public static void recordatorio(){
        ArrayList<Reserva> listaRes = Reserva.getListaReservas();
        ArrayList<Reserva> listaRecordatorio = new ArrayList<>();
        LocalDate fechaActual = LocalDate.now();
        
        for (Reserva ext : listaRes){
            LocalDate fechaRecordatorio = ext.getDia().minusDays(2);
            if(fechaActual.isEqual(fechaRecordatorio)){
                listaRecordatorio.add(ext);
            }
        }
        Reserva.enviarCorreo(listaRecordatorio);
    }
}