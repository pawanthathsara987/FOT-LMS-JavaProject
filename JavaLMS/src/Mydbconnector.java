import java.sql.*;

public class Mydbconnector {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private static final String URL = "jdbc:mysql://localhost:3306/javalms?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public Mydbconnector() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database");

            stmt = con.createStatement();
            String sql = "SELECT * FROM department";
            rs = stmt.executeQuery(sql);

            // Iterate through the ResultSet
            while (rs.next()) {
                String depid = rs.getString("depid"); // Use column name as String, not char
                String depname = rs.getString("depname");
                System.out.println("Department ID: " + depid + ", Department Name: " + depname);
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Mydbconnector(); // Create an instance to run the constructor
    }
}