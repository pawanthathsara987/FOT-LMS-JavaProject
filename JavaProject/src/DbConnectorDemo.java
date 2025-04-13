import java.sql.Connection;

public class DbConnectorDemo {
    public static void main(String[] args) {
        DbConnector db = new DbConnector();
        db.getConnection();
        String sql = "select * from mark";
        db.select(sql);

//        String sql2 = "UPDATE course SET ctype = 'T' WHERE ccode = 'ICT1242'";
//        db.update(sql2);
    }
}
