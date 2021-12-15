package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection implements RockConnection{
    private String username;
    private String password;
    private String url;

    private Connection connection;

    public PostgreConnection(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public PostgreConnection() {
    }


    @Override
    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url,username,password);
        return connection;
    }

    @Override
    public void close() throws SQLException {
        if(connection!=null) connection.close();
    }
}
