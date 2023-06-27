package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbContext1 {
    private static Connection connection;
    private static String user = "root";
    private static String pass = "nghia123";
    private static String url = "jdbc:mysql://sellphonecard.ctzurrwmifrd.ap-southeast-1.rds.amazonaws.com:3306/sellphonecard";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, pass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

}
