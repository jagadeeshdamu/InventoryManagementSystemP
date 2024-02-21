package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.AdminLogin.AdminLogin;
import inventoryManagementSytem.AdminLogin.AdminLoginDao;

public class AdminLogins {
    Scanner sc = new Scanner(System.in);
    CrudOperations<AdminLogin> adminLoginDao = new AdminLoginDao();
    AdminLoginDao login=new AdminLoginDao();
    

    public void addDetails() throws SQLException {
        AdminLogin adminLogin = new AdminLogin();

        System.out.print("Enter username: ");
        adminLogin.setUsername(sc.nextLine());

        System.out.print("Enter password: ");
        adminLogin.setPassword(sc.nextLine());

        adminLoginDao.add(adminLogin);
        System.out.println("Admin login details inserted successfully.");
    }

    public void retrieveDetailById() throws SQLException {
        System.out.print("Enter the admin login ID you want to retrieve: ");
        int adminLoginId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        AdminLogin adminLogin = adminLoginDao.getById(adminLoginId);
        if (adminLogin != null) {
            System.out.println("Admin login details:");
            System.out.println(adminLogin);
        } else {
            System.out.println("Admin login with ID " + adminLoginId + " does not exist.");
        }
    }

    public void retrieveAllDetails() throws SQLException {
        System.out.println("All Admin Login Details:");
        System.out.println(adminLoginDao.getAll());
    }

    public void updateDetails() throws SQLException {
        System.out.print("Enter the admin login ID you want to update: ");
        int adminLoginId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        // Retrieve the existing admin login from the database
        AdminLogin existingAdminLogin = adminLoginDao.getById(adminLoginId);

        if (existingAdminLogin == null) {
            System.out.println("Admin login with ID " + adminLoginId + " does not exist.");
            return;
        }

        System.out.println("Existing Details:");
        System.out.println(existingAdminLogin);

        // Prompt the user for updated details
        System.out.print("New username : ");
        String newUsername = sc.nextLine().trim(); // Trim leading and trailing whitespace
        if (!newUsername.isEmpty()) {
            existingAdminLogin.setUsername(newUsername);
        }

        System.out.print("New password : ");
        String newPassword = sc.nextLine().trim();
        if (!newPassword.isEmpty()) {
            existingAdminLogin.setPassword(newPassword);
        }

        // Update the admin login in the database
        adminLoginDao.update(existingAdminLogin);

        System.out.println("Details updated successfully.");
    }

    public void deleteDetail() throws SQLException {
        System.out.print("Enter the admin login ID you want to delete: ");
        int adminLoginId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        adminLoginDao.delete(adminLoginId);
    }
   
   public boolean checkcredentials() {
	   System.out.print("username : ");
	   String username=sc.nextLine();
	   System.out.print("password : ");
	   String password=sc.nextLine();
	   return login.checkAdminCredentials(username, password);
   }
    
}

