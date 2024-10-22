/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Agus
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
    
    /**
     * Validacion de inicio de sesion
     * @param correo
     * @param password
     * @return 
     */
    public boolean validarUsuario(String correo, String password){
        //METODO DE QUE TRAIGA LA LISTA DE LOS USUARIOS
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        for (Usuario user : listaUsuarios) {
            if(correo.equals(user.getCorreo()) && password.equals(user.getContrasenia())){
                this.setUser(user);
                
                return true;
            }
        }
        return false;
    }
    
    public static void aperturaDelDia(Administrador admin){
        //Aca debe resolverse traer todas las reservas y crear los objetos Reserva
        
        //SETEAR LISTA DE USUARIOS
        for (int i = 0 ; presistenceUsuarios.size(); i++){
            admin.crearEmpleado(DATOS);
        }
        
        //SETEAR LAS RESERVAS CON LA PERSISTENCIA
        for (int h = 0; pesistenceReservas.size() ; i++){
            Reserva nuevaReserva = new Reserva(datos);
        }
        admin.setReservas(Reserva.getListaReservas());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int h = 0; pesistenceReservasEvento.size() ; i++){
            
            LocalDate reservaEvento = LocalDate.parse(datos321, formatter);
            
        }
        
        Reserva.ordenarFechaReservas();
    }
}
