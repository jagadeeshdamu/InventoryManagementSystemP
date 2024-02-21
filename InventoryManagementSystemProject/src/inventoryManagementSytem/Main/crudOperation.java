package inventoryManagementSytem.Main;

import java.sql.SQLException;
import java.util.List;

interface CrudOperations<T> {
	int add(T t) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(int id) throws SQLException;
}