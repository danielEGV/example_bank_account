package persistence.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionJDBC {
    private static Connection connection;
    private String dbUrl;
    private String userName;
    private String password;

    private ConnectionJDBC() {
        dbUrl = getUrlDB();
        userName = getUserName();
        password = getPassword();
        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            new ConnectionJDBC();
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed() || connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties_bank/database.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getUrlDB() {
        return getProperties().getProperty("urldb");
    }

    public String getUserName() {
        return getProperties().getProperty("userName");
    }

    public String getPassword() {
        return getProperties().getProperty("password");
    }
}
