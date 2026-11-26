package Task05;
import java.sql.*;
public final class Db { private Db(){} public static Connection connect()throws SQLException {String url=System.getenv().getOrDefault("DB_URL","jdbc:mysql://localhost:3306/transport");String user=System.getenv().getOrDefault("DB_USER","root");String pass=System.getenv().getOrDefault("DB_PASSWORD","");return DriverManager.getConnection(url,user,pass);} }
