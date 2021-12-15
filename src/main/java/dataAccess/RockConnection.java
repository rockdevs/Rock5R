package dataAccess;

import java.sql.Connection;
import java.sql.SQLException;

public interface RockConnection {

    Connection getConnection() throws SQLException;
    void close() throws SQLException;

}
