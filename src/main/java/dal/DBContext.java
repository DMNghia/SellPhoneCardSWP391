package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection connection;


    public DBContext() {
        connection = DbContext1.getConnection();
//        try {
//            String user = "root";
//            String pass = "nghia";
//            String url = "jdbc:mysql://localhost:3306/sellphonecard";
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url, user, pass);
//        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println("Connect fail!" + ex.getMessage());
//        }
//        finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
    }
}
