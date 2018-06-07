package persistence.connection;

import org.davidmoten.rx.jdbc.ConnectionProvider;
import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.pool.Pool;

import javax.annotation.Nonnull;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionRxJDBC {
    private ConnectionProvider connectionProvider;
    private static Database database;
    private String dbUrl;
    private String userName;
    private String password;

    private ConnectionRxJDBC() {
        dbUrl = getUrlDB();
        userName = getUserName();
        password = getPassword();
        connectionProvider = new ConnectionProvider() {
            @Nonnull
            @Override
            public Connection get() {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(dbUrl, userName, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return connection;
            }

            @Override
            public void close() {
                if (connectionProvider != null) {
                    connectionProvider.close();
                }
            }
        };

        database = Database.fromBlocking(connectionProvider);
        //database = Database.from((Pool<Connection>) connectionProvider);
    }

    public static Database getConnection() {
        if (database == null) {
            new ConnectionRxJDBC();
        }
        return database;
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
