package inventoryManagementSytem.purchaseProduct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import inventoryManageMentSystem.CrudOperations;

public class PurchaseOrderDao implements CrudOperations<PurchaseOrder> {
    private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    @Override
    public int add(PurchaseOrder order) throws SQLException {
        String query = "INSERT INTO PurchaseOrder (productname, quantity) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, order.getProductName());
            preparedStatement.setInt(2, order.getQuantity());
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
    public PurchaseOrder getById(int id) throws SQLException {
        PurchaseOrder order = null;
        String query = "SELECT * FROM PurchaseOrder WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order = new PurchaseOrder();
                    order.setId(resultSet.getInt("id"));
                    order.setProductName(resultSet.getString("productname"));
                    order.setQuantity(resultSet.getInt("quantity"));
                }
            }
        }
        return order;
    }

    @Override
    public List<PurchaseOrder> getAll() throws SQLException {
        List<PurchaseOrder> orders = new ArrayList<>();
        String query = "SELECT * FROM PurchaseOrder";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                PurchaseOrder order = new PurchaseOrder();
                order.setId(resultSet.getInt("id"));
                order.setProductName(resultSet.getString("productname"));
                order.setQuantity(resultSet.getInt("quantity"));
                orders.add(order);
            }
        }

        // Printing data in a table-like format
        System.out.println("-------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s\n", "ID", "Product Name", "Quantity");
        System.out.println("-------------------------------------------------------");
        for (PurchaseOrder order : orders) {
            System.out.printf("%-5d %-20s %-10d\n",
                    order.getId(),
                    order.getProductName(),
                    order.getQuantity());
        }

        return orders;
    }


    @Override
    public boolean update(PurchaseOrder order) throws SQLException {
        String query = "UPDATE PurchaseOrder SET productname = ?, quantity = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, order.getProductName());
            preparedStatement.setInt(2, order.getQuantity());
            preparedStatement.setInt(3, order.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM PurchaseOrder WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
