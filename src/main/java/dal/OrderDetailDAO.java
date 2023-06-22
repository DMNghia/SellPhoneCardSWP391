package dal;

import model.Order;
import model.Product;
import model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO extends DBContext {
    private StorageDAO storageDAO = new StorageDAO();
    private ProductDAO productDAO = new ProductDAO();

    private SupplierDAO supplierDAO = new SupplierDAO();
    private UserDAO userDAO = new UserDAO();

    public List<Storage> getListStorageBySearchProduct(long oid, String name) {
        List<Storage> list = new ArrayList<>();
        try {
            String query = "select s.* from storage s\n" +
                    "left join product p on s.productId = p.id\n" +
                    "left join order_detail o on s.id = o.storage\n" +
                    "where s.isUsed = true and p.name like ? and o.`order` = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setLong(2, oid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy"))));
            }
        } catch (SQLException e) {
            System.err.println("getListStorageBySearchProduct: " + e.getMessage());
        }

        return list;
    }

    public ArrayList<Product> searchProductByName(String name) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String query = "select * from product where name like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"),
                        supplierDAO.getSuppierById(rs.getInt("supplier")), rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")),
                        rs.getBoolean("isDelete"), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")),
                        rs.getTimestamp("updatedAt"), userDAO.getUserById(rs.getInt("updatedBy"))));
            }

        } catch (SQLException e) {
            System.out.println("searchProductByName: " + e.getMessage());
        }
        return list;
    }

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
}
