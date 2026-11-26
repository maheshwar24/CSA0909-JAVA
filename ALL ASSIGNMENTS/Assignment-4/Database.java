package assignment.task06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

final class Database {
    private Database() {}
    static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/csa09_assignment4");
        String user = System.getenv().getOrDefault("DB_USER", "root");
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");
        return DriverManager.getConnection(url, user, password);
    }
}
