package com.mycompany.taller.Model;

import java.time.LocalTime;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Agustin, Juan y Ana
 */
@Entity
public abstract class Usuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private String idUsuario;
    @Basic
    private String nombre;
    private String telefono;
    
    @Temporal(TemporalType.DATE)
    private LocalTime fechaCumpleanios;
    
    @Basic
    private String correo;
    private String contrasenia;
    private String genero;
    private String rol;
    
    private static final String[] roles = {"Administrador", "Maitre", "Mesero", "Recepcionista", "Usuario"};
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String telefono, LocalTime fechaCumple, String correo, String contrasenia, String genero, String rolU) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaCumpleanios = fechaCumple;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
        this.rol = rolU;
        this.idUsuario = UUID.randomUUID().toString();
    }
    
    public Usuario(String nombre, String telefono, LocalTime fechaCumple, String correo, String contrasenia, String genero, String rolU, String idUsuario) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaCumpleanios = fechaCumple;
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
    
    public void setFechaCumpleanios(LocalTime fechaCumpleanios) {
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

    public LocalTime getFechaCumpleanios() {
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
    
    public static String[] getRoles(){
        return roles;
    }
    
    @Override
    public String toString() {
        return "Usuario: " + "\nID Usuario: " + idUsuario + "\nNombre: " + nombre + "\nTelefono: " + telefono + "\nCorreo: " + correo + "\nContraseña: " + contrasenia + "\nGenero: " + genero + "\nRol: " + rol;
    }

}
