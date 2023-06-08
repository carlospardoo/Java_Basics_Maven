package pool_conexiones.datos;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/test_udemy?useSSL=true&useTimezone=true&servetTimezone=UTC&allowPublicKeyRetrieval=true&user=root&password=123456789";

    private static Connection conexion;

    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();

        ds.setUrl(Conexion.CONNECTION_STRING);
//        ds.setUsername("");
//        ds.setPassword("");

        //Se define el tamaño del pool de conexiones
        ds.setInitialSize(5);

        return ds;
    }

    public static Connection getConexion(){
        try {
            if(Conexion.conexion == null || conexion.isClosed())
                Conexion.conexion = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Conexion.conexion;
    }

    public static void close(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement preparedStatement){
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
