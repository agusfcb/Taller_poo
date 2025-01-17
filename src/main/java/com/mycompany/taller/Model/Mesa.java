package com.mycompany.taller.Model;


import java.util.*;
import java.lang.Integer;

/**
 * Clase para representar a las mesas y las funciones asociadas
 * @author Agustin y Juan
 */

public class Mesa {
    
    private String idMesa;

    private Integer capacidad;
    private String ubicacion;
    
    private transient ArrayList<Reserva> reservasMesa;

    private static final ArrayList<String> ubicacionesDisponibles = new ArrayList<>(Arrays.asList("Interior A", "Interior B", "Interior C", "Patio A", "Patio B"));

    private static ArrayList<Mesa> mesasExistentes = new ArrayList<>();

    private static ArrayList<Integer> capacidadEstandar = new ArrayList<>(Arrays.asList(4, 8, 12));

    public Mesa() {
    }
    
    public Mesa(Integer capacidad, String ubicacion) {
        this.idMesa = String.valueOf(Mesa.getMesasExistentes().size() + 1);
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.reservasMesa = new ArrayList<Reserva>();
        Mesa.mesasExistentes.add(this);
    }
    
    public Mesa(String numMesa, Integer capacidad, String ubicacion) {
        this.idMesa = numMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.reservasMesa = new ArrayList<Reserva>();
        Mesa.mesasExistentes.add(this);
    }

    public String getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }
    

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public ArrayList<Reserva> getReservasMesa() {
        return reservasMesa;
    }

    public void setReservasMesa(ArrayList<Reserva> reservasMesa) {
        this.reservasMesa = reservasMesa;
    }

    public static ArrayList<Mesa> getMesasExistentes() {
        return Mesa.mesasExistentes;
    }

    public static void setMesasExistentes(ArrayList<Mesa> mesasExistentes) {
        Mesa.mesasExistentes = mesasExistentes;
    }

    public static ArrayList<Integer> getCapacidadEstandar() {
        return capacidadEstandar;
    }

    public static void setCapacidadEstandar(ArrayList<Integer> capacidadEstandar) {
        Mesa.capacidadEstandar = capacidadEstandar;
    }

    public static ArrayList<String> getUbicacionesDisponibles() {
        return ubicacionesDisponibles;
    }
    
    public void agregarReserva(Reserva resv){
        this.reservasMesa.add(resv);
    }

    public void removerMesa(String num){
        ArrayList<Mesa> mesasTotales = Mesa.getMesasExistentes();
        for(Mesa ext : mesasTotales){
            if(ext.getIdMesa().equals(num)){
                Mesa.mesasExistentes.remove(ext);
            }
        }
    }
    
    public void removerReserva(Reserva res){
        this.reservasMesa.remove(res);
    }
}
