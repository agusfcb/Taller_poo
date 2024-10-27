package com.mycompany.taller.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author Agustin, Juan y Ana
 */
@Entity
@Table(name = "mi_tabla")
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long idUsuario;
    @Basic
    private String nombre;
    private String telefono;
    private LocalDate fechaCumpleanios;
    private String correo;
    private String contrasenia;
    private String genero;
    private String rol;
    
    @Transient
    private static final String[] roles = {"Administrador", "Maitre", "Mesero", "Recepcionista", "Usuario"};
    
    public Usuario() {
    }
    
    public Usuario(String nombre, String telefono, LocalDate fechaCumple, String correo, String contrasenia, String genero, String rolU) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaCumpleanios = fechaCumple;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
        this.rol = rolU;
    }
    
    public Usuario(String nombre, String telefono, LocalDate fechaCumple, String correo, String contrasenia, String genero, String rolU, long idUsuario) {
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
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
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

    public long getIdUsuario() {
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
