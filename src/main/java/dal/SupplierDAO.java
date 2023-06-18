/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Supplier;

/**
 *
 * @author hp
 */
public class SupplierDAO extends DBContext {

    public Supplier getSuppierById(int id) {
        UserDAO userDAO = new UserDAO();
        try {
            String strSelect = "select * from supplier where id =?";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Supplier(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("createdAt"),
                        rs.getTimestamp("deletedAt"), rs.getTimestamp("updatedAt"), rs.getBoolean("isDelete"),
                        rs.getString("image"),  userDAO.getUserById(rs.getInt("createdBy")),
                        userDAO.getUserById(rs.getInt("deletedBy")), userDAO.getUserById(rs.getInt("createdBy")));
            }
        } catch (SQLException e) {
            System.out.println("getSuppierById: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Supplier> getListSupplier() {
        ArrayList<Supplier> list = new ArrayList<>();
        try {
            String strSelect = "select image,isDelete,id from supplier ";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(3);
                String image = rs.getString(1);
                if(!rs.getBoolean(2)){
                    list.add(new Supplier(id, image));
                }
            }
        }catch (SQLException e) {
            System.out.println("getListSupplier: " + e.getMessage());
        }
        return list;
    }
}
