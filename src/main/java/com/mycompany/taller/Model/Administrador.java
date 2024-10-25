package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;


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
     * @param name
     * @param tel
     * @param email
     * @param pass
     * @param genero 
     */
    public Administrador(String name, String tel, String email, String pass, String genero) {
        super(name, tel, email, pass, "Administrador", genero);
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
    public Administrador(String name, String tel, String email, String pass, String genero, String idUs) {
        super(name, tel, email, pass, "Administrador", genero, idUs);
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
    private boolean cambiarRol(String idUsuario, String nuevoRol) {
        ArrayList<Empleado> empleados = Empleado.getListaEmpleados();
        for (Usuario extUsu : empleados){
            if(extUsu.getIdUsuario().equals(idUsuario)){
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
    public boolean editarRol(String idUsuario, String nuevoRol) {
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
        this.listaReservas.addLast(res);
    }
    
    /**
     * 
     * @param empleado 
     */
    public static void addEmpleado(Empleado empleado){
        Administrador.listaEmpleados.addLast(empleado);    
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
    
    /**
     * Metodo que agrega la reserva asociada a un evento
     * @param evento es un objeto del tipo evento
     */
    public static void addEvento(Evento evento){
        Administrador.listaEventos.add(evento);
    }
    
        
    /**
     * Metodo para designar dias donde el restaurante cerrara antes
     * Precondicion: que no haya reservas en ese dia y horario
     * @param fecha
     * @param hora 
     */
    public void cierreEspecial(LocalDate fecha, LocalTime hora){
        try {
            for(Mesa mesaExt : Mesa.getMesasTot()){
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
        Reserva.getDiasEspeciales().addLast(fechaEsp);
    }
    
    /**
     * Metodo para crear objetos empleados
     * @param name
     * @param tel
     * @param email
     * @param pass
     * @param rol
     * @param gen
     * @return 
     */
    public void crearEmpleado(String name, String tel, String email, String pass, String rol, String gen){
        switch(rol){
            case "Maitre":
                Maitre nuevoMaitre = new Maitre(name, tel, email, pass, gen);
                Administrador.addEmpleado(nuevoMaitre);
                break;
            case "Mesero":
                Mesero nuevoMesero = new Mesero(name, tel, email, pass, gen);
                Administrador.addEmpleado(nuevoMesero);
                break;
            case "Recepcionista":
                Recepcionista nuevoRecep = new Recepcionista(name, tel, email, pass, gen);
                Administrador.addEmpleado(nuevoRecep);
                break;
            case "Administrador":
                Administrador nuevoAdmin = new Administrador(name, tel, email, pass, gen);
                Administrador.addEmpleado(nuevoAdmin);
                break;
            default:
                break;
        }
    }
    
    public Empleado buscarEmpleado(String idEmpleado){
        for(Empleado extEmpleado : Administrador.getListaEmpleados()){
            if(extEmpleado.getIdUsuario().equals(idEmpleado)){
                return extEmpleado;
            }
        }
        return null;
    }
    
    public boolean eliminarEmpleado(String idEmpleado){
        for(Empleado extEmpleado : Administrador.getListaEmpleados()){
            if(extEmpleado.getIdUsuario().equals(idEmpleado)){
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
        Administrador.listaEventos.addLast(evento);    
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
     * Metodo para crear evento
     * @param dia
     * @param horaInicio
     * @param horaFin 
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
