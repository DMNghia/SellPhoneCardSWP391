package dal;

import com.oracle.wls.shaded.org.apache.bcel.generic.Select;
import model.Order;
import model.Storage;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    private UserDAO userDAO = new UserDAO();

    private OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    private ProductDAO productDAO = new ProductDAO();

    public Order findOrderByTimeAndUser(int userId, String time) {

        try {
            String query = "select * from `order` where user = ? and createdAt = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, time);
            ps.executeQuery();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<Storage> listStorage = orderDetailDAO.getListStorageBySearchProduct(rs.getLong("id"), "%");
                return new Order(rs.getLong("id"), userDAO.getUserById(rs.getInt("user")), rs.getString("status"), rs.getInt("totalAmount"),
                        rs.getBoolean("isDelete"), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")), listStorage);
            }
        } catch (SQLException e) {
            System.err.println("findOrderByTimeAndUser: " + e.getMessage());
        }

        return null;
    }

    public void add(Order order) {
        try {
            String query = "insert into `order` (user, status, totalAmount, isDelete, createdAt, createdBy)\n" +
                    "value (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, order.getUser().getId());
            ps.setString(2, order.getStatus());
            ps.setInt(3, order.getTotalAmount());
            ps.setBoolean(4, order.isDelete());
            ps.setTimestamp(5, order.getCreatedAt());
            ps.setInt(6, order.getCreatedBy().getId());
            orderDetailDAO.add(order, order.getListStorage());
            ps.execute();
        } catch (SQLException e) {
            System.err.println("OrderDAO-add: " + e.getMessage());
        }
    }

    public long totalOrder(String status, String search) {
        long result = 1;
        try {
            String sql = "select * from `order` where status like ? and isDelete = false";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            result = 0;
            while (rs.next()) {
                List<Storage> listStorage = orderDetailDAO.getListStorageBySearchProduct(rs.getLong("id"), search);
                if (listStorage.size() > 0) {
                    result += 1;
                }
            }

        } catch (SQLException e) {
            System.out.println("getAllOrder " + e.getMessage());
        }

        return result;
    }

    public List<Order> getAllOrder(String status, String search, int page) {
        List<Order> listOrder = new ArrayList<>();
        try {
            String sql = "select * from `order` where status like ? and isDelete = false \n" +
                    "limit 10 offset ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<Storage> listStorage = orderDetailDAO.getListStorageBySearchProduct(rs.getLong("id"), search);
                if (listStorage.size() > 0) {
                    listOrder.add(new Order(rs.getLong("id"), userDAO.getUserById(rs.getInt("user")), rs.getString("status"), rs.getInt("totalAmount"),
                            rs.getBoolean("isDelete"), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                            userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")), listStorage));

                }
            }

        } catch (SQLException e) {
            System.out.println("getAllOrder " + e.getMessage());
        }
        return listOrder;
    }

    public Order getOrderById(long id) {
        try {
            String sql = "select * from `order` where  isDelete = false and id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(rs.getLong("id"), userDAO.getUserById(rs.getInt("user")), rs.getString("status"), rs.getInt("totalAmount"),
                        rs.getBoolean("isDelete"), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")));
            }

        } catch (SQLException e) {
            System.out.println("getOrderById" + e.getMessage());
        }
        return null;
    }

    public void delete(Order order) {
        try {
            String query = "update `order`\n" +
                    "set deletedAt = ?, deletedBy = ?, isDelete = ?\n" +
                    "where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, order.getDeletedAt());
            ps.setInt(2, order.getDeletedBy().getId());
            ps.setBoolean(3, order.isDelete());
            ps.setLong(4, order.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Delete order: " + e.getMessage());
        }
    }

    public List<Product> getListDistinctProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String query = "select distinct `order` from order_detail;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(productDAO.findProductById(rs.getInt("productId")));
            }
        } catch (SQLException e) {
            System.err.println("getListDistinctProduct: " + e.getMessage());
        }
        return list;
    }

    public Long getTotalOrder() {
        try {
            String query = "select count(id) from `order` where isDelete = false;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("getTotalOrder: " + e.getMessage());
        }
        return (long) -1;
    }

    public void deleteOrder(long oid) {
        try {
            String sql = "delete from `order` where id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, oid);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("deleteOrder: " + e.getMessage());
        }
    }

    public List<String> getDistinctStatus() {
        List<String> list = new ArrayList<>();
        try {
            String query = "select distinct status from `order`;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("status"));
            }
        } catch (SQLException e) {
            System.err.println("getDistinctStatus: " + e.getMessage());
        }
        return list;
    }
    public List<Order> getAllOrderWithUser(String status, String search, int page,int userId) {
        List<Order> listOrder = new ArrayList<>();
        try {
            String sql = "select * from `order` where status like ? and isDelete = false and user = ?  \n" +
                    "limit 10 offset ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, userId);
            ps.setInt(3, page);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<Storage> listStorage = orderDetailDAO.getListStorageBySearchProduct(rs.getLong("id"), search);
                if (listStorage.size() > 0) {
                    listOrder.add(new Order(rs.getLong("id"), userDAO.getUserById(rs.getInt("user")), rs.getString("status"), rs.getInt("totalAmount"),
                            rs.getBoolean("isDelete"), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                            userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")), listStorage));

                }
            }

        } catch (SQLException e) {
            System.out.println("getAllOrder " + e.getMessage());
        }
        return listOrder;
    }



}


