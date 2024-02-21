package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.login.Login;
import inventoryManagementSytem.login.LoginDao;

public class Logins {
     Scanner sc = new Scanner(System.in);
     CrudOperations<Login> loginDao = new LoginDao();
    Login login = new Login();
    
    public boolean addDetails() throws SQLException {
        

        System.out.print("Enter your email: ");
        login.setEmail(sc.nextLine());

        System.out.print("Enter your password: ");
        login.setPassword(sc.nextLine());

        if(loginDao.add(login)==1) {
        	return true;
        }else {
        	return false;
        }
        
    }

    public void retrieveDetailById() throws SQLException {
        System.out.print("Enter the login ID you want to retrieve: ");
        int loginId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        Login login = loginDao.getById(loginId);
        if (login != null) {
            System.out.println("Login details:");
            System.out.println(login);
        } else {
            System.out.println("Login with ID " + loginId + " does not exist.");
        }
    }

    public void retrieveAllDetails() throws SQLException {
        System.out.println("All Login Details:");
        System.out.println(loginDao.getAll());
    }

    public void updateDetails() throws SQLException {
        System.out.print("Enter the login ID you want to update: ");
        int loginId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        // Retrieve the existing login from the database
        Login existingLogin = loginDao.getById(loginId);

        if (existingLogin == null) {
            System.out.println("Login with ID " + loginId + " does not exist.");
            return;
        }

        System.out.println("Existing Details:");
        System.out.println(existingLogin);

        // Prompt the user for updated details
        System.out.print("New email (press Enter to keep current): ");
        String newEmail = sc.nextLine().trim(); // Trim leading and trailing whitespace
        if (!newEmail.isEmpty()) {
            existingLogin.setEmail(newEmail);
        }

        System.out.print("New password (press Enter to keep current): ");
        String newPassword = sc.nextLine().trim();
        if (!newPassword.isEmpty()) {
            existingLogin.setPassword(newPassword);
        }

        // Update the login in the database
        loginDao.update(existingLogin);

        System.out.println("Details updated successfully.");
    }

    public void deleteDetail() throws SQLException {
        System.out.print("Enter the login ID you want to delete: ");
        int loginId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        loginDao.delete(loginId);
        System.out.println("Login with ID " + loginId + " deleted successfully.");
    }

    
}
