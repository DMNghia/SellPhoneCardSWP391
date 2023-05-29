/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.mysql.cj.xdevapi.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author hp
 */
public class ProductDAO extends DBContext {

    

    public ArrayList<Product> getListPrice(int id) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String str = "select * from product where supplier = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                double price = rs.getDouble(4);
                list.add(new Product(price));
            }
        } catch (SQLException e) {
            System.out.println("getListPrice: " + e.getMessage());
        }return list;
    }
}
