package org.example.db;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String DB_PROPERTIES_FILE = "database.properties";

    public static Connection getConnection() throws SQLException {
        Properties properties = carregarPropriedadesBancoDeDados();
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        return DriverManager.getConnection(url, user, password);
    }

    private static Properties carregarPropriedadesBancoDeDados() throws SQLException {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
            if (inputStream == null) {
                throw new SQLException("Não foi possível encontrar o arquivo de propriedades do banco de dados.");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new SQLException("Não foi possível carregar o arquivo de propriedades do banco de dados.", e);
        }
        return properties;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
