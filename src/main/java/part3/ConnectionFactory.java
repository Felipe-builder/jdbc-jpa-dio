package part3;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    //Construtor declarado como privado. Evitando assim criar instância da fábria.
    private ConnectionFactory() {throw new UnsupportedOperationException();}

    public static Connection getConnection() {

        //OBS: NÃO ESQUECER DE BAIXAR O DRIVER PARA O BANCO DE DADOS QUE IRÁ UTILIZAR! (Como Demonstrado na parte 1 do curso)

        // 1 - Deckarar objeto conexão (irá receber uma conexão após executar os passos abaixo)
        Connection connection = null;

        // 2 - Carregar arquivo de propriedades para pegar parâmetros necessários para se comunicar com o banco de dados
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {

            // 3 - Definir parâmetros para se connectar ao banco de dados MySQL
            Properties properties = new Properties();
            properties.load(inputStream);

            String driver = properties.getProperty("jdbc.driver");
            String dataBaseAddress = properties.getProperty("db.address");
            String dataBaseName = properties.getProperty("db.name");
            String user = properties.getProperty("db.user.login");
            String password = properties.getProperty("db.user.password");

            // 4 - Construção da string de conexão
            StringBuilder stringBuilder = new StringBuilder("jdbc:")
                    .append(driver).append("://")
                    .append(dataBaseAddress).append("/")
                    .append(dataBaseName);

            String connectionUrl = stringBuilder.toString(); // == "jdbc:mysql://localhost/digital_innovation_one
            try {
                connection = DriverManager.getConnection(connectionUrl, user, password);
            } catch (SQLException e) {
                System.out.println("Falha ao tentar criar conexão");
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println("Fala aoentar carregar arquivos de propriedades");
            e.printStackTrace();
        }
        return connection;
    }
}
