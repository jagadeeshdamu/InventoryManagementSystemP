package inventoryManagementSytem.customerAdress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import inventoryManageMentSystem.CrudOperations;

public class CustomerAddressDao implements CrudOperations<CustomerAddress> {
	private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    @Override
    public int add(CustomerAddress address) throws SQLException {
        String query = "INSERT INTO CustomerAddress (name, location, pincode) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, address.getName());
            preparedStatement.setString(2, address.getLocation());
            preparedStatement.setString(3, address.getPincode());
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
    public CustomerAddress getById(int id) throws SQLException {
        CustomerAddress address = null;
        String query = "SELECT * FROM CustomerAddress WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    address = new CustomerAddress();
                    address.setId(resultSet.getInt("id"));
                    address.setName(resultSet.getString("name"));
                    address.setLocation(resultSet.getString("location"));
                    address.setPincode(resultSet.getString("pincode"));
                }
            }
        }
        return address;
    }

    @Override
    public List<CustomerAddress> getAll() throws SQLException {
        List<CustomerAddress> addresses = new ArrayList<>();
        String query = "SELECT * FROM CustomerAddress";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                CustomerAddress address = new CustomerAddress();
                address.setId(resultSet.getInt("id"));
                address.setName(resultSet.getString("name"));
                address.setLocation(resultSet.getString("location"));
                address.setPincode(resultSet.getString("pincode"));
                addresses.add(address);
            }
        }
        
        // Printing data in a table-like format
        System.out.printf("%-5s %-20s %-30s %-10s\n", "ID", "Name", "Location", "Pincode");
        for (CustomerAddress address : addresses) {
            System.out.printf("%-5d %-20s %-30s %-10s\n", 
                              address.getId(), 
                              address.getName(), 
                              address.getLocation(), 
                              address.getPincode());
        }
        
        return addresses;
    }

    @Override
    public boolean update(CustomerAddress address) throws SQLException {
        String query = "UPDATE CustomerAddress SET name = ?, location = ?, pincode = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, address.getName());
            preparedStatement.setString(2, address.getLocation());
            preparedStatement.setString(3, address.getPincode());
            preparedStatement.setInt(4, address.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM CustomerAddress WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}

