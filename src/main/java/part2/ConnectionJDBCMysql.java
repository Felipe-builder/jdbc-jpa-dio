package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCMysql {
    public static void main(String[] args) throws SQLException {

        String urlConnection = "jdbc:mysql://localhost/digital_innovation_one";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(urlConnection,"root","password");
            System.out.println("SUCESSO!");
        } catch (SQLException e) {
            System.out.println("FALHA!");
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
