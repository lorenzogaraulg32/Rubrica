package com.rubrica.persistenza;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBService {

    private static final String CONFIG_FILE = "credenziali_database.properties";

    public Connection getConnection() {
        Properties properties = new Properties();

        Connection connection;

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);

            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            String dbName = properties.getProperty("db.name");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

            connection = DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            System.out.println("Errore nella lettura del file properties");
            return null;
        } catch (SQLException e) {
            System.out.println("Errore nella connessione con il database");
            return null;
        }

        return connection;
    }
}
