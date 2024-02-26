package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private String nombre_user;
    private String contrasenia;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre_user, String contrasenia, String rol) {
        this.nombre_user = nombre_user;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(int id_user, String nombre_user, String contrasenia, String rol) {
        this.id_user = id_user;
        this.nombre_user = nombre_user;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNombre_user() {
        return nombre_user;
    }

    public void setNombre_user(String nombre_user) {
        this.nombre_user = nombre_user;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
