package inventoryManagementSytem.AdminLogin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import inventoryManageMentSystem.CrudOperations;

public class AdminLoginDao implements CrudOperations<AdminLogin> {
    private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    @Override
    public int add(AdminLogin adminLogin) throws SQLException {
        String query = "INSERT INTO AdminLogin (username, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, adminLogin.getUsername());
            preparedStatement.setString(2, adminLogin.getPassword());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
        return -1;
    }

    @Override
    public AdminLogin getById(int id) throws SQLException {
        AdminLogin adminLogin = null;
        String query = "SELECT * FROM AdminLogin WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    adminLogin = new AdminLogin();
                    adminLogin.setId(resultSet.getInt("id"));
                    adminLogin.setUsername(resultSet.getString("username"));
                    adminLogin.setPassword(resultSet.getString("password"));
                }
            }
        }
        return adminLogin;
    }

    @Override
    public List<AdminLogin> getAll() throws SQLException {
        List<AdminLogin> adminLogins = new ArrayList<>();
        String query = "SELECT * FROM AdminLogin";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                AdminLogin adminLogin = new AdminLogin();
                adminLogin.setId(resultSet.getInt("id"));
                adminLogin.setUsername(resultSet.getString("username"));
                adminLogin.setPassword(resultSet.getString("password"));
                adminLogins.add(adminLogin);
            }
        }
        return adminLogins;
    }

    @Override
    public boolean update(AdminLogin adminLogin) throws SQLException {
        String query = "UPDATE AdminLogin SET username = ?, password = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, adminLogin.getUsername());
            preparedStatement.setString(2, adminLogin.getPassword());
            preparedStatement.setInt(3, adminLogin.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM AdminLogin WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
    
    
    public boolean checkAdminCredentials(String username, String password) {
        boolean isAdminLoggedIn = false;
        
        try {
        	Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM AdminLogin WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If resultSet has any rows, it means the provided credentials are valid
            if (resultSet.next()) {
                isAdminLoggedIn = true;
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database exceptions appropriately
        }

        return isAdminLoggedIn;
    }
}

