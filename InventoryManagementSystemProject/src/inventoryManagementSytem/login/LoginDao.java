package inventoryManagementSytem.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import inventoryManageMentSystem.CrudOperations;

public class LoginDao implements CrudOperations<Login> {
	private static final String URL = "jdbc:mysql://localhost:3306/inventoryProject";
    private static final String USER = "root";
    private static final String PASSWORD = "Jaga@123";

    @Override
    public int add(Login login) throws SQLException {
        String query = "INSERT INTO Login (email, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, login.getEmail());
            preparedStatement.setString(2, login.getPassword());
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
    public Login getById(int id) throws SQLException {
        Login login = null;
        String query = "SELECT * FROM Login WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    login = new Login();
                    login.setId(resultSet.getInt("id"));
                    login.setEmail(resultSet.getString("email"));
                    login.setPassword(resultSet.getString("password"));
                }
            }
        }
        return login;
    }

    @Override
    public List<Login> getAll() throws SQLException {
        List<Login> logins = new ArrayList<>();
        String query = "SELECT * FROM Login";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Login login = new Login();
                login.setId(resultSet.getInt("id"));
                login.setEmail(resultSet.getString("email"));
                login.setPassword(resultSet.getString("password"));
                logins.add(login);
            }
        }
        return logins;
    }

    @Override
    public boolean update(Login login) throws SQLException {
        String query = "UPDATE Login SET email = ?, password = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login.getEmail());
            preparedStatement.setString(2, login.getPassword());
            preparedStatement.setInt(3, login.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM Login WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
