package dao;

import model.User;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for User entity.
 */
public class UserDAO {

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (username, encrypted_password, email, role_id, currency_preference) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEncryptedPassword());
            stmt.setString(3, user.getEmail());
            // Use roleId directly from User model
            int roleId = user.getRoleId();
            stmt.setInt(4, roleId);
            stmt.setString(5, user.getCurrencyPreference());
            stmt.executeUpdate();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT u.id, u.username, u.encrypted_password, u.email, u.role_id, u.currency_preference " +
                     "FROM Users u WHERE u.username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEncryptedPassword(rs.getString("encrypted_password"));
                    user.setEmail(rs.getString("email"));
                    user.setRoleId(rs.getInt("role_id"));
                    user.setCurrencyPreference(rs.getString("currency_preference"));
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.id, u.username, u.encrypted_password, u.email, r.role_name, u.currency_preference " +
                     "FROM Users u JOIN Roles r ON u.role_id = r.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEncryptedPassword(rs.getString("encrypted_password"));
                user.setEmail(rs.getString("email"));
                user.setRoleId(rs.getInt("id"));
                user.setCurrencyPreference(rs.getString("currency_preference"));
                users.add(user);
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET encrypted_password = ?, email = ?, role_id = ?, currency_preference = ? WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getEncryptedPassword());
            stmt.setString(2, user.getEmail());
            int roleId = user.getRoleId();
            stmt.setInt(3, roleId);
            stmt.setString(4, user.getCurrencyPreference());
            stmt.setString(5, user.getUsername());
            stmt.executeUpdate();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM Users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }

}
