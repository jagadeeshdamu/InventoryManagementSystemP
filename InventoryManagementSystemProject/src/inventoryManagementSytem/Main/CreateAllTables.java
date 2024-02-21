package inventoryManagementSytem.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAllTables {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createAllTables() {
        try {
            // Establishing connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            // Creating tables
            createTables();

            // Performing CRUD operations
            // You can call methods to perform operations here

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing JDBC resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to create tables
    private static void createTables() throws SQLException {
        // Create Products table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Product (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(255)," +
                "price DECIMAL(10, 2) NOT NULL," +
                "quantity INT NOT NULL," +
                "category VARCHAR(100)" +
                ")");
        //query for Registration table
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Registration (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL" +
                ")");
        //query for Login
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS Login (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "email VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "FOREIGN KEY (email) REFERENCES Registration(email)" +
                ")");
        //query for PurchaseOrder
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS PurchaseOrder (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "productname VARCHAR(255) NOT NULL," +
                "quantity INT NOT NULL" +
                ")");
        //query for CustomerAddress
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS CustomerAddress (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "location VARCHAR(255) NOT NULL," +
                "pincode VARCHAR(10) NOT NULL" +
                ")");
        //query for AdminLogin
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS AdminLogin (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL" +
                ")");
        //query for Suppliers
        statement.executeUpdate( "CREATE TABLE IF NOT EXISTS Suppliers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "location VARCHAR(255) NOT NULL" +
                ")");  
    }

}
