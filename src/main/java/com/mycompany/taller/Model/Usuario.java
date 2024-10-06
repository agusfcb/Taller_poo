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

    private void crearUsuario(String nombre, String telefono, String correo, String contrasenia) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasenia = contrasenia;

        this.idUsuario = UUID.randomUUID().toString();
    }

    public void registrarUsuario(String nombre, String telefono, String correo, String contrasenia) {
        crearUsuario(nombre, telefono, correo, contrasenia);
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


    public void Operation1() {

    }
}
