package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCMysql2 {
    public static void main(String[] args) {
        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one";
        String user = "root";
        String password = "password";

        StringBuilder stringBuilder = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionUrl = stringBuilder.toString();

        try(Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("SUCESSO ao se connectar no MySQL");
        } catch (SQLException e) {
            System.out.println("FALHA ao se connectar no MySQL");
            e.printStackTrace();
        }

    }
}
