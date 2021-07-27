package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC1 {
    public static void main(String[] args) {

        String urlConnection = "jdbc:postgresql://localhost/catalogo";

        try ( Connection connection = DriverManager.getConnection(urlConnection, "postgres", "5432")){
            System.out.println("Sucesso!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
