package jdbc;

import manejo_jdbc.datos.PersonaDao;
import manejo_jdbc.domain.Persona;

import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) {
        PersonaDao personaDao = new PersonaDao();

//        Persona personaNueva = new Persona("Felipa", "Aragon", "faragon@mail.com", "7539514682");
//
//        personaDao.insertar(personaNueva);

        Persona personaActualiza = new Persona(4, "Carlos", "Esparza", "cesparza@mail.com", "9876543210");

        personaDao.actualizar(personaActualiza);

//        List<Persona> personas1 = personaDao.seleccionar();
//
//        personas1.forEach( x -> System.out.println(x));
//
//        Persona personaEliminar = new Persona(6);
//        personaDao.borrar(personaEliminar);

        List<Persona> personas = personaDao.seleccionar();

        personas.forEach( x -> System.out.println(x));

        System.out.println("*** CONSULTA DE SP ***");

        Persona personaSp = new Persona(2);
        System.out.println(personaDao.callSpGetRegistro(personaSp));



    }
}
