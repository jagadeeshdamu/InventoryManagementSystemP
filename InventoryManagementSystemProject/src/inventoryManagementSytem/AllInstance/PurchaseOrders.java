package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.purchaseProduct.PurchaseOrder;
import inventoryManagementSytem.purchaseProduct.PurchaseOrderDao;

public class PurchaseOrders {
    Scanner sc = new Scanner(System.in);
    CrudOperations<PurchaseOrder> purchaseOrderDao = new PurchaseOrderDao();
    
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    
    public void addDetails() throws SQLException {
        

        System.out.print("Enter product name: ");
        purchaseOrder.setProductName(sc.nextLine());

        System.out.print("Enter quantity: ");
        purchaseOrder.setQuantity(Integer.parseInt(sc.nextLine()));

        purchaseOrderDao.add(purchaseOrder);
        
    }

    public void retrieveDetailById() throws SQLException {
        System.out.print("Enter the purchase order ID you want to retrieve: ");
        int purchaseOrderId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        PurchaseOrder purchaseOrder = purchaseOrderDao.getById(purchaseOrderId);
        if (purchaseOrder != null) {
            System.out.println("Purchase order details:");
            System.out.println(purchaseOrder);
        } else {
            System.out.println("Purchase order with ID " + purchaseOrderId + " does not exist.");
        }
    }

    public void retrieveAllDetails() throws SQLException {
        System.out.println("All Purchase Order Details:");
        purchaseOrderDao.getAll();
    }

    public void updateDetails() throws SQLException {
        System.out.print("Enter the purchase order ID you want to update: ");
        int purchaseOrderId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        // Retrieve the existing purchase order from the database
        PurchaseOrder existingPurchaseOrder = purchaseOrderDao.getById(purchaseOrderId);

        if (existingPurchaseOrder == null) {
            System.out.println("Purchase order with ID " + purchaseOrderId + " does not exist.");
            return;
        }

        System.out.println("Existing Details:");
        System.out.println(existingPurchaseOrder);

        // Prompt the user for updated details
        System.out.print("New product name : ");
        String newProductName = sc.nextLine().trim(); // Trim leading and trailing whitespace
        if (!newProductName.isEmpty()) {
            existingPurchaseOrder.setProductName(newProductName);
        }

        System.out.print("New quantity : ");
        String newQuantityStr = sc.nextLine().trim();
        if (!newQuantityStr.isEmpty()) {
            int newQuantity = Integer.parseInt(newQuantityStr);
            existingPurchaseOrder.setQuantity(newQuantity);
        }

        // Update the purchase order in the database
        purchaseOrderDao.update(existingPurchaseOrder);

        System.out.println("Details updated successfully.");
    }

    public void deleteDetail() throws SQLException {
        System.out.print("Enter the purchase order ID you want to delete: ");
        int purchaseOrderId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        purchaseOrderDao.delete(purchaseOrderId);
    }

    
}
