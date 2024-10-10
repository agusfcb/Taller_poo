import com.mycompany.taller.Model.Reserva;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Administrador {

    private String idUsuario;
    private String rol;

    public Administrador(String idUsuario, String rol) {
        this.idUsuario = idUsuario;
        this.rol = rol;
    }

    public boolean cambiarRol(String idUsuario, String nuevoRol) {
        System.out.println("El rol de " + idUsuario + " ha sido cambiado a " + nuevoRol);
        return true;
    }

    public boolean editarRol(String idUsuario, String nuevoRol) {
        System.out.println("El rol de " + idUsuario + " ha sido actualizado a " + nuevoRol);
        return true;
    }

    public void actualizarSiguienteApertura(Date fechaFin, LocalDateTime nuevaApertura) {
        System.out.println("La siguiente apertura será en: " + nuevaApertura);
    }

    public void definirAforo(String nombre, Date fechaInicio, Date fechaFin, LocalDateTime horaInicio, LocalDateTime horaFin, ArrayList<Integer> numMesas) {
        System.out.println("Definiendo aforo para " + nombre);
    }

    public void actualizarApertura(LocalDateTime fechaInicio, LocalDateTime fechaHoraApertura) {
        System.out.println("Actualización de apertura para " + fechaInicio);
    }

    public void actualizarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
        System.out.println("Actualización de cierre para " + fechaInicio);
    }

    public void editarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
        System.out.println("Cierre editado para " + fechaInicio);
    }

    public void configurarFranjaHoraria(LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime horaInicio, LocalDateTime horaFin) {
        System.out.println("Franja horaria configurada desde " + horaInicio + " hasta " + horaFin);
    }

    public void addReserva(Reserva res) {
        
        System.out.println("Reserva añadida: " + res.toString());
    }

    public void mostrarReservas() {
        ArrayList<Reserva> listaReservas = Reserva.getListaReservas(); 
        if (listaReservas.isEmpty()) {
            System.out.println("No hay reservas en la agenda.");
            return;
        }
        System.out.println("Reservas en la agenda:");
        for (Reserva reserva : listaReservas) {
            System.out.println(reserva.toString());
        }
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
