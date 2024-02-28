package logica;

import persistencia.ControladoraPersistencia;

public class Controladora {
    
    ControladoraPersistencia persis = new ControladoraPersistencia();
    
    public void crearUsuario(String nombre_user, String contrasenia, String rol){
        
        Usuario user = new Usuario(nombre_user, contrasenia, rol);
        persis.crearUsuario(user);
        
    }
    
    
}
