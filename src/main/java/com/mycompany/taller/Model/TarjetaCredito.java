package com.mycompany.taller.Model;

import java.util.*;

/**
 * 
 */
public class TarjetaCredito {
    
    private String nombre;
    private String emisor;
    private String numeroTarjeta;
    private String codVerificacion;
    private String multa;

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
}