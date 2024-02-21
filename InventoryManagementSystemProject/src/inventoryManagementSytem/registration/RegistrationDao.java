package inventoryManagementSytem.registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import inventoryManageMentSystem.CrudOperations;

public class RegistrationDao implements CrudOperations<Registration> {
	private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    @Override
    public int add(Registration registration) throws SQLException {
        String query = "INSERT INTO Registration (name, email, password) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, registration.getName());
            preparedStatement.setString(2, registration.getEmail());
            preparedStatement.setString(3, registration.getPassword());
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
    public Registration getById(int id) throws SQLException {
        Registration registration = null;
        String query = "SELECT * FROM Registration WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    registration = new Registration();
                    registration.setId(resultSet.getInt("id"));
                    registration.setName(resultSet.getString("name"));
                    registration.setEmail(resultSet.getString("email"));
                    registration.setPassword(resultSet.getString("password"));
                }
            }
        }
        return registration;
    }

    @Override
    public List<Registration> getAll() throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String query = "SELECT * FROM Registration";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Registration registration = new Registration();
                registration.setId(resultSet.getInt("id"));
                registration.setName(resultSet.getString("name"));
                registration.setEmail(resultSet.getString("email"));
                registration.setPassword(resultSet.getString("password"));
                registrations.add(registration);
            }
        }
        
        // Printing data in a table-like format
        System.out.printf("%-5s %-20s %-30s %-10s\n", "ID", "Name", "Email", "Password");
        for (Registration registration : registrations) {
            System.out.printf("%-5d %-20s %-30s %-10s\n", 
                              registration.getId(), 
                              registration.getName(), 
                              registration.getEmail(), 
                              registration.getPassword());
        }
        
        return registrations;
    }


    @Override
    public boolean update(Registration registration) throws SQLException {
        String query = "UPDATE Registration SET name = ?, email = ?, password = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, registration.getName());
            preparedStatement.setString(2, registration.getEmail());
            preparedStatement.setString(3, registration.getPassword());
            preparedStatement.setInt(4, registration.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM Registration WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
    
    public boolean checkRegistrationCredentials(String email, String password) {
        boolean isRegistrationLoggedIn = false;
        
        try {
        	Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String query = "SELECT * FROM Registration WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If resultSet has any rows, it means the provided credentials are valid
            if (resultSet.next()) {
            	isRegistrationLoggedIn = true;
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database exceptions appropriately
        }

        return isRegistrationLoggedIn;
    }
}

