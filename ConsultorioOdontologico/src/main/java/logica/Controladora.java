package logica;

import persistencia.ControladoraPersistencia;

public class Controladora {
    
    ControladoraPersistencia persis = new ControladoraPersistencia();
    
    public void crearUsuario(int id, String nombre_user, String contrasenia, String rol){
        
        Usuario user = new Usuario(id, nombre_user, contrasenia, rol);
        persis.crearUsuario(user);
        
    }
    
    
}
