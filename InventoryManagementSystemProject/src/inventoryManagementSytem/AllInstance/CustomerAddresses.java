package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.customerAdress.CustomerAddress;
import inventoryManagementSytem.customerAdress.CustomerAddressDao;

public class CustomerAddresses {
    Scanner sc = new Scanner(System.in);
    CrudOperations<CustomerAddress> customerAddressDao = new CustomerAddressDao();

    
    public void addDetails() throws SQLException {
        CustomerAddress customerAddress = new CustomerAddress();

        System.out.print("Enter customer name: ");
        customerAddress.setName(sc.nextLine());

        System.out.print("Enter location: ");
        customerAddress.setLocation(sc.nextLine());

        System.out.print("Enter pincode: ");
        customerAddress.setPincode(sc.nextLine());

        customerAddressDao.add(customerAddress);
    }

    public void retrieveDetailById() throws SQLException {
        System.out.print("Enter the customer address ID you want to retrieve: ");
        int customerAddressId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        CustomerAddress customerAddress = customerAddressDao.getById(customerAddressId);
        if (customerAddress != null) {
            System.out.println("Customer address details:");
            System.out.println(customerAddress);
        } else {
            System.out.println("Customer address with ID " + customerAddressId + " does not exist.");
        }
    }

    public void retrieveAllDetails() throws SQLException {
        System.out.println("All Customer Address Details:");
        customerAddressDao.getAll();
    }

    public void updateDetails() throws SQLException {
        System.out.print("Enter the customer address ID you want to update: ");
        int customerAddressId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        // Retrieve the existing customer address from the database
        CustomerAddress existingCustomerAddress = customerAddressDao.getById(customerAddressId);

        if (existingCustomerAddress == null) {
            System.out.println("Customer address with ID " + customerAddressId + " does not exist.");
            return;
        }

        System.out.println("Existing Details:");
        System.out.println(existingCustomerAddress);

        // Prompt the user for updated details
        System.out.print("New customer name (press Enter to keep current): ");
        String newName = sc.nextLine().trim(); // Trim leading and trailing whitespace
        if (!newName.isEmpty()) {
            existingCustomerAddress.setName(newName);
        }

        System.out.print("New location (press Enter to keep current): ");
        String newLocation = sc.nextLine().trim();
        if (!newLocation.isEmpty()) {
            existingCustomerAddress.setLocation(newLocation);
        }

        System.out.print("New pincode (press Enter to keep current): ");
        String newPincode = sc.nextLine().trim();
        if (!newPincode.isEmpty()) {
            existingCustomerAddress.setPincode(newPincode);
        }

        // Update the customer address in the database
        customerAddressDao.update(existingCustomerAddress);

        System.out.println("Details updated successfully.");
    }

    public void deleteDetail() throws SQLException {
        System.out.print("Enter the customer address ID you want to delete: ");
        int customerAddressId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        customerAddressDao.delete(customerAddressId);
    }

    
}

