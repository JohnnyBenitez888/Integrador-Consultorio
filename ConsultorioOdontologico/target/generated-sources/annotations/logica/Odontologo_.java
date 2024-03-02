package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Horario;
import logica.Turno;
import logica.Usuario;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-03-01T23:15:02")
@StaticMetamodel(Odontologo.class)
public class Odontologo_ extends Persona_ {

    public static volatile ListAttribute<Odontologo, Turno> turnosOdon;
    public static volatile SingularAttribute<Odontologo, Usuario> unUsuario;
    public static volatile SingularAttribute<Odontologo, String> especialidad;
    public static volatile SingularAttribute<Odontologo, Horario> unHorario;

}