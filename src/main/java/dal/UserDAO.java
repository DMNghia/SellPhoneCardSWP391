package dal;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DBContext {

    public User getUserbyAccount(String account) {
        try {
            String query = "SELECT * FROM user WHERE account = ? and isActive = true";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getBoolean(7), rs.getBoolean(8), rs.getTimestamp(9), rs.getInt(10), rs.getTimestamp(11), rs.getInt(12),
                        rs.getTimestamp(13), rs.getInt(14));
            }
        } catch (SQLException e) {
            System.out.println("UserDao-isUserExist: " + e.getMessage());
        }
        return null;
    }

    public boolean isEmailAvailable(String email) {
        try {
            String query = "SELECT * from user where email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("UserDAO-isEmailAvailable: " + e.getMessage());
        }
        return true;
    }

    public boolean isAccountAvailable(String account) {
        try {
            String query = "SELECT * from user where account = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("UserDAO-isAccountAvailable: " + e.getMessage());
        }
        return true;
    }

    public User getUser(String account, String password) {
        try {
            String query = "SELECT * FROM user WHERE account = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6),
                        rs.getBoolean(7), rs.getBoolean(8));
            }
        } catch (SQLException e) {
            System.out.println("UserDao-isUserExist: " + e.getMessage());
        }
        return null;
    }

    public void add(User user) {
        try {
            String query = "INSERT INTO user (account, password, email, role, isDelete, isActive)"
                    + "VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setBoolean(5, user.isDelete());
            ps.setBoolean(6, user.isActive());
            ps.execute();
            System.out.println("Insert new user successfully!");
        } catch (SQLException e) {
            System.out.println("UserDAO-add: " + e.getMessage());
        }
    }

    public void update(User user, int id) {
        try {
            String query = "UPDATE user SET account = ?, password = ?, email = ?, role = ?,"
                    + "phoneNumber = ?, isDelete = ?, isActive = ?, createdAt = ?, createdBy = ?, updatedAt = ?, updatedBy = ?, "
                    + "deletedAt = ?, deletedBy = ? "
                    + "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setString(5, user.getPhoneNumber());
            ps.setBoolean(6, user.isDelete());
            ps.setBoolean(7, user.isActive());
            ps.setInt(14, id);
            ps.setTimestamp(8, user.getCreatedAt());
            ps.setInt(9, user.getCreatedBy());
            ps.setTimestamp(10, user.getupdatedAt());
            ps.setInt(11, user.getupdatedBy());
            ps.setTimestamp(12, user.getDeletedAt());
            ps.setInt(13, user.getDeletedBy());
            ps.execute();
            System.out.println("Update user successfully!");
        } catch (SQLException e) {
            System.out.println("UserDAO-update: " + e.getMessage());
        }
    }

}
