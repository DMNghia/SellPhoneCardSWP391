package dal;

import model.Order;
import model.Product;
import model.Storage;
import model.User;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDAO extends DBContext {
    private UserDAO userDAO = new UserDAO();

    private OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//


    public List<Order> getAllOrder(){
        List<Order> listOrder = new ArrayList<>();
        try{
            String sql = "select * from `order`;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listOrder.add(new Order(rs.getLong("id"),userDAO.getUserById(rs.getInt("user")),rs.getString("status"),rs.getInt("totalAmount"),
                rs.getBoolean("isDelete") ,rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                userDAO.getUserById(rs.getInt("updatedBy")),rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")) , orderDetailDAO.getListStorebyOrder(rs.getLong("id"))));
            }

        } catch (SQLException e) {
            System.out.println("getAllOrder" + e.getMessage());
        }
        return listOrder;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> list;
        list = orderDAO.getAllOrder();
        for (Order o : list) {
//            System.out.println(o.getListStorage().size());
            for (int i = 0; i < o.getListStorage().size(); i++) {
                System.out.println(o.getListStorage().get(i).getProduct().getName());
            }
//        }


        }
    }





}

