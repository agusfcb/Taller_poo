package com.mycompany.taller.Model;
import java.util.*;

/**
 * 
 */
public class Mesero extends Empleado {
    
    

//Formato metodos:
    /**
     * 
     */
    public Mesero() {
        super();
    }
    public Mesero(String name, String tel, String email, String pass, String genero) {
        super(name, tel, email, pass, "Mesero", genero);
    }
    public Mesero(String name, String tel, String email, String pass, String genero, String idUsuario){
        super(name, tel, email, pass, "Maitre", genero, idUsuario);
    }
}