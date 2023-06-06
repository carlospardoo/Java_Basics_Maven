package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMysqlJDBC {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_udemy?useSSL=true&useTimezone=true&servetTimezone=UTC&allowPublicKeyRetrieval=true&user=root&password=123456789";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Opcional en nuevas versiones

            Connection conexion = DriverManager.getConnection(url);

            Statement sentencia = conexion.createStatement();

            String sql = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";

            ResultSet resultado = sentencia.executeQuery(sql);

            while (resultado.next()){
                System.out.print("id_persona: "+resultado.getInt("id_persona"));
                System.out.print(" ");
                System.out.print("nombre: "+resultado.getString("nombre"));
                System.out.print(" ");
                System.out.print("apellido: "+resultado.getString("apellido"));
                System.out.print(" ");
                System.out.print("email: "+resultado.getString("email"));
                System.out.print(" ");
                System.out.print("telefono: "+resultado.getString("telefono"));
                System.out.print(" ");
                System.out.println("");
            }

            resultado.close();
            sentencia.close();
            conexion.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
