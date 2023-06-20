package dal;

<<<<<<<HEAD
        =======
import model.Order;
>>>>>>>a4d8522c6687d44c55392be86b7549d4484dce87
import model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO extends DBContext {
    private StorageDAO storageDAO = new StorageDAO();

    public List<Storage> getListStorebyOrder(long id) {
        ArrayList<Storage> listStorage = new ArrayList<>();
        try {
            String sql = "SELECT storage from  order_detail where `order` = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listStorage.add(storageDAO.getStorageByIdWithUsed(rs.getLong("storage")));

            }

        } catch (SQLException e) {
            System.out.println("getListStorebyOrder : " + e.getMessage());
        }
        return listStorage;
    }

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
