package com.mycompany.taller.Model;

import java.util.*;

/**
 * 
 * @author Agustin, Juan y Ana
 */
public abstract class Usuario {
    
    private String idUsuario;
    private String nombre;
    private String telefono;
    private String correo;
    private String contrasenia;
    private String genero;
    private String rol;
    private static final String[] roles = {"Administrador", "Maitre", "Mesero", "Recepcionista", "Usuario"};
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String telefono, String correo, String contrasenia, String genero, String rolU) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
        this.rol = rolU;
        this.idUsuario = UUID.randomUUID().toString();
    }
    
    public Usuario(String nombre, String telefono, String correo, String contrasenia, String genero, String rolU, String idUsuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
        this.rol = rolU;
        this.idUsuario = idUsuario;
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

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
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
    
    public void cambiarNombre(String nombre) {
        setNombre(nombre);
    }

    public void cambiarTelefono(String telefono) {
        setTelefono(telefono);
    }

    public void cambiarCorreo(String correo) {
        setCorreo(correo);
    }

    public void cambiarContrasenia(String contrasenia) {
        setContrasenia(contrasenia);
    }
    public void cambiarGenero(String genero) {
        this.setGenero(genero);
    }
    public void cambiarIdUsuario(String idUsuario) {
        this.setIdUsuario(idUsuario);
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public static String[] getRoles(){
        return roles;
    }
    
    @Override
    public String toString() {
        return "Usuario: " + "\nID Usuario: " + idUsuario + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nCorreo: " + correo + "\nContraseña: " + contrasenia + "\nGenero: " + genero + "\nRol: " + rol;
    }

}
