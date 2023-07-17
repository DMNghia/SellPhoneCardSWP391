package dal;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {
    public List<User> getAllUser(){
        List<User> userlist = new ArrayList<>();
        try{
            String query = "select * from user where   role = true;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userlist.add(new User(rs.getInt("id"), rs.getString("account"), rs.getString("password"),
                        rs.getString("email"), rs.getInt("role"), rs.getString("phoneNumber"), rs.getInt("balance"),
                        rs.getBoolean("isDelete"), rs.getBoolean("isActive"), rs.getTimestamp("createdAt"),
                        rs.getInt("createdBy"), rs.getTimestamp("updatedAt"), rs.getInt("updatedBy"),
                        rs.getTimestamp("deletedAt"), rs.getInt("deletedBy")));
            }

        }catch (SQLException e){
            System.out.println("getAllUser: " + e.getMessage());
        }
        return  userlist;
    }

    public User getUserById(long id) {
        try {
            String query = "SELECT * from user where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("account"), rs.getString("password"),
                        rs.getString("email"), rs.getInt("role"), rs.getString("phoneNumber"), rs.getInt("balance"),
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
                        .phoneNumber(rs.getString("phoneNumber")).balance(rs.getInt("balance"))
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
                        .phoneNumber(rs.getString("phoneNumber")).balance(rs.getInt("balance"))
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

    public int getTotalUsers(int id) {
        try {
            String query = "select count(id) from user where isDelete = false and id != ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count(id)");
            }
         } catch (SQLException e) {
            System.out.println("getTotalUsers: " + e.getMessage());
        }
        return 0;
    }

    public List<User> searchUsers(String searchName, int page, int isActive) {
        List<User> listUser = new ArrayList<>();

        try {
            String query = "select * from user where isDelete = false\n" +
                    " and account like ? " + (isActive >= 0 ? "and isActive = ?" : "\n") +
                    " limit 10 offset ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, searchName);
            if (isActive >= 0) {
                ps.setInt(2, isActive);
                ps.setInt(3, page);
            } else {
                ps.setInt(2, page);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listUser.add(new User().builder().id(rs.getInt("id")).account(rs.getString("account"))
                        .email(rs.getString("email")).phoneNumber(rs.getString("phoneNumber"))
                        .isActive(rs.getBoolean("isActive")).balance(rs.getInt("balance"))
                        .createdAt(rs.getTimestamp("createdAt")).createdBy(rs.getInt("createdBy")).build());
            }
        } catch (SQLException e) {
            System.out.println("searchUsers: " + e.getMessage());
        }
        return listUser;
    }
//    public Long getTotalUser(String account, int id, String search) {
//        try {
//            String query = "select count(s.id) from storage s " +
//                    "left join product p on s.productId = p.id " +
//                    "where price" + (price > -1 ? " = ?" : "") +
//                    " and s.productId" + (productId > -1 ? " = ?" : "") +
//                    " and p.name like ? and s.isUsed = false and s.isDelete = false;";
//            PreparedStatement ps = connection.prepareStatement(query);
//            int i = 1;
//            if (price > -1) {
//                ps.setInt(i, price);
//                i += 1;
//            }
//            if (productId > -1) {
//                ps.setInt(i, productId);
//                i += 1;
//            }
//            ps.setString(i, search);
//            i += 1;
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                return rs.getLong("count(s.id)");
//            }
//        } catch (SQLException e) {
//            System.err.println("searchStorage: " + e.getMessage());
//        }
//        return (long) 0;
//    }

//    public static void main(String[] args) {
//        List<User> listuser = DAO.userDAO.getAllUser();
//        System.out.println("List user" + listuser);
//    }
}
