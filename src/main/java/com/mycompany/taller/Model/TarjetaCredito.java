package com.mycompany.taller.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class TarjetaCredito {
    
    private String nombre;
    private String emisor;
    private String numeroTarjeta;
    private String codVerificacion;
    private String multa;
    private Reserva reservaT;
    
    
    public TarjetaCredito(){}

    public TarjetaCredito(String nombre, String emisor, String numeroTarjeta, String codVerificacion) {
        this.nombre = nombre;
        this.emisor = emisor;
        this.numeroTarjeta = numeroTarjeta;
        this.codVerificacion = codVerificacion;
        this.multa = "$ 0";
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getCodVerificacion() {
        return codVerificacion;
    }

    public void setCodVerificacion(String codVerificacion) {
        this.codVerificacion = codVerificacion;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }
    
    public void agregarReser(Reserva res){
        this.reservaT = res;
    }

    @Override
    public String toString() {
        return "TarjetaCredito: " + "\nNombre: " + nombre + "\nEmisor: " + emisor + "\nNumero de tarjeta: " + numeroTarjeta;
    }
    
    //VALIDAR TARJETA ES UN METODO QUE DEBE COMPROBAR UN NOMBRE Y
    //QUE LA TARJETA TENGA 16 DIGITOS NUMERICOS XXXX XXXX XXXX XXXX
    
    //VALIDAR CODIGO DE VERIFICACION DEBE SER UN METODO QUE VALIDE 3 DIGITOS NUMERICOS
//Formato metodos:
    /**
     * @param String idReserva 
     * @return
     */
    
    
    /**
     * Este es un metodo que se colocara directamente luego del loggin de forma que tome la fecha cada dia
     * Al iniciar este metodo buscara todas las reservas del dia anterior que no tuvieron asistencia y les asignara una multa
     * @param ArrayList<Reserva> la lista de todas las reservas del dia anterior al dia actual
     * @return void
     */
    public void agregarMulta(){
        this.multa = "u$d 50";
    }
    
    /**
     * Valida que se introduzcan 16 digitos
     * @param numeroTarjeta
     * @param codigoVerif
     * @return 
     */
    public static boolean validarTarjeta(String numeroTarjeta, String codigoVerif){
        String regex1 = "\\d{16}";
        String regex2 = "\\d{3}";
        boolean controlTarjeta = false;
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(numeroTarjeta);

        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(codigoVerif);
        
        if (matcher1.find() && matcher2.find()){
            return true;
        } else {
            return false;
        }
    }
}