package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import inventoryManageMentSystem.CrudOperations;
import inventoryManagementSytem.product.Product;
import inventoryManagementSytem.product.ProductDao;

public class Products {
	Scanner sc=new Scanner(System.in);
	CrudOperations<Product> productDao=new ProductDao();
	
	Product product=new Product();
	public void addDetails() throws SQLException {
		
		//interface implement
		
		System.out.print("product name : ");
        product.setName(sc.nextLine()); // Read the entire line to handle spaces
        System.out.print("product Description : ");
        product.setDescription(sc.nextLine());
        System.out.print("product Category : ");
        product.setCategory(sc.nextLine());
        System.out.print("product Price : ");
        product.setPrice(Integer.parseInt(sc.nextLine())); // Parse the input string to an int
        System.out.print("product Quantity : ");
        product.setQuantity(Integer.parseInt(sc.nextLine())); // Parse the input string to an int
        
        productDao.add(product);
        System.out.println("details insert successfully");
		
	}
	
	public void retrieveDetailById() throws SQLException {
		System.out.print("Product Id : ");
		System.out.println(productDao.getById(sc.nextInt()));
	}
	
	public void retrieveAllDetails() throws SQLException {
		System.out.println("Product Table Details : ");
		productDao.getAll();
	}
	
	public void updateDetails() throws SQLException {
	    System.out.print("Enter the product ID you want to update: ");
	    int productId = sc.nextInt();
	    sc.nextLine(); // Consume newline character

	    // Retrieve the existing product from the database
	    Product existingProduct = productDao.getById(productId);

	    if (existingProduct == null) {
	        System.out.println("Product with ID " + productId + " does not exist.");
	        return;
	    }

	    System.out.println("Existing Details:");
	    System.out.println(existingProduct);

	    // Prompt the user for updated details
	    System.out.print("New product name : ");
	    String newName = sc.nextLine().trim(); // Trim leading and trailing whitespace
	    if (!newName.isEmpty()) {
	        existingProduct.setName(newName);
	    }

	    System.out.print("New product Description (press Enter to keep current): ");
	    String newDescription = sc.nextLine().trim();
	    if (!newDescription.isEmpty()) {
	        existingProduct.setDescription(newDescription);
	    }

	    System.out.print("New product Category : ");
	    String newCategory = sc.nextLine().trim();
	    if (!newCategory.isEmpty()) {
	        existingProduct.setCategory(newCategory);
	    }

	    System.out.print("New product Price : ");
	    String newPriceStr = sc.nextLine().trim();
	    if (!newPriceStr.isEmpty()) {
	        double newPrice = Double.parseDouble(newPriceStr);
	        existingProduct.setPrice(newPrice);
	    }

	    System.out.print("New product Quantity : ");
	    String newQuantityStr = sc.nextLine().trim();
	    if (!newQuantityStr.isEmpty()) {
	        int newQuantity = Integer.parseInt(newQuantityStr);
	        existingProduct.setQuantity(newQuantity);
	    }

	    // Update the product in the database
	    productDao.update(existingProduct);

	    System.out.println("Details updated successfully.");
	}

	public void deleteDetail() throws SQLException {
		productDao.delete(2);
	}
	
	

}
