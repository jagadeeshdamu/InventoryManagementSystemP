package inventoryManagementSytem.Main;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManagementSytem.AllInstance.*;

public class BussinessMainApplication {
    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        
        // Create all tables
        CreateAllTables.createAllTables();
        
        System.out.println("-------- Welcome to the Inventory Management System --------");
        boolean running = true;
        
        while (running) {
        	
            System.out.println("1.registration");
    		System.out.println("2.login");
    		System.out.println("3.purchaseOrders");
    		System.out.println("4.product");
    		System.out.println("5.adminlogin");
    		System.out.println("6.exist");
    		System.out.print("please choose option : ");
            String userInput = scanner.next();
            
            // Consume newline character
            scanner.nextLine();
            
            switch (userInput) {
                case "1":
                    main.registrations();
                    break;
                case "2":
                    main.login();
                    break;
                case "3":
                    main.purchaseOrder();
                    break;
                case "4":
                    main.product();
                    break;
                case "5":
                    main.admin();
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            
        }
        
        // Close the scanner
        scanner.close();
       
    }
}
