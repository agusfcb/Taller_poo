package com.mycompany.taller.Model;

import java.util.*;

//hola
public abstract class Usuario {

    private String nombre;
    private String telefono;
    private String correo;
    private String contrasenia;
    private String genero;
    private String idUsuario;


    public Usuario() {
        
    }

    public Usuario(String nombre, String telefono, String correo, String contrasenia) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.idUsuario = UUID.randomUUID().toString();
        this.genero = "S/D";
    }
    public Usuario(String nombre, String telefono, String correo, String contrasenia, String genero) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.genero = genero;
    }
    
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    private void setCorreo(String correo) {
        this.correo = correo;
    }
    private void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    private void setGenero(String genero) {
        this.genero = genero;
    }
    private void setIdUsuario(String idUsuario) {
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

    public abstract void validarUsuario(String usuario, String contrasenia);

    public abstract void iniciarSesion(String correo, String contrasenia);
}
