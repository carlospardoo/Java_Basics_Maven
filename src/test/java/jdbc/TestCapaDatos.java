package jdbc;

import capa_datos.datos.Conexion;
import capa_datos.datos.IPersonaDao;
import capa_datos.datos.PersonaDaoJDBC;
import capa_datos.domain.PersonaDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestCapaDatos {

    public static void main(String[] args) {



        Connection conexion = null;

        try {

            conexion = Conexion.getConexion();

            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }

            IPersonaDao personaDao = new PersonaDaoJDBC(conexion);

            List<PersonaDto> personaDtoList = personaDao.seleccionar();
            for(PersonaDto persona : personaDtoList){
                System.out.println("PersonaDTO: " + persona);
            }

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
