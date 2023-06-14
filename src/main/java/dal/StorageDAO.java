package dal;

import model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorageDAO extends DBContext {

    public List<Storage> getAllStorage() {
        List<Storage> list = new ArrayList<>();
        try {
            String query = "select * from storage";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), rs.getInt("productId"), rs.getBoolean("isUsed"),
                        rs.getTimestamp("createdAt"), rs.getInt("createdBy"), rs.getTimestamp("updatedAt"),
                        rs.getInt("updatedBy"), rs.getTimestamp("deletedAt"), rs.getInt("deletedBy")));
            }
        } catch (SQLException e) {
            System.out.println("getAllStorage: " + e.getMessage());
        }
        return list;
    }
}
