package com.mycompany.taller.Model;
import java.util.*;

/**
 * 
 */
public class Mesero extends Empleado {
    
    

//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
    public Mesero() {
        super();
    }
    public Mesero(ArrayList<Reserva> reservas, String rol, String permiso, String name, String tel, String email, String pass) {
        super(reservas, "Mesero", permiso, name, tel, email, pass);
    }
    
}