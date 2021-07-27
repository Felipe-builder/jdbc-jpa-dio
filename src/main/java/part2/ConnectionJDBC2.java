package part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC2 {
    public static void main(String[] args) {
        //Definir paramentros para se connectar ao banco de dados
        String driver = "postgresql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "catalogo";
        String user = "felipe_b";
        String password = "123";

        //Construção da String de conexão
        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionURL = sb.toString(); //sb.toString() == "jdbc://postgresql/localhost/catalogo"

        //Carregar a classe especifica de implementação do driver na memória da JVM(A partir da versão JDBC 4 não é mais necessário)
        //Class.forName("com.postgresql.jdbc.Driver");

        // Agora Cria a conexão usando o DriverManager, passando como parâmetros a string de conexão, usuario e senha do usuario
        try (Connection connection = DriverManager.getConnection(connectionURL, user, password)){
            System.out.println("Sucesso ao conectar ao banco PostgreSQL");
        } catch (SQLException throwables) {
            System.out.println("Falha ao conectar ao banco PostgreSQL");
            throwables.printStackTrace();
        }
    }
}
