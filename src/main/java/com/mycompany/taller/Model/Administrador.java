package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;



/**
 * Clase para representar al administrador y lo las funciones que puede realizar
 * @author Agustin y Juan
 */
public class Administrador extends Empleado{

    private String permisos = "Ver historial; Definir horario; Crear evento; Definir dias especiales; Ver reservas.";
    
    private ArrayList<Reserva> listaReservaActualizada = new ArrayList<>();
    private static ArrayList<Evento> listaEventos = new ArrayList<>();
    public ArrayList<Reserva> listaReservas;
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    
    /**
     * Constructor por defecto
     */
    public Administrador(){
        super();
        this.listaReservas = new ArrayList<>();
    }
    
    /**
     * Constructor parametrizado
     * @param name
     * @param tel
     * @param email
     * @param pass
     * @param genero 
     */
    public Administrador(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Administrador", genero);
        this.listaReservas = new ArrayList<>();
    }
    
    /**
     * Constructor para la persistencia
     * @param name
     * @param tel
     * @param email
     * @param pass
     * @param genero
     * @param idUs 
     */
    public Administrador(String name, String tel, LocalDate fechaCumple, String email, String pass, String genero, long idUs) {
        super(name, tel, fechaCumple, email, pass, "Administrador", genero, idUs);
        this.listaReservas = new ArrayList<>();
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    
    /**
     * Metodo para que los objetos credos por la persistencia sean agregados a la lista de empleados
     */
    public static void setListaEmpleado(ArrayList<Empleado> empleados){
        Administrador.listaEmpleados = empleados;
    }
    
    /**
     * 
     * @param idUsuario
     * @param nuevoRol
     * @return 
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
     * Metodo para cambiar el rol de un empleado
     * @param idUsuario
     * @param nuevoRol
     * @return 
     */
    public boolean editarRol(long idUsuario, String nuevoRol) {
        return this.cambiarRol(idUsuario, nuevoRol);
    }

    /**
     * 
     * @param horarios 
     */
    public void configurarFranjaHoraria(ArrayList<LocalTime> horarios) {
        Reserva.setHorarios(horarios);
    }
    
    
    /**
     * 
     * @param horaExt 
     */
    public void addHorario(LocalTime horaExt){
        Reserva.agregarHorario(horaExt);
    }
    
    /**
     * 
     * @param res 
     */
    public void addReserva(Reserva res) {
        this.listaReservas.add(res);
    }
    
    /**
     * 
     * @param empleado 
     */
    public static void addEmpleado(Empleado empleado){
        Administrador.listaEmpleados.add(empleado);    
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Reserva> getListaReservas() {
        return Reserva.getListaReservas();  
    }
    /**
     * 
     * @return 
     */
    public ArrayList<Reserva> getListaReservaActualizada() {
        return listaReservaActualizada;
    }
    
    /**
     * La lista de reservas se debe actualizar cuando lo solicite el Administrador (conectado a la persistencia)
     * @param listaReservaActualizada 
     */
    public void setListaReservaActualizada(ArrayList<Reserva> listaReservaActualizada) {
        this.listaReservaActualizada = listaReservaActualizada;
    }
    
    /**
     * 
     * @return lista de eventos
     */
    public static ArrayList<Evento> getListaEventos() {
        return Administrador.listaEventos;
    }

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
    
    public void crearMesa(String numero, Integer capacidad, String ubicacion){
        Mesa nuevaMesa = new Mesa(capacidad, ubicacion);
    }
    
    /**
     * Metodo para designar dias donde el restaurante cerrara antes
     * Precondicion: que no haya reservas en ese dia y horario
     * @param fecha
     * @param hora 
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
     * @param fechaEsp 
     */
    public void agregarDiaEspecial(LocalDate fechaEsp){
        Reserva.getDiasEspeciales().add(fechaEsp);
    }
    
    /**
     * Metodo para crear empleados
     * @param name nombre
     * @param tel telefono
     * @param fechaCumple fecha de cumpleaños
     * @param email email
     * @param pass contraseña
     * @param rol rol
     * @param gen genero
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
    
    public Empleado buscarEmpleado(long idEmpleado){
        for(Empleado extEmpleado : Administrador.getListaEmpleados()){
            if(String.valueOf(extEmpleado.getIdUsuario()).equals(String.valueOf(idEmpleado))){
                return extEmpleado;
            }
        }
        return null;
    }
    
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
     * @param evento 
     */
    public static void agregarEvento(Evento evento){
        Administrador.listaEventos.add(evento);    
    }
    
    /**
     * Metodo para eliminar un evento
     * @param evento 
     */
    public static void eliminarEvento(Evento evento){
        Administrador.listaEventos.remove(evento);
    }
    
    /**
     * Metodo para buscar un evento
     * @param nombre
     * @return 
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
     * @param evento
     * @return 
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
     * @param ubic ubicacion de las mesas
     */
    public void crearEventoUbic(String nombre, LocalDate dia, LocalTime horaInicio, LocalTime horaFin, String ubic){
        Evento nuevoEvento = new Evento(nombre, dia, horaInicio, horaFin, ubic, this);
        Administrador.agregarEvento(nuevoEvento);
    }
    
    /**
     * Metodo publico para crear eventos
     * @param nombre
     * @param fecha
     * @param horaI
     * @param horaF
     * @param numerosMesas 
     */
    public void crearEventoMesas(String nombre, LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas){
        Evento nuevoEvento = new Evento(nombre, fecha, horaI, horaF, numerosMesas, this);
        Administrador.agregarEvento(nuevoEvento);
    }
    
}
