package com.mycompany.taller.Model;

import java.util.*;

//hola
public class Usuario {

    private String nombre;
    private String telefono;
    private String correo;
    private String contrasenia;
    private String genero;
    private String idUsuario;


    public Usuario() {
        
    }

    private Usuario(String nombre, String telefono, String correo, String contrasenia) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.idUsuario = UUID.randomUUID().toString();
        this.genero = "Desconocido";
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
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getGenero() {
        return genero;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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


    private boolean validarUsuario(String correo, String contrasenia) {
        return this.correo.equals(correo) && this.contrasenia.equals(contrasenia);
    }

    public boolean iniciarSesion(String correo, String contrasenia) {
        return validarUsuario(correo, contrasenia);
    }

}
