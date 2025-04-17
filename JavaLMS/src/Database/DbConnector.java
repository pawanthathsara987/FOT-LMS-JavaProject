package Database;

import java.sql.*;

public class DbConnector {
    private static String url = "jdbc:mysql://localhost:3306/javalms";
    private static String user = "root";
    private static String password = "1234";
    private static Connection connection = null;
    private static Statement stmt = null;

    public DbConnector() {
        getConnection();
    }

    public static Connection getConnection() {
        if (connection == null) {
            try{
                //load mysql jdbc driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                //establish connection
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection establish successfully");
            }catch(ClassNotFoundException e){
                System.err.println("JBDC Driver not found" + e.getMessage());
            }catch (SQLException e){
                System.err.println("Connection failed" + e.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try{
                connection.close();
                System.out.println("Database connection closed");
            }catch (SQLException e){
                System.err.println("Error Closing Connection" + e.getMessage());
            }
        }
    }
}
