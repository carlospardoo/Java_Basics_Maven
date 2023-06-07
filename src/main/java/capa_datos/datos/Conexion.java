package capa_datos.datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/test_udemy?useSSL=true&useTimezone=true&servetTimezone=UTC&allowPublicKeyRetrieval=true&user=root&password=123456789";

    private static Connection conexion;

    public static Connection getConexion(){
        try {
            if(Conexion.conexion == null || conexion.isClosed())
                Conexion.conexion = DriverManager.getConnection(Conexion.CONNECTION_STRING);
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
