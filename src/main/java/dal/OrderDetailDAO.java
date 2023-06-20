package dal;

import model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO extends DBContext{
    private StorageDAO storageDAO = new StorageDAO();

    public List<Storage> getListStorebyOrder (long id){
        ArrayList<Storage> listStorage = new ArrayList<>();
        try{
            String sql = "SELECT storage from  order_detail where `order` = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listStorage.add(storageDAO.getStorageByIdWithUsed(rs.getLong("storage")));

            }

        } catch (SQLException e) {
            System.out.println("getListStorebyOrder : " + e.getMessage() );
        }
        return listStorage;
    }
}
