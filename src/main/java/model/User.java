package model;

public class User {
    private int id;
    private String account;
    private String password;
    private String email;
    private int role;
    private String phoneNumber;
    private boolean isDelete;
    private boolean isActive;

    public User() {
    }

    public User(String account, String password, String email, int role, boolean isDelete, boolean isActive) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
        this.isDelete = isDelete;
        this.isActive = isActive;
    }

    public User(String account, String password, String email, int role, String phoneNumber, boolean isDelete, boolean isActive) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isDelete = isDelete;
        this.isActive = isActive;
    }

    public User(int id, String account, String password, String email, int role, String phoneNumber, boolean isDelete, boolean isActive) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isDelete = isDelete;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
