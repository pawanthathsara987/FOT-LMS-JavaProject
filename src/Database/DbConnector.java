package Database;

import java.sql.*;

public class DbConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/javalms";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Load the JDBC driver once
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        }
    }

    // Public method to get a new DB connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    // Simple SELECT for testing (for learning only — not recommended in production)
    public void select(String sql) {
        try (Connection conn = getConnection();
             Statement stmt = conn != null ? conn.createStatement() : null;
             ResultSet rs = stmt != null ? stmt.executeQuery(sql) : null) {

            if (rs != null) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rs.getString(i));
                        if (i < columnCount) System.out.print(", ");
                    }
                    System.out.println();
                }
            }

        } catch (SQLException e) {
            System.err.println("Select error: " + e.getMessage());
        }
    }

    // Generic update/insert/delete method
    public void executeUpdate(String sql) {
        try (Connection conn = getConnection();
             Statement stmt = conn != null ? conn.createStatement() : null) {

            if (stmt != null) {
                int rows = stmt.executeUpdate(sql);
                System.out.println("Query OK, " + rows + " row(s) affected.");
            }

        } catch (SQLException e) {
            System.err.println("Execution error: " + e.getMessage());
        }
    }
}
