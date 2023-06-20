package dal;

import model.Order;
import model.OrderDetail;
import model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAO extends DBContext {

    private StorageDAO storageDAO = new StorageDAO();
    public void add(Order order, List<Storage> listStorage) {
        try {
            for (Storage s : listStorage) {
                storageDAO.update(s);
                String query = "insert into order_detail(`order`, storage) values (?, ?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setLong(1, order.getId());
                ps.setLong(2, s.getId());
                ps.execute();
            }
        } catch (SQLException e) {
            System.out.println("OrderDetailDAO-add: " + e.getMessage());
        }
    }
}
