package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static void main(String[] args) throws SQLException {

        String urlConnection = "jdbc:postgresql://localhost/catalogo";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(urlConnection, "postgres", "5432");
            System.out.println("SUCESSO");
        } catch (SQLException throwables) {
            System.out.println("FALHA");
            throwables.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
