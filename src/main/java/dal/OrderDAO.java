package dal;

import model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO extends DBContext{

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
}
