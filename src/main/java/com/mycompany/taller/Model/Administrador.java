package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrador extends Empleado{

    private String permisos = "Ver historial; Definir horario; Crear evento; Definir dias especiales; Ver reservas.";
    private ArrayList<Reserva> listaReservaActualizada = new ArrayList<>();
    private static ArrayList<Evento> listaEventos = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    
    
    public Administrador(String name, String tel, String email, String pass, String genero) {
        super(name, tel, email, pass, "Administrador", genero);
    }
    
    
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
     * 
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
     * Metodo para designar dias donde el restaurante cerrara antes
     * Precondicion: que no haya reservas en ese dia y horario
     * @param fecha
     * @param hora 
     */
    public void cierreEspecial(LocalDate fecha, LocalTime hora){
        try {
            for(Mesa mesaExt : Mesa.getMesasTot()){
                Reserva newReserva = new Reserva(fecha, hora, mesaExt, this);
            }
        }catch (Exception e) {
            System.out.println("\n Error al definir cierre anticipado \n");
        }
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
    public static ArrayList<Reserva> getListaEventos() {
        return Administrador.getListaEventos();
    }
    
    /**
     * Metodo que agrega la reserva asociada a un evento
     * @param evento es un objeto del tipo evento
     */
    public static void addEvento(Evento evento){
        Administrador.listaEventos.add(evento);
    }
    
    /**
     * Metodo para guardar dias especiales, util para ver horarios especiales
     * @param fechaEsp 
     */
    public void designarHorarioEspecial(LocalDate fechaEsp){
        Reserva.getDiasEspeciales().add(fechaEsp);
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
                Administrador.listaEmpleados.addLast(nuevoMaitre);
                break;
            case "Mesero":
                Mesero nuevoMesero = new Mesero(name, tel, email, pass, gen);
                Administrador.listaEmpleados.addLast(nuevoMesero);
                break;
            case "Recepcionista":
                Recepcionista nuevoRecep = new Recepcionista(name, tel, email, pass, gen);
                Administrador.listaEmpleados.addLast(nuevoRecep);
                break;
            case "Administrador":
                Administrador nuevoAdmin = new Administrador(name, tel, email, pass, gen);
                Administrador.listaEmpleados.addLast(nuevoAdmin);
                break;
            default:
                break;
        }
    }

    /**
     * Metodo para que los objetos credos por la persistencia sean agregados a la lista de empleados
     */
    public static void agregarEmpleado(Empleado emplea){
        Administrador.listaEmpleados.addLast(emplea);
    }
    
    /**
     * Metodo para crear evento
     * @param dia
     * @param horaInicio
     * @param horaFin 
     */
    public void crearEventoUbic(LocalDate dia, LocalTime horaInicio, LocalTime horaFin, String ubic){
        Evento nuevoEvento = new Evento(dia, horaInicio, horaFin, ubic, this);
    }
    
    
    public void crearEventoMesas(LocalDate fecha, LocalTime horaI, LocalTime horaF, ArrayList<String> numerosMesas){
        Evento nuevoEvento = new Evento(fecha, horaI, horaF, numerosMesas, this);
    }
     
}
