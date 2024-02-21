package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.supplier.Supplier;
import inventoryManagementSytem.supplier.SupplierDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Suppliers {
    Scanner sc = new Scanner(System.in);
    CrudOperations<Supplier> suppliersDao = new SupplierDao();

    
    public void addDetails() throws SQLException {
    	Supplier supplier = new Supplier();

        System.out.print("Enter supplier name: ");
        supplier.setName(sc.nextLine());

        System.out.print("Enter location: ");
        supplier.setLocation(sc.nextLine());

        suppliersDao.add(supplier);
        System.out.println("Supplier details inserted successfully.");
    }

    public void retrieveDetailById() throws SQLException {
        System.out.print("Enter the supplier ID you want to retrieve: ");
        int supplierId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        Supplier supplier = suppliersDao.getById(supplierId);
        if (supplier != null) {
            System.out.println("Supplier details:");
            System.out.println(supplier);
        } else {
            System.out.println("Supplier with ID " + supplierId + " does not exist.");
        }
    }

    public void retrieveAllDetails() throws SQLException {
        System.out.println("All Supplier Details:");
        System.out.println(suppliersDao.getAll());
    }

    public void updateDetails() throws SQLException {
        System.out.print("Enter the supplier ID you want to update: ");
        int supplierId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        // Retrieve the existing supplier from the database
        Supplier existingSupplier = suppliersDao.getById(supplierId);

        if (existingSupplier == null) {
            System.out.println("Supplier with ID " + supplierId + " does not exist.");
            return;
        }

        System.out.println("Existing Details:");
        System.out.println(existingSupplier);

        // Prompt the user for updated details
        System.out.print("New supplier name (press Enter to keep current): ");
        String newName = sc.nextLine().trim(); // Trim leading and trailing whitespace
        if (!newName.isEmpty()) {
            existingSupplier.setName(newName);
        }

        System.out.print("New location (press Enter to keep current): ");
        String newLocation = sc.nextLine().trim();
        if (!newLocation.isEmpty()) {
            existingSupplier.setLocation(newLocation);
        }

        // Update the supplier in the database
        suppliersDao.update(existingSupplier);

        System.out.println("Details updated successfully.");
    }

    public void deleteDetail() throws SQLException {
        System.out.print("Enter the supplier ID you want to delete: ");
        int supplierId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        suppliersDao.delete(supplierId);
        System.out.println("Supplier with ID " + supplierId + " deleted successfully.");
    }
}
