package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
public class Administrador extends Empleado{

    private String permisos = "Ver historial; Definir horario; Crear evento; Definir dias especiales; Ver reservas.";
    
    private ArrayList<Reserva> listaReservaActualizada = new ArrayList<>();
    private static ArrayList<Evento> listaEventos = new ArrayList<>();
    public ArrayList<Reserva> listaReservas = new ArrayList<>();
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    
    /**
     * Constructor por defecto
     */
    public Administrador(){
        super();
    }
    
    /**
     * Constructor parametrizado
     * @param name Nombre
     * @param tel Telefono
     * @param fechaCumple Fecha de cumpleanios
     * @param email Correo electronico
     * @param pass Contrasenia
     * @param genero Genero
     */
    public Administrador(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Administrador", genero);
    }
    
    /**
     * Constructor parametrizado
     * @param name Nombre
     * @param tel Telefono
     * @param fechaCumple Fecha de cumpleanios
     * @param email Correo electronico
     * @param pass Contrasenia
     * @param genero Genero
     * @param idUs ID del usuario
     */
    public Administrador(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero, long idUs) {
        super(name, tel, fechaCumple, email, pass, "Administrador", genero, idUs);
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    

    /**
     * Metodo para setear la lista de empleados con una lista pasada por parametro
     * @param empleados Lista de todos los empleados
     */
    public static void setListaEmpleado(ArrayList<Empleado> empleados){
        Administrador.listaEmpleados = empleados;
    }
    
    /**
     * Metodo privado para editar el rol de un empleado
     * @param idUsuario ID del usuario
     * @param nuevoRol Nuevo rol que se le asigna
     * @return true si se realizo el cambio, false si no se pudo realizar el cambio
     */
    private boolean cambiarRol(long idUsuario, String nuevoRol) {
        ArrayList<Empleado> empleados = Empleado.getListaEmpleados();
        for (Usuario extUsu : empleados){
            if(String.valueOf(extUsu.getIdUsuario()).equals(String.valueOf(idUsuario))){
                extUsu.setRol(nuevoRol);
                return true;
            }
        }
        return false;
    }
    /**
     * Metodopublico para cambiar el rol de un empleado
     * @param idUsuario ID del usuario
     * @param nuevoRol Nuevo rol que se le asigna
     * @return true si se realizo el cambio, false si no se pudo realizar el cambio
     */
    public boolean editarRol(long idUsuario, String nuevoRol) {
        return this.cambiarRol(idUsuario, nuevoRol);
    }

    /**
     * Metodo para pasar un array con los horarios disponibles para reservar
     * @param horarios array con horas LocalTime
     */
    public void configurarFranjaHoraria(ArrayList<LocalTime> horarios) {
        Reserva.setHorarios(horarios);
    }
    
    /**
     * Metodo para agregar un horario extra a los disponibles
     * @param horaExt hora formato LocalTime
     */
    public void addHorario(LocalTime horaExt){
        Reserva.agregarHorario(horaExt);
    }
    
    /**
     * Metodo para agregar las reservas creadas para eventos
     * @param res Reserva
     */
    public void addReserva(Reserva res) {
        this.listaReservas.add(res);
    }
    
    /**
     * Metodo para agregar los empleados creados a la lista de empleados
     * @param empleado Empleado
     */
    public static void addEmpleado(Empleado empleado){
        Administrador.listaEmpleados.add(empleado);    
    }
    
    /**
     * Metodo que devuelve la lista de todas las reservas del restaurante
     * @return Array de objetos Reserva
     */
    public ArrayList<Reserva> getListaReservas() {
        return Reserva.getListaReservas();  
    }
    
    /**
     * Metodo pendiente, para ver un array de las reservas en cualquier momento del dia
     * @return devuelve un array de objetos Reserva
     */
    public ArrayList<Reserva> getListaReservaActualizada() {
        return listaReservaActualizada;
    }
    
    /**
     * La lista de reservas se debe actualizar cuando lo solicite el Administrador (conectado a la persistencia)
     * @param listaReservaActualizada array con reservas luego de un proceso de actualizacion
     */
    public void setListaReservaActualizada(ArrayList<Reserva> listaReservaActualizada) {
        this.listaReservaActualizada = listaReservaActualizada;
    }
    
    /**
     * Metodo que devuelve la lsita de todos los eventos creados
     * @return lista de objetos Evento
     */
    public static ArrayList<Evento> getListaEventos() {
        return Administrador.listaEventos;
    }

    /**
     * Metodo que devuelve la lista de todos los empleados
     * @return array de objetos Empleado
     */
    public static ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public static void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
        Administrador.listaEmpleados = listaEmpleados;
    }
    
    
    /**
     * Metodo que agrega la reserva asociada a un evento
     * @param evento es un objeto del tipo evento
     */
    public static void addEvento(Evento evento){
        Administrador.listaEventos.add(evento);
    }
    
    /**
     * Metodo para crear objetos que representen las mesas
     * @param numero numero de mesa
     * @param capacidad capacidad de la mesa
     * @param ubicacion ubicacion de la mesa
     */
    public void crearMesa(String numero, Integer capacidad, String ubicacion){
        Mesa nuevaMesa = new Mesa(capacidad, ubicacion);
    }
    
