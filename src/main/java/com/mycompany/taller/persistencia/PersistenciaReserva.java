package com.mycompany.taller.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.taller.Model.Reserva;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agustin y Juan
 */
public class PersistenciaReserva {

    private ArrayList<Reserva> reservas;
    private final String filePath = "reservas.json";
    private final Gson gson;

    public PersistenciaReserva() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.reservas = new ArrayList<>();
        loadReservas();
    }

    // Crear una nueva reserva
    public void createReserva(Reserva reserva) {
        reserva.setDiaString(reserva.getDia().toString());
        reserva.setHoraString(reserva.getHora().toString());
        reservas.add(reserva);
        saveReservas();
    }

    // Leer todas las reservas
    public List<Reserva> readReservas() {
        return reservas;
    }

    // Actualizar una reserva por ID
    public boolean updateReserva(String idReserva, Reserva nuevaReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIdReserva().equals(idReserva)) {
                nuevaReserva.setDiaString(nuevaReserva.getDia().toString());
                nuevaReserva.setHoraString(nuevaReserva.getHora().toString());
                reservas.set(i, nuevaReserva);
                saveReservas();
                return true;
            }
        }
        return false;
    }

    // Eliminar una reserva por ID
    public boolean deleteReserva(String idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIdReserva().equals(idReserva)) {
                reservas.remove(i);
                saveReservas();
                return true;
            }
        }
        return false;
    }

    // Cargar reservas desde el archivo JSON
    private void loadReservas() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Reserva>>() {}.getType();
            reservas = gson.fromJson(reader, listType);
            if (reservas == null) {
                reservas = new ArrayList<>();
            }
            // Convertir las cadenas de fecha y hora a LocalDate y LocalTime
            for (Reserva reserva : reservas) {
                reserva.setDia(LocalDate.parse(reserva.getDiaString()));
                reserva.setHora(LocalTime.parse(reserva.getHoraString()));
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo, se inicia con una lista vacía.");
            reservas = new ArrayList<>();
        }
    }

    // Guardar reservas en el archivo JSON
    private void saveReservas() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(reservas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
