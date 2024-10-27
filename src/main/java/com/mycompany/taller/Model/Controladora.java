/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Agustin y Juan
 */
public class Controladora {
    private Usuario user;
    
    //METODO DE LA PERSISTENCIA QUE TRAIGA A TODOS LOS USUARIOS

    public Controladora() {
    }
    
    public Usuario getUser(){
        return this.user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
