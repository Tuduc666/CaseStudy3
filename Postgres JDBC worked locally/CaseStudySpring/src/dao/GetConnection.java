package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
// Heroku connection
//	public static Connection getConnection() throws ClassNotFoundException, IOException, SQLException {
//		String dbUrl = System.getenv("JDBC_DATABASE_URL");
//		return DriverManager.getConnection(dbUrl);
//	}
    private final static String url = "jdbc:postgresql://localhost/dvdrental";
    private final static String user = "postgres";
    private final static String password = "Tamduc1166";
    
    public static Connection Connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
}
