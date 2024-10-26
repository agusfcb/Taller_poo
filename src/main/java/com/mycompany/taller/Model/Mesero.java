package com.mycompany.taller.Model;
import java.time.LocalTime;
import java.util.*;

/**
 * 
 */
public class Mesero extends Empleado {
    
    private String permisos = "Ver comentarios de las reservas que puede atender";

    public Mesero() {
        super();
    }
    public Mesero(String name, String tel, LocalTime fechaCumple, String email, String pass, String genero) {
        super(name, tel, fechaCumple, email, pass, "Mesero", genero);
    }
    public Mesero(String name, String tel, LocalTime fechaCumple, String email, String pass, String genero, String idUsuario){
        super(name, tel, fechaCumple, email, pass, "Maitre", genero, idUsuario);
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }
    
}