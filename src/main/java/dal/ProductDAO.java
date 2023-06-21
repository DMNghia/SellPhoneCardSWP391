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
import java.util.List;

import model.Product;

/**
 *
 * @author hp
 */
public class ProductDAO extends DBContext {
    private UserDAO userDAO = new UserDAO();
    private SupplierDAO supplierDAO = new SupplierDAO();

    public ArrayList<Product> getListPrice(int id) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String str = "select * from product where supplier = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"),
                        supplierDAO.getSuppierById(rs.getInt("supplier")),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")),
                        rs.getBoolean("isDelete"),rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")),
                        rs.getTimestamp("updatedAt"), userDAO.getUserById(rs.getInt("updatedBy") )));
            }
        } catch (SQLException e) {
            System.out.println("getListPrice: " + e.getMessage());
        }return list;
    }

    public Product findProductById(int id) {
        Product product = new Product();
        try {
            String str = "select * from product where id = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"),
                        supplierDAO.getSuppierById(rs.getInt("supplier")),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")),
                        rs.getBoolean("isDelete"),rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")),
                        rs.getTimestamp("updatedAt"), userDAO.getUserById(rs.getInt("updatedBy")));
            }
        } catch (SQLException e) {
            System.out.println("findProductById: " + e.getMessage());
        }
        return product;
    }


}
