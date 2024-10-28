/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taller.persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.taller.Model.Usuario;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 *Clase para persistencia de usuarios
 * @author Agustin y Juan
 */
public class PersistenciaUsuario {
    
    private static final String FILE_PATH = "src\\main\\java\\com\\mycompany\\taller\\persistencia\\archivosPersistencia\\Usuarios.json";
    private ArrayList<Usuario> listaUsers;
    private Gson gson;
    
    public PersistenciaUsuario(){
        this.gson = new Gson();
        this.cargarUsuarios();
    }
    
    private void cargarUsuarios(){
        try {
            String json = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            listaUsers = gson.fromJson(json, new TypeToken<ArrayList<Usuario>>(){}.getType());
            if (listaUsers == null) {
                listaUsers = new ArrayList<>();
                Usuario.setListaUsuarios(listaUsers);
            }
        } catch (IOException e) {
            listaUsers = new ArrayList<>();
            Usuario.setListaUsuarios(listaUsers);
        }
        
    }
    
    public void guardarUsuario(){
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(listaUsers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void agregarUser(Usuario user) {
        this.listaUsers.add(user);
        this.guardarUsuario();
    }

    // Leer usuarios
    public ArrayList<Usuario> getUsers() {
        return listaUsers;
    }

    // Actualizar usuario
    public void actualizarUsuario(String id, Usuario usuarioCambios) {
        for (int i = 0; i < listaUsers.size(); i++) {
            if (listaUsers.get(i).getIdUsuario().equals(id)) {
                listaUsers.set(i, usuarioCambios);
                guardarUsuario();
                break;
            }
        }
    }

    // Borrar usuario
    public void eliminarUsuario(String id) {
        listaUsers.removeIf(user -> user.getIdUsuario().equals(id));
        guardarUsuario();
    }
}



