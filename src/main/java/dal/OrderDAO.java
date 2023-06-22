package dal;

import model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DBContext {

    private UserDAO userDAO = new UserDAO();

    private OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

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



    public List<Order> getAllOrder() {
        List<Order> listOrder = new ArrayList<>();
        try {
            String sql = "select * from `order`;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listOrder.add(new Order(rs.getLong("id"), userDAO.getUserById(rs.getInt("user")), rs.getString("status"), rs.getInt("totalAmount"),
                        rs.getBoolean("isDelete"), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")), orderDetailDAO.getListStorebyOrder(rs.getLong("id"))));
            }

        } catch (SQLException e) {
            System.out.println("getAllOrder" + e.getMessage());
        }
        return listOrder;
    }

}


