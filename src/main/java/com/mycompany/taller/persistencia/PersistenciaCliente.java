
package com.mycompany.taller.persistencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.taller.Model.Cliente;
import com.mycompany.taller.Model.Reserva;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersistenciaCliente {
    private ArrayList<Cliente> clientes; // Lista de clientes
    private final String filePath = "clientes.json";
    private final Gson gson;

    public PersistenciaCliente() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.clientes = new ArrayList<>();
        loadClientes();
    }

    // Crear un nuevo cliente
    public void createCliente(Cliente cliente) {
        clientes.add(cliente);
        saveClientes();
    }

    // Leer todos los clientes
    public List<Cliente> readClientes() {
        return clientes;
    }

    // Actualizar un cliente por ID
    public boolean updateCliente(String id, Cliente nuevoCliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdUsuario().equals(id)) {
                clientes.set(i, nuevoCliente);
                saveClientes();
                return true;
            }
        }
        return false;
    }

    // Eliminar un cliente por ID
    public boolean deleteCliente(String id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdUsuario().equals(id)) {
                clientes.remove(i);
                saveClientes();
                return true;
            }
        }
        return false;
    }

    // Cargar clientes desde el archivo JSON
    private void loadClientes() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Cliente>>() {}.getType();
            clientes = gson.fromJson(reader, listType);
            if (clientes == null) {
                clientes = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de clientes, se inicia con una lista vacía.");
            clientes = new ArrayList<>();
        }
    }

    // Guardar clientes en el archivo JSON
    private void saveClientes() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
