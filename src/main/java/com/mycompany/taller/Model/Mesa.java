package com.mycompany.taller.Model;


import java.util.*;
import java.lang.Integer;

/**
 * 
 */
//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
public class Mesa {
    private String numero;
    private Integer capacidad;
    private String ubicacion;
    private ArrayList<Reserva> reservasMesa;
    private static ArrayList<Mesa> mesasExistentes = new ArrayList<>();
    private static ArrayList<Integer> capacidadEstandar = new ArrayList<>(Arrays.asList(4, 8, 12));

    public Mesa() {
    }
    
    public Mesa(String numero, Integer capacidad, String ubicacion) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.reservasMesa = new ArrayList<Reserva>();
        Mesa.mesasExistentes.add(this);
    }

    public String getNumero() {
        return numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public static ArrayList<Mesa> getMesasTot(){
        return mesasExistentes;
    }
    
    public void agregarReserva(Reserva resv){
        this.reservasMesa.add(resv);
    }
    
    public boolean controlMesa(String num){
        for(Mesa mesaNum : Mesa.getMesasTot()){
            if(mesaNum.getNumero().equals(num)){
                return false;
            }
        }
        return true;
    }
    public void agregarMesa(String num, Integer cap, String ubi){
        if(controlMesa(num)){
            Mesa nuevaMesa = new Mesa(num, cap, ubi);
        }
    }
    
    public void removerMesa(String num){
        ArrayList<Mesa> mesasTotales = Mesa.getMesasTot();
        for(Mesa ext : mesasTotales){
            if(ext.getNumero().equals(num)){
                Mesa.mesasExistentes.remove(ext);
            }
        }
    }
    
    public void removerReserva(Reserva res){
        this.reservasMesa.remove(res);
    }
    
}