package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.util.*;
import java.util.UUID;
/**
 * Clase para representar al usuarios y las funciones que puede realizar
 *
 * @author Agustin, Juan y Ana
 */

public class Usuario {

    private String idUsuario;

    private String nombre;
    private String telefono;
    private String fechaCumple;
    private transient LocalDate fechaCumpleanios;
    private String correo;
    private String contrasenia;
    private String genero;
    private String rol;
    

    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    private static final String[] roles = {"Administrador", "Maitre", "Mesero", "Recepcionista", "Usuario"};

    /**
     * Default constructor
     */
    public Usuario() {
    }

    /**
     * Constructor parametrizado
     * @param nombre nombre
     * @param telefono telefono
     * @param fechaCumple fecha de cumpleaños
     * @param correo correo
     * @param contrasenia contraseña
     * @param genero genero
     * @param rolU rol del usuario
     */
    public Usuario(String nombre, String telefono, String fechaCumple, String correo, String contrasenia, String genero, String rolU) {
        this.idUsuario = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaCumple = fechaCumple;
        this.fechaCumpleanios = LocalDate.parse(this.fechaCumple);
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
        this.rol = rolU;
    }

    /**
     * Constructor parametrizado
     * @param nombre nombre
     * @param telefono telefono
     * @param fechaCumple fecha de cumpleaños
     * @param correo correo
     * @param contrasenia contraseña
     * @param genero genero
     * @param rolU rol del usuario
     * @param idUsuario del usuario
     */
    public Usuario(String nombre, String telefono, String fechaCumple, String correo, String contrasenia, String genero, String rolU, String idUsuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaCumple = fechaCumple;
        this.fechaCumpleanios = LocalDate.parse(this.fechaCumple);
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
        this.rol = rolU;
        this.idUsuario = idUsuario;
        Usuario.listaUsuarios.add(this);
    }

    /**
     * Getter listaUsuarios
     * @return array de Usuario
     */
    public static ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * Setter listaUsuarios
     * @param listaUsuarios array de Usuario
     */
    public static void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        Usuario.listaUsuarios = listaUsuarios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaCumle() {
        return fechaCumple;
    }

    public void setFechaCumle(String fechaCumple) {
        this.setFechaCumpleanios(LocalDate.parse(fechaCumple));
        this.fechaCumple = fechaCumple;
    }

    
    
    public void setFechaCumpleanios(LocalDate fechaCumpleanios) {
        this.fechaCumpleanios = fechaCumpleanios;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaCumpleanios() {
        return fechaCumpleanios;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getGenero() {
        return genero;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public static String[] getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "Usuario: " + "\nID Usuario: " + idUsuario + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nCorreo: " + correo + "\nContraseña: " + contrasenia + "\nGenero: " + genero + "\nRol: " + rol;
    }

}
