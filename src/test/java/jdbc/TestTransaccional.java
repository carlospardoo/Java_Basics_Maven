package jdbc;

import transacciones_jdbc.datos.Conexion;
import transacciones_jdbc.datos.PersonaDao;
import transacciones_jdbc.domain.Persona;

import java.sql.Connection;
import java.sql.SQLException;

public class TestTransaccional {

    public static void main(String[] args) {



        Connection conexion = null;

        try {

            conexion = Conexion.getConexion();

            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            PersonaDao personaJdbc = new PersonaDao(conexion);

            Persona cambioPersona = new Persona();
            cambioPersona.setIdPersona(4);
            cambioPersona.setNombre("Carlos Miguel");
            cambioPersona.setApellido("Esparza");
            cambioPersona.setEmail("cesparza@mail.com");
            cambioPersona.setTelefono("9876543210");
            personaJdbc.actualizar(cambioPersona);

            Persona nuevaPersona = new Persona(
                    "Dayanna",
//                    "Pardo12345678901234567890123456789012345678901234567890",
                    "Pardo",
                    "dpardo@mail.com",
                    "4561237890");

            personaJdbc.insertar(nuevaPersona);

            conexion.commit();
            System.out.println("Se ha hecho commit");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Entrada al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