    /**
     * Metodo para designar dias donde el restaurante cerrara antes
     * Precondicion: que no haya reservas en ese dia y horario
     * @param fecha fecha del dia con cierre anticipado
     * @param hora hora donde se marcaran todas las mesas como ocupadas para no ser reservadas
     */
    public void cierreEspecial(LocalDate fecha, LocalTime hora){
        try {
            for(Mesa mesaExt : Mesa.getMesasExistentes()){
                Reserva nuevaReserva = new Reserva(fecha, hora, mesaExt, this);
            }
        }catch (Exception e) {
            System.out.println("\n Error al definir cierre anticipado \n");
        }
    }
    
    /**
     * Metodo para guardar dias especiales, util para ver horarios especiales
     * @param fechaEsp fecha especial para acceder a horarios especiales
     */
    public void agregarDiaEspecial(LocalDate fechaEsp){
        Reserva.getDiasEspeciales().add(fechaEsp);
    }
    
    /**
     * Metodo para crear objetos empleados
     * @param name nombre del empleado
     * @param tel telefono del empleado
     * @param email email del empleado
     * @param pass contrasenia para el empleado
     * @param rol rol del empleado
     * @param gen genero del empleado
     */
    public void crearEmpleado(String name, String tel, LocalDate fechaCumple, String email, String pass, String rol, String gen){
        switch(rol){
            case "Maitre":
                Maitre nuevoMaitre = new Maitre(name, tel, fechaCumple, email, pass, gen);
                Administrador.addEmpleado(nuevoMaitre);
                break;
            case "Mesero":
                Mesero nuevoMesero = new Mesero(name, tel, fechaCumple, email, pass, gen);
                Administrador.addEmpleado(nuevoMesero);
                break;
            case "Recepcionista":
                Recepcionista nuevoRecep = new Recepcionista(name, tel, fechaCumple, email, pass, gen);
                Administrador.addEmpleado(nuevoRecep);
                break;
            case "Administrador":
                Administrador nuevoAdmin = new Administrador(name, tel, fechaCumple, email, pass, gen);
                Administrador.addEmpleado(nuevoAdmin);
                break;
            default:
                break;
        }
    }
    
    /**
     * Metodo para buscar en la lista de empleados uno en particular
     * @param idEmpleado id del empleado
     * @return objeto del tipo Empleado
     */
    public Empleado buscarEmpleado(long idEmpleado){
        for(Empleado extEmpleado : Administrador.getListaEmpleados()){
            if(String.valueOf(extEmpleado.getIdUsuario()).equals(String.valueOf(idEmpleado))){
                return extEmpleado;
            }
        }
        return null;
    }
    
    /**
     * Metodo para remover un empleado de la lista de empleados del restaurante
     * @param idEmpleado id del empleado
     * @return true si se encontro y removio al empleado, false si no se encontro al empleado
     */
    public boolean eliminarEmpleado(long idEmpleado){
        for(Empleado extEmpleado : Administrador.getListaEmpleados()){
            if(String.valueOf(extEmpleado.getIdUsuario()).equals(String.valueOf(idEmpleado))){
                Administrador.getListaEmpleados().remove(extEmpleado);
                return true;
            }
        }
        return false;
    
    }

    
    // METODO BASICOS PARA GESTIONAR EVENTOS
    
    /**
     * Metodo que agrega a la lista de eventos cada evento creado
     * @param evento objeto de tipo Evento
     */
    public static void agregarEvento(Evento evento){
        Administrador.listaEventos.add(evento);    
    }
    
    /**
     * Metodo estatico para eliminar un evento de la lista de eventos ya que la lista es estatica
     * @param evento objeto de tipo evento
     */
    public static void eliminarEvento(Evento evento){
        Administrador.listaEventos.remove(evento);
    }
    
    /**
     * Metodo para buscar un evento por nombre
     * @param nombre nombre del evento
     * @return devuelve un objeto de tipo Evento si se encuentra en la lista de eventos
     */
    public Evento buscarEvento(String nombre){
        for (Evento extEv : Administrador.getListaEventos()){
            if(extEv.getNombreEvento().equals(nombre)){
                return extEv;           
            }
        }
        return null;
    }
    
    /**
     * Meotodo publico para cancelar eventos
     * @param evento objeto del tipo Evento
     * @return true si se encontro y elimino el evento, false si no se pudo eliminar
     */
    public boolean cancelarEvento(Evento evento){
        try {
            evento.eliminarEvento();
            Administrador.eliminarEvento(evento);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    
    //OPCIONES DE CREACION DE EVENTOS, POR UBICACIONES COMPLETAS O POR LISTA DE MESAS
    
    /**
     * Metodo para crear evento por ubicacion completa
     * @param dia Fecha del evento
     * @param horaInicio hora del primer turno
     * @param horaFin hora del ultimo turno
     */
    public void crearEventoUbic(String nombre, LocalDate dia, LocalTime horaInicio, LocalTime horaFin, String ubic){
        Evento nuevoEvento = new Evento(nombre, dia, horaInicio, horaFin, ubic, this);
        Administrador.agregarEvento(nuevoEvento);
    }
    
    /**
     * Metodo publico para crear evento por mesas
     * @param nombre nombre del evento
     * @param fecha fecha del evento
     * @param horaI hora del primer turno
     * @param horaF hora del ultimo turno
     * @param numerosMesas array de las mesas elegidas
     */
    public void crearEventoMesas(String nombre, LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas){
        Evento nuevoEvento = new Evento(nombre, fecha, horaI, horaF, numerosMesas, this);
        Administrador.agregarEvento(nuevoEvento);
    }
    
}
