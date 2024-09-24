
import java.util.*;

/**
 * 
 */
public class Cliente extends Cliente {

    /**
     * Default constructor
     */
    public Cliente() {
    }

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private String telefono;

    /**
     * 
     */
    private String correo;

    /**
     * 
     */
    private String contraseña;

    /**
     * 
     */
    public ArrayList<Reserva> historialReservas;

    /**
     * @param Date fecha 
     * @param Date hora 
     * @return
     */
    private ArrayList<String> buscarMesaDisponible(void Date fecha, void Date hora) {
        // TODO implement here
        return null;
    }

    /**
     * @param Date fecha 
     * @param Date hora
     */
    public void verMesasDisponibles(void Date fecha, void Date hora) {
        // TODO implement here
    }

    /**
     * @param DateTime fecha 
     * @param DateTime hora 
     * @param Mesa mesa
     */
    public void crearReservar(void DateTime fecha, void DateTime hora, void Mesa mesa) {
        // TODO implement here
    }

    /**
     * @param idReserva 
     * @return
     */
    public boolean cacelarReserva(void idReserva) {
        // TODO implement here
        return false;
    }

    /**
     * @param DateTime fecha 
     * @param DateTime hora 
     * @param Mesa mesa 
     * @return
     */
    public boolean modificarReserva(void DateTime fecha, void DateTime hora, void Mesa mesa) {
        // TODO implement here
        return false;
    }

    /**
     * @param tuplaDeString 
     * @return
     */
    public boolean actualizarInformacion(void tuplaDeString) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public String verHistorial() {
        // TODO implement here
        return "";
    }

    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }

}