package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamtung
 */
public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:mysql://localhost:3306/sellphonecard";
            String username = "root";
            String password = "";
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connect succcess");
            System.out.println(connection.getCatalog());

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Lỗi SQL");
            System.out.println(ex);
        }

        
    }

    public static void main(String[] args) {
        DBContext dbContext = new DBContext();

    }
}