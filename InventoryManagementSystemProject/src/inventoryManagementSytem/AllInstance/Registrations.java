package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.registration.Registration;
import inventoryManagementSytem.registration.RegistrationDao;

public class Registrations {
     Scanner sc = new Scanner(System.in);
     
     CrudOperations<Registration> registrationDao = new RegistrationDao();
     RegistrationDao rao=new RegistrationDao();
     Registration registration = new Registration();
    

    
    

    public void addDetails() throws SQLException {
    	

        System.out.print("Enter your name: ");
        registration.setName(sc.nextLine());

        System.out.print("Enter your email: ");
        registration.setEmail(sc.nextLine());

        System.out.print("Enter your password: ");
        registration.setPassword(sc.nextLine());

        registrationDao.add(registration);
        
    }

    public void retrieveDetailById() throws SQLException {
        System.out.print("Enter the registration ID you want to retrieve: ");
        int registrationId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        Registration registration = registrationDao.getById(registrationId);
        if (registration != null) {
            System.out.println("Registration details:");
            System.out.println(registration);
        } else {
            System.out.println("Registration with ID " + registrationId + " does not exist.");
        }
    }

    public void retrieveAllDetails() throws SQLException {
        System.out.println("All Registration Details:");
        registrationDao.getAll();
    }

    public void updateDetails() throws SQLException {
        System.out.print("Enter the registration ID you want to update: ");
        int registrationId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        // Retrieve the existing registration from the database
        Registration existingRegistration = registrationDao.getById(registrationId);

        if (existingRegistration == null) {
            System.out.println("Registration with ID " + registrationId + " does not exist.");
            return;
        }

        System.out.println("Existing Details:");
        System.out.println(existingRegistration);

        // Prompt the user for updated details
        System.out.print("New name : ");
        String newName = sc.nextLine().trim(); // Trim leading and trailing whitespace
        if (!newName.isEmpty()) {
            existingRegistration.setName(newName);
        }

        System.out.print("New email : ");
        String newEmail = sc.nextLine().trim();
        if (!newEmail.isEmpty()) {
            existingRegistration.setEmail(newEmail);
        }

        System.out.print("New password : ");
        String newPassword = sc.nextLine().trim();
        if (!newPassword.isEmpty()) {
            existingRegistration.setPassword(newPassword);
        }

        // Update the registration in the database
        registrationDao.update(existingRegistration);

        System.out.println("Details updated successfully.");
    }

    public void deleteDetail() throws SQLException {
        System.out.print("Enter the registration ID you want to delete: ");
        int registrationId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        registrationDao.delete(registrationId);
    }
    
    
    public boolean checkCredintials()throws SQLException {
    	System.out.print("email : ");
    	String email=sc.nextLine();
    	System.out.print("password : ");
    	String password=sc.nextLine();
    	return rao.checkRegistrationCredentials(email,password);
    }

    
}

