package inventoryManagementSytem.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import inventoryManageMentSystem.CrudOperations;

public class ProductDao implements CrudOperations<Product> {
	private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    @Override
    public int add(Product product) throws SQLException {
        String query = "INSERT INTO Product (name, description, price, quantity, category) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getCategory());
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
    public Product getById(int id) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM Product WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product();
                    product.setId(resultSet.getInt("id"));
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setCategory(resultSet.getString("category"));
                }
            }
        }
        return product;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setCategory(resultSet.getString("category"));
                products.add(product);
            }
        }
        
        // Printing data in a table-like format
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-60s %-10s %-10s %-15s\n", "ID", "Name", "Description", "Price", "Quantity", "Category");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        for (Product product : products) {
            System.out.printf("%-5d %-20s %-60s %-10.2f %-10d %-15s\n", 
                              product.getId(), 
                              product.getName(), 
                              product.getDescription(), 
                              product.getPrice(), 
                              product.getQuantity(), 
                              product.getCategory());
        }
        
        return products;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        String query = "UPDATE Product SET name = ?, description = ?, price = ?, quantity = ?, category = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getCategory());
            preparedStatement.setInt(6, product.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM Product WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
