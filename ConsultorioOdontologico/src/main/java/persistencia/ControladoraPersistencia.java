package persistencia;

import logica.Usuario;

public class ControladoraPersistencia {
    
    HorarioJpaController horaJpa = new HorarioJpaController();
    OdontologoJpaController odoJpa = new OdontologoJpaController();
    PacienteJpaController paciJpa = new PacienteJpaController();
    ResponsableJpaController respJpa = new ResponsableJpaController();
    SecretarioJpaController secreJpa = new SecretarioJpaController();
    TurnoJpaController turnoJpa = new TurnoJpaController();
    UsuarioJpaController userJpa = new UsuarioJpaController();

    public void crearUsuario(Usuario user) {
        userJpa.create(user);
    }
    
}
