package dal;

import model.Product;
import model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDAO extends DBContext {

    private UserDAO userDAO = new UserDAO();
    private ProductDAO productDAO = new ProductDAO();


    public List<Product> getListDistinctProductWithStorage() {
        List<Product> list = new ArrayList<>();
        try {
            String query = "select distinct p.id from product p\n" +
                    "left join storage s on p.id = s.productId\n" +
                    "where s.isUsed = false and s.isDelete = false;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(productDAO.findProductById(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.err.println("getListDistinctProductWithStorage: " + e.getMessage());
        }
        return list;
    }

    public List<Storage> getListStorageWithNearestExpiredAt(int size, String supplierName, int price) {
        List<Storage> listStorage = new ArrayList<>();
        try {
            String query = "select s.* from storage s\n" +
                    "left join product p on s.productId = p.id\n" +
                    "left join supplier s2 on p.supplier = s2.id\n" +
                    "where s2.name = ? and p.price = ? and s.isUsed = false and s.isDelete = false\n" +
                    "and p.isDelete = false and s2.isDelete = false\n" +
                    "order by s.expiredAt\n" +
                    "limit ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplierName);
            ps.setInt(2, price);
            ps.setInt(3, size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listStorage.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy"))));
            }
        } catch (SQLException e) {
            System.err.println("getStorageWithNearestExpiredAt: " + e.getMessage());
        }

        return listStorage;
    }

    public List<Storage> searchStorage(int price, int productId, String search, int page) {
        List<Storage> list = new ArrayList<>();
        try {
            String query = "select s.* from storage s " +
                    "left join product p on s.productId = p.id " +
                    "where price" + (price > -1 ? " = ?" : "") +
                    " and s.productId" + (productId > -1 ? " = ?" : "") +
                    " and p.name like ? and s.isUsed = false and s.isDelete = false" +
                    " limit 10 offset ?";
            PreparedStatement ps = connection.prepareStatement(query);
            int i = 1;
            if (price > -1) {
                ps.setInt(i, price);
                i += 1;
            }
            if (productId > -1) {
                ps.setInt(i, productId);
                i += 1;
            }
            ps.setString(i, search);
            i += 1;
            ps.setInt(i, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy"))));
            }
        } catch (SQLException e) {
            System.err.println("searchStorage: " + e.getMessage());
        }
        return list;
    }

    public void delete(Storage storage) {
        try {
            String query = "update storage\n" +
                    "set deletedAt = ?, deletedBy = ?, isDelete = ?\n" +
                    "where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setTimestamp(1, storage.getDeletedAt());
            ps.setInt(2, storage.getDeletedBy().getId());
            ps.setBoolean(3, storage.isDelete());
            ps.setLong(4, storage.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Delete storage: " + e.getMessage());
        }
    }
    public void update(Storage storage) {
        try {
            String query = "update storage\n" +
                    "set serialNumber = ?, cardNumber = ?, expiredAt = ?, productId = ?, createdAt = ?,\n" +
                    "    createdBy = ?, updatedAt = ?, updatedBy = ?, isUsed = ?, isDelete = ?\n" +
                    "where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, storage.getSerialNumber());
            ps.setString(2, storage.getCardNumber());
            ps.setTimestamp(3, storage.getExpiredAt());
            ps.setInt(4, storage.getProduct().getId());
            ps.setTimestamp(5, storage.getCreatedAt());
            ps.setInt(6, storage.getCreatedBy().getId());
            ps.setTimestamp(7, storage.getUpdatedAt());
            ps.setInt(8, storage.getUpdatedBy().getId());
            ps.setBoolean(9, storage.isUsed());
            ps.setBoolean(10, storage.isDelete());
            ps.setLong(11, storage.getId());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Update storage: " + e.getMessage());
        }
    }
    public Storage getStorageById(long id) {

        try {
            String query = "select * from storage where isUsed = false and isDelete = false and id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")));
            }
        } catch (SQLException e) {
            System.out.println("getStorageById: " + e.getMessage());
        }
        return null;
    }

    public Long getTotalStorage(int price, int productId, String search) {
        try {
            String query = "select count(s.id) from storage s " +
                    "left join product p on s.productId = p.id " +
                    "where price" + (price > -1 ? " = ?" : "") +
                    " and s.productId" + (productId > -1 ? " = ?" : "") +
                    " and p.name like ? and s.isUsed = false and s.isDelete = false;";
            PreparedStatement ps = connection.prepareStatement(query);
            int i = 1;
            if (price > -1) {
                ps.setInt(i, price);
                i += 1;
            }
            if (productId > -1) {
                ps.setInt(i, productId);
                i += 1;
            }
            ps.setString(i, search);
            i += 1;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("count(s.id)");
            }
        } catch (SQLException e) {
            System.err.println("searchStorage: " + e.getMessage());
        }
        return (long) 0;
    }

    public List<Storage> getListStorageForPage(int page) {
        List<Storage> list = new ArrayList<>();
        try {
            String query = "select * from storage where isUsed = false and isDelete = false\n" +
                    "limit 10 offset ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy"))));
            }
        } catch (SQLException e) {
            System.out.println("getAllStorage: " + e.getMessage());
        }
        return list;
    }

    public List<Storage> getAllStorage() {
        List<Storage> list = new ArrayList<>();
        try {
            String query = "select * from storage where isUsed = false and isDelete = false";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ProductDAO productDAO = new ProductDAO();
            while (rs.next()) {
                list.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy"))));
            }
        } catch (SQLException e) {
            System.out.println("getAllStorage: " + e.getMessage());
        }
        return list;
    }
    public Storage getStorageByIdWithUsed(long id) {

        try {
            String query = "select * from storage where isUsed = true and isDelete = false and id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), userDAO.getUserById(rs.getInt("deletedBy")));
            }
        } catch (SQLException e) {
            System.out.println("getStorageById: " + e.getMessage());
        }
        return null;
    }
}