package model;

/**
 * Represents a user in the TrendSphere system.
 */
public class User {
    private int id;
    private String username;
    private String encryptedPassword;
    private String email;
    private int roleId; 
    private String currencyPreference;

    public User() {
    }

    public User(int id, String username, String encryptedPassword, String email, int roleId, String currencyPreference) {
        this.id = id;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
        this.roleId = roleId;
        this.currencyPreference = currencyPreference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getCurrencyPreference() {
        return currencyPreference;
    }

    public void setCurrencyPreference(String currencyPreference) {
        this.currencyPreference = currencyPreference;
    }
}