/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.taller.Model.Mesa;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 *
 * @author Agustin y Juan
 */
public class PersistenciaMesa {
    private ArrayList<Mesa> mesas;
    private final String filePath = "mesas.json"; // Ruta del archivo JSON

    // Constructor
    public PersistenciaMesa() {
        mesas = new ArrayList<>();
        cargarMesas(); // Cargar mesas del archivo JSON
    }

    // Cargar mesas del archivo JSON
    private void cargarMesas() {
        try (Reader reader = new FileReader(filePath)) {
            mesas = new Gson().fromJson(reader, new TypeToken<ArrayList<Mesa>>(){}.getType());
            Mesa.setMesasExistentes(mesas);
        } catch (IOException e) {
            mesas = new ArrayList<>(); // Inicializa la lista vacía si el archivo no existe
            Mesa.setMesasExistentes(mesas);
        }
    }

    // Guardar mesas en el archivo JSON
    private void guardarMesas() {
        try (Writer writer = new FileWriter(filePath)) {
            new Gson().toJson(mesas, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Crear una nueva mesa
    public void agregarMesa(Mesa mesa) {
        mesas.add(mesa);
        cargarMesas();
    }

    // Leer todas las mesas
    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    // Actualizar una mesa
    public void actualizarMesa(String numero, Mesa updatedMesa) {
        for (int i = 0; i < mesas.size(); i++) {
            if (mesas.get(i).getIdMesa().equals(numero)) {
                mesas.set(i, updatedMesa);
                guardarMesas();
                return;
            }
        }
    }

    // Eliminar una mesa
    public void deleteMesa(String numero) {
        mesas.removeIf(mesa -> mesa.getIdMesa().equals(numero));
        guardarMesas();
    }
}
