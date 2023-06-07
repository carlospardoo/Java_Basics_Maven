package transacciones_jdbc.datos;



import transacciones_jdbc.domain.Persona;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDao {

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_SELECT_ONE = "{ call sp_Get_Una_Persona(?) }";
    private static final String SQL_INSERT = "INSERT INTO persona ( nombre, apellido, email, telefono ) values ( ?, ?, ?, ? )";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ? ";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ? ";

    private Connection conexionTransaccional;

    public PersonaDao() {

    }

    public PersonaDao(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    public List<Persona> seleccionar() throws SQLException{

        List<Persona> personas = new ArrayList<>();

        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        try {

            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional: manejo_jdbc.datos.Conexion.getConexion();

            statement = connection.prepareStatement(PersonaDao.SQL_SELECT);

            resultSet = statement.executeQuery();

            while ( resultSet.next() ){
                int idPersona = resultSet.getInt("id_persona");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");

                Persona persona = new Persona(idPersona, nombre, apellido, email, telefono);

                personas.add(persona);

            }


        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        finally {
            manejo_jdbc.datos.Conexion.close(resultSet);
            manejo_jdbc.datos.Conexion.close(statement);

            if(this.conexionTransaccional == null)
                manejo_jdbc.datos.Conexion.close(connection);
        }

        return personas;

    }

    public int insertar(Persona persona) throws SQLException{

        Connection connection = null;

        PreparedStatement statement = null;

        int registros = 0;

        try {

            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional: manejo_jdbc.datos.Conexion.getConexion();

            statement = connection.prepareStatement(PersonaDao.SQL_INSERT);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setString(3, persona.getEmail());
            statement.setString(4, persona.getTelefono());

            registros = statement.executeUpdate();

        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        finally {
            manejo_jdbc.datos.Conexion.close(statement);
            if(this.conexionTransaccional == null)
                manejo_jdbc.datos.Conexion.close(connection);
        }

        return registros;

    }

    public int actualizar(Persona persona) throws SQLException{

        Connection connection = null;

        PreparedStatement statement = null;

        int registros = 0;

        try {

            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional: manejo_jdbc.datos.Conexion.getConexion();

            statement = connection.prepareStatement(PersonaDao.SQL_UPDATE);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setString(3, persona.getEmail());
            statement.setString(4, persona.getTelefono());
            statement.setInt(5, persona.getIdPersona());

            registros = statement.executeUpdate();

        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        finally {
            manejo_jdbc.datos.Conexion.close(statement);

            if(this.conexionTransaccional == null)
                manejo_jdbc.datos.Conexion.close(connection);
        }

        return registros;

    }

    public int borrar(Persona persona) throws SQLException{

        Connection connection = null;

        PreparedStatement statement = null;

        int registros = 0;

        try {

            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional: manejo_jdbc.datos.Conexion.getConexion();

            statement = connection.prepareStatement(PersonaDao.SQL_DELETE);
            statement.setInt(1, persona.getIdPersona());

            registros = statement.executeUpdate();

        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        finally {
            manejo_jdbc.datos.Conexion.close(statement);
            if(this.conexionTransaccional == null)
                manejo_jdbc.datos.Conexion.close(connection);
        }

        return registros;

    }

    public Persona callSpGetRegistro(Persona persona) throws SQLException{

        Connection connection = null;

        CallableStatement statement = null;

        Persona retornado = null;

        ResultSet resultSet = null;

        try {

            connection = (this.conexionTransaccional != null) ? this.conexionTransaccional: manejo_jdbc.datos.Conexion.getConexion();

            statement = connection.prepareCall(PersonaDao.SQL_SELECT_ONE);

            statement.setInt (1, persona.getIdPersona());

//            statement.registerOutParameter(2, Types.INTEGER);
//            statement.registerOutParameter(3, Types.VARCHAR);
//            statement.registerOutParameter(4, Types.VARCHAR);
//            statement.registerOutParameter(5, Types.VARCHAR);

            resultSet = statement.executeQuery();

            resultSet.next();

            retornado = new Persona(
//                    statement.getInt("ID"),
//                    statement.getString("NOMBRE"),
//                    null,
//                    statement.getString("CORREO"),
//                    statement.getString("TELÉFONO")

                      resultSet.getInt("ID"),
                      resultSet.getString("NOMBRE"),
                      null,
                      resultSet.getString("CORREO"),
                      resultSet.getString("TELÉFONO")

            );

        }

//        catch (SQLException e) {
//            e.printStackTrace();
//        }
        finally {
            manejo_jdbc.datos.Conexion.close(statement);
            if(this.conexionTransaccional == null)
                Conexion.close(connection);
        }

        return retornado;
    }

}
