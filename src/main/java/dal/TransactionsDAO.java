/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Supplier;
import model.Transactions;
import model.User;

/**
 *
 * @author hp
 */
public class TransactionsDAO extends DBContext {

    private static Transactions findTransactionsById(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Transactions> getListTransactionsForPage(int page) {
        List<Transactions> list = new ArrayList<>();
        try {
            String query = "select * from transactions limit 10 offset ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transactions(rs.getInt("id"), userDAO.getUserById(rs.getInt("user")),
                        rs.getInt("orderId"), rs.getDouble("money"), rs.getString("note"),
                        rs.getBoolean("type"), rs.getBoolean("status"), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy"))));
            }
        } catch (SQLException e) {
            System.out.println("getAllStorage: " + e.getMessage());
        }
        return list;

    }

    private UserDAO userDAO = new UserDAO();

    public ArrayList<Transactions> getListTransactions() {
        ArrayList<Transactions> list = new ArrayList<>();
        try {
            String strSelect = "select * from transactions ";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transactions(rs.getInt("id"), userDAO.getUserById(rs.getInt("user")),
                        rs.getInt("orderId"), rs.getDouble("money"), rs.getString("note"),
                        rs.getBoolean("type"), rs.getBoolean("status"), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy"))));
            }
        } catch (SQLException e) {
            System.out.println("getListTransactions: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Transactions> getListTransactionsById(int id) {
        ArrayList<Transactions> list = new ArrayList<>();
        try {
            String str = "SELECT * FROM transactions where user = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transactions(rs.getInt("id"), userDAO.getUserById(rs.getInt("user")),
                        rs.getInt("orderId"), rs.getDouble("money"), rs.getString("note"),
                        rs.getBoolean("type"), rs.getBoolean("status"), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy"))));
            }
        } catch (SQLException e) {
            System.out.println("getListTransactions: " + e.getMessage());
        }
        return list;
    }

    public Long getTotalTransactions() {
        try {
            String query = "select count(id) from transactions;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("getTotalTransactions: " + e.getMessage());
        }
        return (long) -1;
    }

    public List<Transactions> getListDistinctTransactions() {
        List<Transactions> list = new ArrayList<>();
        try {
            String query = "select distinct Id from transactions;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(TransactionsDAO.findTransactionsById(rs.getInt("productId")));
            }
        } catch (SQLException e) {
            System.err.println("getListDistinctProduct: " + e.getMessage());
        }
        return list;
    }

    public List<Transactions> getListTransactionsById(int id, int start, int end) {
        List<Transactions> list = new ArrayList<>();
        List<Transactions> list1 = new ArrayList<>();
        TransactionsDAO td = new TransactionsDAO();

        list = td.getListTransactionsById(id);
        for (int i = start; i < end; i++) {
            list1.add(list.get(i));
        }
        return list1;
    }

    public List<Transactions> searchTransactions(String type, String status, String search,int id) {
        List<Transactions> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM transactions "
                    + "where user=? " + (!type.isEmpty() ? "and type= ?" : "")
                    + (!status.isEmpty() ? " and status = ?" : "")
                    + (!search.isEmpty() ? " and note like ?" : "");

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int i = 2;
            if (!type.isEmpty()) {
                boolean x = false;
                if(type.equals("true")){
                    x = true;
                }
                ps.setBoolean(i,x);
                i++;
            }
            if (!status.isEmpty()) {
                boolean x =false;
                if(status.equals("true")){
                    x=true;
                }
                ps.setBoolean(i,x);
                i++;
            }
            if (!search.isEmpty()){
                ps.setString(i, "%"+search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transactions(rs.getInt("id"), userDAO.getUserById(rs.getInt("user")),
                        rs.getInt("orderId"), rs.getDouble("money"), rs.getString("note"),
                        rs.getBoolean("type"), rs.getBoolean("status"), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy"))));
            }
        } catch (SQLException e) {
            System.err.println("searchStorage: " + e.getMessage());
        }
        return list;
    }

    public List<Transactions> getDetailHistory(int id) {
        List<Transactions> list = new ArrayList<>();
        try {
            String str = "SELECT * FROM transactions where id = ?";
            PreparedStatement ps = connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transactions(rs.getInt("id"), userDAO.getUserById(rs.getInt("user")),
                        rs.getInt("orderId"), rs.getDouble("money"), rs.getString("note"),
                        rs.getBoolean("type"), rs.getBoolean("status"), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy"))));
            }
        } catch (SQLException e) {
            System.out.println("getDetailHistory: " + e.getMessage());
        }
        return list;
    }

}
