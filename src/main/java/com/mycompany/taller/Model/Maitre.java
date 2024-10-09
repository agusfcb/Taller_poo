package com.mycompany.taller.Model;
import java.util.*;

/**
 * 
 */

//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
public class Maitre extends Empleado {
    // Esta es mi clase
    // Saludos desde PC Agustin
    public Maitre() {
        super();
    }
    public Maitre(ArrayList<Reserva> reservas, String rol, String permiso, String name, String tel, String email, String pass) {
        super(reservas, "Maitre", permiso, name, tel, email, pass);
    }
}