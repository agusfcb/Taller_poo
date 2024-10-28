/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.persistencia;

/**
 *
 * @author Agus
 */
public class ControladoraPersis {
    
    public PersistenciaUsuario controlUsuario;
    public PersistenciaCliente controlClientes;
    public PersistenciaReserva controlReserva;
    public PersistenciaMesa controlMesa;
    
    public ControladoraPersis(){
        controlUsuario = new PersistenciaUsuario();
        controlClientes = new PersistenciaCliente();
        controlReserva = new PersistenciaReserva();
        controlMesa = new PersistenciaMesa();
    }

    public PersistenciaUsuario getControlUsuario() {
        return controlUsuario;
    }

    public PersistenciaCliente getControlClientes() {
        return controlClientes;
    }

    public PersistenciaReserva getControlReserva() {
        return controlReserva;
    }

    public PersistenciaMesa getControlMesa() {
        return controlMesa;
    }
    
}
