package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;

public class Controladora {
    
    ControladoraPersistencia persis = new ControladoraPersistencia();
    
    public void crearUsuario(String nombre_user, String contrasenia, String rol){
        
        Usuario user = new Usuario(nombre_user, contrasenia, rol);
        persis.crearUsuario(user);
        
    }

    public List<Usuario> listarUsuarios() {
        return persis.listarUsuarios();
    }

    public void eliminarUsuario(int id) {
        persis.eliminarUsuario(id);
    }
    
    public void editarUsuario(Usuario user){
        persis.editarUsuario(user);
    }

    public Usuario traerUsuario(int id) {
        return persis.traerUsuario(id);
    }

    public boolean validarUser(String name, String contra) {
        List<Usuario> listaUsuarios = persis.listarUsuarios();

        for (Usuario u : listaUsuarios) {
            if (u.getNombre_user().equals(name) && u.getContrasenia().equals(contra)) {
                return true;
            }
        }
        return false;
    }
}
