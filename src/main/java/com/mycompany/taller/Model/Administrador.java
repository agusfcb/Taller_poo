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
        return true;
    }

    public boolean editarRol(String idUsuario, String nuevoRol) {
        return true;
    }


    public void actualizarApertura(LocalDateTime fechaInicio, LocalDateTime fechaHoraApertura) {
       
    }

    public void actualizarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
        
    }

    public void editarCierre(LocalDateTime fechaInicio, LocalDateTime fechaHoraCierre) {
      
    }

    public void configurarFranjaHoraria(LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime horaInicio, LocalDateTime horaFin) {
     
    }

    public void addReserva(Reserva res) {
     
    }

    public ArrayList<Reserva> getListaReservas() {
        return Reserva.getListaReservas();  
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
