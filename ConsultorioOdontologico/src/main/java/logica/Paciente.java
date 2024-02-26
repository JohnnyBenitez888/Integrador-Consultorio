package logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends Persona {

    private boolean tiene_OS;
    private String tipoSangre;
    @OneToOne
    private Responsable responsable;
    @OneToMany(mappedBy = "paciente")
    private List<Turno> turnosPac;

    public Paciente() {
    }

    public Paciente(boolean tiene_OS, String tipoSangre, Responsable responsable, List<Turno> turnos, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.tiene_OS = tiene_OS;
        this.tipoSangre = tipoSangre;
        this.responsable = responsable;
        this.turnosPac = turnos;
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

    public List<Turno> getTurnosPac() {
        return turnosPac;
    }

    public void setTurnosPac(List<Turno> turnosPac) {
        this.turnosPac = turnosPac;
    }

}
