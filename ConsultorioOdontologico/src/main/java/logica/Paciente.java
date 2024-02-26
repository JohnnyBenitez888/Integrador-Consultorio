package logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Paciente extends Persona {

    private boolean tiene_OS;
    private String tipoSangre;
    private Responsable responsable;
    private List<Turno> turnos;

    public Paciente() {
    }

    public Paciente(boolean tiene_OS, String tipoSangre, Responsable responsable, List<Turno> turnos, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.tiene_OS = tiene_OS;
        this.tipoSangre = tipoSangre;
        this.responsable = responsable;
        this.turnos = turnos;
    }

    public boolean isTiene_OS() {
        return tiene_OS;
    }

    public void setTiene_OS(boolean tiene_OS) {
        this.tiene_OS = tiene_OS;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

}
