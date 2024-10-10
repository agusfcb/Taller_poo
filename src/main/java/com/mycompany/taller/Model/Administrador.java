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
