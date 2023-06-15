package dal;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DBContext {

    public User getUserById(int id) {
        try {
            String query = "SELECT * from user where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("account"), rs.getString("password"),
                        rs.getString("email"), rs.getInt("role"), rs.getString("phoneNumber"), rs.getDouble("balance"),
                        rs.getBoolean("isDelete"), rs.getBoolean("isActive"), rs.getTimestamp("createdAt"),
                        rs.getInt("createdBy"), rs.getTimestamp("updatedAt"), rs.getInt("updatedBy"),
                        rs.getTimestamp("deletedAt"), rs.getInt("deletedBy"));
            }
         } catch (SQLException e) {
            System.out.println("getUserById: " + e.getMessage());
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
            String query = "SELECT * from user where account = ? and isActive = true";
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
                return new User().builder().id(rs.getInt("id"))
                        .account(rs.getString("account")).password(rs.getString("password"))
                        .email(rs.getString("email")).role(rs.getInt("role"))
                        .phoneNumber(rs.getString("phoneNumber")).balance(rs.getDouble("balance"))
                        .isActive(rs.getBoolean("isActive")).isDelete(rs.getBoolean("isDelete"))
                        .createdAt(rs.getTimestamp("createdAt")).createdBy(rs.getInt("createdBy"))
                        .deletedAt(rs.getTimestamp("deletedAt")).deletedBy(rs.getInt("deletedBy"))
                        .updatedAt(rs.getTimestamp("updatedAt")).updatedBy(rs.getInt("updatedBy"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("UserDao-isUserExist: " + e.getMessage());
        }
        return null;
    }

    public User getUserbyAccount(String account) {
        try {
            String query = "SELECT * FROM user WHERE account = ? and isActive = true";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User().builder().id(rs.getInt("id"))
                        .account(rs.getString("account")).password(rs.getString("password"))
                        .email(rs.getString("email")).role(rs.getInt("role"))
                        .phoneNumber(rs.getString("phoneNumber")).balance(rs.getDouble("balance"))
                        .isActive(rs.getBoolean("isActive")).isDelete(rs.getBoolean("isDelete"))
                        .createdAt(rs.getTimestamp("createdAt")).createdBy(rs.getInt("createdBy"))
                        .deletedAt(rs.getTimestamp("deletedAt")).deletedBy(rs.getInt("deletedBy"))
                        .updatedAt(rs.getTimestamp("updatedAt")).updatedBy(rs.getInt("updatedBy"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("UserDao-isUserExist: " + e.getMessage());
        }
        return null;
    }


    public void add(User user) {
        try {
            String query = "INSERT INTO user (account, password, email, role, isDelete, isActive, createdAt)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setBoolean(5, user.isDelete());
            ps.setBoolean(6, user.isActive());
            ps.setTimestamp(7, user.getCreatedAt());
            ps.execute();
            System.out.println("Insert new user successfully!");
        } catch (SQLException e) {
            System.out.println("UserDAO-add: " + e.getMessage());
        }
    }

    public void update(User user, int id) {
        try {
            String query = "UPDATE user SET account = ?, password = ?, email = ?, role = ?,"
                    + "phoneNumber = ?, balance = ?, isDelete = ?, isActive = ?, createdAt = ?, createdBy = ?, updatedAt = ?, updatedBy = ?, "
                    + "deletedAt = ?, deletedBy = ? "
                    + "WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getAccount());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setString(5, user.getPhoneNumber());
            ps.setDouble(6, user.getBalance());
            ps.setBoolean(7, user.isDelete());
            ps.setBoolean(8, user.isActive());
            ps.setInt(15, id);
            ps.setTimestamp(9, user.getCreatedAt());
            ps.setInt(10, user.getCreatedBy());
            ps.setTimestamp(11, user.getUpdatedAt());
            ps.setInt(12, user.getUpdatedBy());
            ps.setTimestamp(13, user.getDeletedAt());
            ps.setInt(14, user.getDeletedBy());
            ps.execute();
            System.out.println("Update user successfully!");
        } catch (SQLException e) {
            System.out.println("UserDAO-update: " + e.getMessage());
        }
    }


    public boolean checkUser(String account, String password) {
        try {
            String query = "SELECT * FROM user WHERE account = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("UserDAO-checkUser: " + e.getMessage());
        }
        return false;
    }

    public boolean isAccountActive(String account) {
        try {
            String query = "SELECT * from user where account = ? and isActive = 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("UserDAO-isAccountAvailable: " + e.getMessage());
        }
        return false;
    }
}
