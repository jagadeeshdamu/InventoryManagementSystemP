package inventoryManagementSytem.AllInstance;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	Registrations registration=new Registrations();
	PurchaseOrders purchaseOrders=new PurchaseOrders();
	AdminLogins adminlogin=new AdminLogins();
	Products product=new Products();
	Suppliers suppliers=new Suppliers();
	CustomerAddresses customerAddresses=new CustomerAddresses();
	Logins login=new Logins();
	
	//registration
	
	public void registrations() throws SQLException {

		registration.addDetails();	
		System.out.println("✅✨ Registration successful! ✨✅");

	
	}
	
	//login
	
	public void login() throws SQLException {
		Scanner sc =new Scanner(System.in);
		System.out.println("Please login :");
		if(registration.checkCredintials()) {
			for(int pro=0;;pro++) {
			product.retrieveAllDetails();
			System.out.println("1.order");
			System.out.println("2.exist");
			
			String order=sc.next();
			
			if(order.equals("1")) {
				purchaseOrders.addDetails();
				customerAddresses.addDetails();
				System.out.println("🎉🎉🎉 Order placed successfully! 🎉🎉🎉");
			}
			else if(order.equals("2")){
				break;
			}
			sc.nextLine();
	}}
		
			
	
	}
	//product
	
	public void product() throws SQLException {
		Scanner sc =new Scanner(System.in);
		for(int po=0;;po++) {
		System.out.println("1.AddData");
		System.out.println("2.DisplayallData");
		System.out.println("3.DisplayDataById");
		System.out.println("4.DeleteDataById");
		System.out.println("5.Update");
		String operation=sc.next();
		if(operation.equals("1")) {
			product.addDetails();
		}
		else if(operation.equals("2")) {
			product.retrieveAllDetails();
		}
		else if(operation.equals("3")) {
			product.retrieveDetailById();	
		}
		else if(operation.equals("4")) {
			product.deleteDetail();
			System.out.println("🗑️✨ Data deletion successful! ✨🗑️");

		}
		else if(operation.equals("5")) {
			product.updateDetails();
		}
		
	}
	}

	//order
	public void purchaseOrder() throws SQLException {
		Scanner sc =new Scanner(System.in);
		for(int po=0;;po++) {
			System.out.println("1.AddData");
			System.out.println("2.DisplayallData");
			System.out.println("3.DisplayDataById");
			System.out.println("4.DeleteDataById");
			System.out.println("5.Update");
		String operation=sc.next();
		if(operation.equals("1")) {
			purchaseOrders.addDetails();
		}
		else if(operation.equals("2")) {
			purchaseOrders.retrieveAllDetails();
		}
		else if(operation.equals("3")) {
			purchaseOrders.retrieveDetailById();	
		}
		else if(operation.equals("4")) {
			purchaseOrders.deleteDetail();
			System.out.println("🗑️✨ Data deletion successful! ✨🗑️");

		}
		else if(operation.equals("5")) {
			purchaseOrders.updateDetails();
		}
	}
		
		
	}
   //admin
	public void admin() throws SQLException {
		Scanner sc =new Scanner(System.in);
		if(adminlogin.checkcredentials()) {
			System.out.println("👑✨ Admin login Successful! ✨👑");

		for(int j=0;;j++) {
			
			System.out.println("1.product");
			System.out.println("2.purchaseOrders");
			System.out.println("3.Admindetails");
			System.out.println("4.Registration");
			System.out.println("5.customerAdress");
			System.out.println("6.suppliers");
			System.out.println("7.exist");
			System.out.print("choose option :");
			String edit=sc.next();
		
		if(edit.equals("1")) {
			for(int p=0;;p++) {
			System.out.println("1.addData");
			System.out.println("2.deleteData");
			System.out.println("3.update");
			System.out.println("4.DisplayDataById");
			System.out.println("5.DisplayAll");
			System.out.println("6.exist");
			String pro=sc.next();
			
			if(pro.equals("1")) {
				product.addDetails();
			}
			else if(pro.equals("2")) {
				product.deleteDetail();
				System.out.println("🗑️✨ Data deletion successful! ✨🗑️");
			}
			else if(pro.equals("3")) {
				product.updateDetails();
			}
			else if(pro.equals("4")) {
				product.retrieveDetailById();
			}
			else if(pro.equals("5")) {
				product.retrieveAllDetails();
			}
			else if(pro.equals("6")) {
				break;
			}
			
		}}
		
		else if(edit.equals("7")) {
			break;
		}
		else if(edit.equals("2")) {
			for(int p=0;;p++) {
			System.out.println("1.addData");
			System.out.println("2.deleteData");
			System.out.println("3.update");
			System.out.println("4.DisplayDataById");
			System.out.println("5.DisplayAll");
			System.out.println("6.exist");
			String pro=sc.next();
			
			if(pro.equals("1")) {
				purchaseOrders.addDetails();
			}
			else if(pro.equals("2")) {
				purchaseOrders.deleteDetail();
				System.out.println("🗑️✨ Data deletion successful! ✨🗑️");
			}
			else if(pro.equals("3")) {
				purchaseOrders.updateDetails();
			}
			else if(pro.equals("4")) {
				purchaseOrders.retrieveDetailById();
			}
			else if(pro.equals("5")) {
				purchaseOrders.retrieveAllDetails();
			}
			else if(pro.equals("6")) {
				break;
			}
			
		}}
		else if(edit.equals("3")) {
			for(int p=0;;p++) {
			System.out.println("1.addData");
			System.out.println("2.deleteData");
			System.out.println("3.update");
			System.out.println("4.DisplayDataById");
			System.out.println("5.DisplayAll");
			System.out.println("6.exist");
			String pro=sc.next();
			
			if(pro.equals("1")) {
				adminlogin.addDetails();
			}
			else if(pro.equals("2")) {
				adminlogin.deleteDetail();
				System.out.println("🗑️✨ Data deletion successful! ✨🗑️");
			}
			else if(pro.equals("3")) {
				adminlogin.updateDetails();
			}
			else if(pro.equals("4")) {
				adminlogin.retrieveDetailById();
			}
			else if(pro.equals("5")) {
				adminlogin.retrieveAllDetails();
			}
			else if(pro.equals("6")) {
				break;
			}
			
		}}
		else if(edit.equals("4")) {
			for(int p=0;;p++) {
			System.out.println("1.addData");
			System.out.println("2.deleteData");
			System.out.println("3.update");
			System.out.println("4.DisplayDataById");
			System.out.println("5.DisplayAll");
			System.out.println("6.exist");
			String pro=sc.next();
			
			if(pro.equals("1")) {
				registration.addDetails();
			}
			else if(pro.equals("2")) {
				registration.deleteDetail();
				System.out.println("🗑️✨ Data deletion successful! ✨🗑️");
			}
			else if(pro.equals("3")) {
				registration.updateDetails();
			}
			else if(pro.equals("4")) {
				registration.retrieveDetailById();
			}
			else if(pro.equals("5")) {
				registration.retrieveAllDetails();
			}
			else if(pro.equals("6")) {
				break;
			}
			
		}}
		else if(edit.equals("5")) {
			for(int p=0;;p++) {
			System.out.println("1.addData");
			System.out.println("2.deleteData");
			System.out.println("3.update");
			System.out.println("4.DisplayDataById");
			System.out.println("5.DisplayAll");
			System.out.println("6.exist");
			String pro=sc.next();
			
			if(pro.equals("1")) {
				customerAddresses.addDetails();
			}
			else if(pro.equals("2")) {
				customerAddresses.deleteDetail();
				System.out.println("🗑️✨ Data deletion successful! ✨🗑️");
			}
			else if(pro.equals("3")) {
				customerAddresses.updateDetails();
			}
			else if(pro.equals("4")) {
				customerAddresses.retrieveDetailById();
			}
			else if(pro.equals("5")) {
				customerAddresses.retrieveAllDetails();
			}
			else if(pro.equals("6")) {
				break;
			}
			
		}}
		else if(edit.equals("6")) {
			for(int p=0;;p++) {
			System.out.println("1.addData");
			System.out.println("2.deleteData");
			System.out.println("3.update");
			System.out.println("4.DisplayDataById");
			System.out.println("5.DisplayAll");
			System.out.println("6.exist");
			String pro=sc.next();
			
			if(pro.equals("1")) {
				suppliers.addDetails();
			}
			else if(pro.equals("2")) {
				suppliers.deleteDetail();
				System.out.println("🗑️✨ Data deletion successful! ✨🗑️");
			}
			else if(pro.equals("3")) {
				suppliers.updateDetails();
			}
			else if(pro.equals("4")) {
				suppliers.retrieveDetailById();
			}
			else if(pro.equals("5")) {
				suppliers.retrieveAllDetails();
			}
			else if(pro.equals("6")) {
				break;
			}
			
		}}
		
		}
		
	}
	}
	
	public void option() {
		System.out.println("1.registration");
		System.out.println("2.login");
		System.out.println("3.purchaseOrders");
		System.out.println("4.product");
		System.out.println("5.adminlogin");
		System.out.println("6.exist");
		System.out.print("please choose option : ");
		
		
	}

}
