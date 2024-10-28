/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Model;

import com.mycompany.taller.persistencia.ControladoraPersis;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase para controlar las relaciones entre logica y persistencia
 * @author Agustin y Juan
 */
public class Controladora {
    private Usuario user;
    private ControladoraPersis controlPersis;
    //METODO DE LA PERSISTENCIA QUE TRAIGA A TODOS LOS USUARIOS

    public Controladora() {
        user =  null;
        controlPersis = new ControladoraPersis(); 
    }
    
    public Usuario getUser(){
        return this.user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public ControladoraPersis getControlPersis() {
        return controlPersis;
    }
    
}
