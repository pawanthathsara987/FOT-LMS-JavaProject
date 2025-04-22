import java.sql.*;

public class DbConnector {

    private String url = "jdbc:mysql://localhost:3306/javalms";
    private String user = "root";
    private String password = "1234";
    private Connection conn = null;
    private Statement stmt = null;

    private void registerDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found" + e.getMessage());
        }
    }

    public Connection getConnection(){

        registerDriver();

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection error" + e.getMessage());
        }
        return conn;
    }

    public void select(String sql){

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){



                System.out.println(
                        rs.getString(1)+ ", " +
                                rs.getString(2) + ", " +
                                rs.getString(3) + ", " +
                                rs.getString(4) + ", " +
                                rs.getString(5) + ", " +
                                rs.getString(6) + ", " +
                                rs.getString(7) + ", " +
                                rs.getString(8) + ", " +
                                rs.getString(9));
            }
        } catch (SQLException e) {
            System.out.println("Statement error" + e.getMessage());
        }
    }

    public void insert(String sql){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Statement error" + e.getMessage());
        }
    }

    public void update(String sql){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Statement error" + e.getMessage());
        }
    }

    public void delete(String sql){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Statement error" + e.getMessage());
        }
    }
}
