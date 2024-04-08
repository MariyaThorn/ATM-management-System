import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAuthentication {
    private ArrayList<CustomerMain> customerlist = new ArrayList<CustomerMain>();
	private Scanner sc = new Scanner(System.in);
	private boolean isAuthenticated = false;
	private int cash;
	private int customerindexno = -1;
	private String x;
	private int options;
	private int option;

	public CustomerAuthentication() {
		readArrayListFromFile();
	}
	public void signup()
	{
		String reply;
			boolean isDuplicate = false;
			
			CustomerMain obj = new CustomerMain();
			System.out.println("Enter account number: ");
			int id = sc.nextInt();
			sc.nextLine();
			obj.setcustomerid(id);
			
			for (CustomerMain customer : customerlist) {
				if(customer.getcustomerid() == id) {
					isDuplicate = true;
					break;
				}
			}
			
			if(isDuplicate) {
				System.out.println("This account already registered!");
				return;
			}
			
			obj.setcustomerid(id);
			
			System.out.println("Enter Customer Name: ");
			obj.setcustomername(sc.nextLine());
			
			System.out.println("Enter your address:  ");
			obj.setaddress(sc.nextLine());
			
			System.out.println("Enter your pin code: ");
			obj.setpin(sc.nextLine());
			
			System.out.println("Enter your phone number ###-###-###: ");
			obj.setphoneno(sc.nextLine());
			
			System.out.println("Enter your date of birth MM/DD/YYYY: ");
			obj.setdob(sc.nextLine());
			
			System.out.println("Enter your Amount you want to put in: ");
			obj.setbalance(sc.nextInt());
		
			customerlist.add(obj);
			writeArrayListToFile();

}

	public void writeArrayListToFile()
	{
	  try	
	  {
		FileOutputStream fout = new FileOutputStream("D:\\CustomerRegistration.txt");
		ObjectOutputStream oout = new ObjectOutputStream(fout);
		//WRITE ARRAYLIST OBJECT INTO FILE 
		oout.writeObject(customerlist);
	  
	  }catch(FileNotFoundException e){System.out.println(e.toString());}
	   catch(IOException e) {System.out.print(e.toString());}
	   catch(Exception e) {System.out.print(e.toString());}
	}
	
	public void readArrayListFromFile()
	{
	  try
	  {
		FileInputStream fin=new FileInputStream("D:\\CustomerRegistration.txt");
		ObjectInputStream oin=new ObjectInputStream(fin);
		//READ DATA FROM FILE AND STORE IT IN ARRAYLIST 
		customerlist = (ArrayList<CustomerMain>)oin.readObject();
	  
	
	  }catch(FileNotFoundException e){System.out.println(e.toString());}
	   catch(IOException e) {System.out.print(e.toString());}
	   catch(Exception e) {System.out.print(e.toString());}
	  
	}
	
	public String signin() {
	    // Read customer data from file
		isAuthenticated = false;
	    readArrayListFromFile();
	    
	    sc.nextLine();
	    // Proceed with sign-in process
	    System.out.println("Enter your username: ");
	    String username = sc.nextLine();
	
	    System.out.println("Enter your pin code: ");
	    String pin = sc.nextLine();
	
	    for(int i=0; i< customerlist.size(); i++)
	    {
	    	CustomerMain tempobj = customerlist.get(i);
	        if (tempobj.getcustomername().equals(username) && tempobj.getpin().equals(pin)) {
	        	customerindexno = i;
	            isAuthenticated = true;
	            break;
	        }
	    }
	    
	    // Display authentication result
	    if (isAuthenticated) {
	       return "X";
	    } 
	    else {
	       return "Y";
	    	}
	    
		}

	public void withdraw() {	
		System.out.println("Enter the Amount that you want to withdraw: ");
		int cashs= sc.nextInt();	
		CustomerMain tempobj = customerlist.get(customerindexno);
			cash =tempobj.getbalance()- cashs;
			if (cash>=0)
			{
				tempobj.setbalance(cash);		
				customerlist.set(customerindexno, tempobj);		
				System.out.println("You Have Withdrawn:"+ cashs);
			}
			else {
				System.out.println("You Do Not Have Enough Amount To withdraw");
			}
			writeArrayListToFile();
		}


	public void deposit() {	
		System.out.println("Enter the Amount that you want to Deposit: ");
		int cashs= sc.nextInt();	
		CustomerMain tempobj = customerlist.get(customerindexno);
			cash =tempobj.getbalance()+ cashs;	
			tempobj.setbalance(cash);	
			customerlist.set(customerindexno, tempobj);		
			System.out.println("You Have Deposited:"+ cashs);	
			writeArrayListToFile();
		}


	
	public void fastcash() {
		System.out.println("Amount of cash you want to withdrawn: ");
		System.out.println("Press 1 to withdraw 100");
		System.out.println("Press 2 to withdraw 300");
		System.out.println("Press 3 to withdraw 500");
		System.out.println("Press 4 to withdraw 1000");
		System.out.println("Enter t99he option you want to select from 1-4\n");
		options = sc.nextInt();
		CustomerMain tempobj = customerlist.get(customerindexno);
		switch(options) {
			case 1: 
				cash = tempobj.getbalance() - 100;
				System.out.println("100 will be withdrawn from your account");
				break;
			case 2:
				cash = tempobj.getbalance() - 300;
				System.out.println("300 will be withdrawn from your account");
				break; 
			case 3: 
				cash = tempobj.getbalance() - 500;
				System.out.println("500 will be withdrawn from your account");
				break;
			case 4: 
				cash = tempobj.getbalance() - 1000;
				System.out.println("1000 will be withdrawn from your account");
				break;
			default: System.out.println("Please select the option within the range");
				return;
			}
			if (cash>=0)
			{
				tempobj.setbalance(cash);		
				customerlist.set(customerindexno, tempobj);		
			}
			else {
				System.out.println("You Do Not Have Enough Amount To withdraw");
			}
			writeArrayListToFile();
	 }
	
	public void changePin() {
	    
	    System.out.println("Enter your username: ");
	    String username = sc.nextLine();

	    System.out.println("Enter your old PIN code: ");
	    String oldPin = sc.nextLine();

	   
	    isAuthenticated = false;
	    for (int i = 0; i < customerlist.size(); i++) {
	        CustomerMain tempobj = customerlist.get(i);
	        if (tempobj.getcustomername().equals(username) && tempobj.getpin().equals(oldPin)) {
	            customerindexno = i;
	            isAuthenticated = true;
	            break;
	        }
	    }

	    
	    if (isAuthenticated) {
	        System.out.println("Enter your new PIN code: ");
	        String newPin = sc.nextLine();

	        
	        CustomerMain tempobj = customerlist.get(customerindexno);
	        tempobj.setpin(newPin);
	        customerlist.set(customerindexno, tempobj);

	        
	        writeArrayListToFile();

	        System.out.println("PIN changed successfully!");
	    }
	    else {
	        System.out.println("Authentication failed. Please check your username and PIN.");
	    }
	}
	
	
	public void ATM() {
	    String reply; 
	    do {
	        if (!isAuthenticated) {
	            System.out.println("Please sign in to access your account.");
	            signin();
	            if (!isAuthenticated) {
	                System.out.println("Authentication failed. Unable to proceed.");
	                return;
	            }
	        }

	        System.out.println("=====================");
	        System.out.println("      Help Center    ");
	        System.out.println("=====================");
	        System.out.println("Press 1 for ATM Guidelines");
	        System.out.println("Press 2 for Help Services");
	        System.out.println("Press 3 to Change PIN");
	        System.out.println("Press 4 to Delete Account");
	        System.out.println("Press 5 to Go back to MENU page");
	        System.out.println("Please select the option within the range");
	        option = sc.nextInt();
	        sc.nextLine();

	        switch (option) {
	            case 1: 
	                System.out.println("Welcome to our system!\r\n "
	                    + "To ensure a smooth and secure experience, please follow these guidelines:\r\n"
	                    + "- Account Access: Use your unique credentials to log in securely. Never share your username or password with anyone.\n"
	                    + "- Navigation: Familiarize yourself with the system layout. Our intuitive interface makes it easy to navigate between different features and services.\n"
	                    + "- Security Measures: We prioritize the safety of your transactions. Our system employs encryption and other advanced security measures to protect your data. \n"
	                    + "- Transaction Confirmation: Always review your transactions before confirming. Double-check the recipient details and transaction amount to avoid errors.\n"
	                    + "- Contact Support: Should you encounter any issues or have questions, our customer support team is readily available to assist you.\r\n"
	                    + "- Contact information: +855 97XX XXXX\r\n"
	                );
	                break;
	                
	            case 2: 
	                System.out.println("Our ATMs offer a range of convenient services to meet your banking needs:\r\n"
	                    + "- Deposits: Easily deposit cash or checks into your account using our ATMs. Follow the on-screen instructions to complete your deposit securely.\r\n"
	                    + "- Withdrawals: Access your funds 24/7 by withdrawing cash from our ATMs. Simply insert your card and follow the prompts to withdraw the desired amount.\r\n"
	                    + "- Balance Inquiries: Check your account balance at any time by using our ATMs. This service provides you with up-to-date information on your finances.\r\n"
	                );
	                break;
	            
	            case 3: 
	                changePin();
	                break;
	            
	            case 4: 
	                isAuthenticated = false;
	                readArrayListFromFile();
	                
	                System.out.println("Enter your username: ");
	                String username = sc.nextLine();
	                    
	                System.out.println("Enter your PIN code: ");
	                String pin = sc.nextLine();
	                
	                for(int i=0; i< customerlist.size(); i++) {
	                    CustomerMain tempobj = customerlist.get(i);
	                    if (tempobj.getcustomername().equals(username) && tempobj.getpin().equals(pin)) {
	                        customerindexno = i;
	                        isAuthenticated = true;
	                        break;
	                    }
	                }
	                
	                if (isAuthenticated) {
	                    CustomerMain tempobj = customerlist.get(customerindexno);
	                    tempobj.getcustomerid();
	                    customerlist.remove(tempobj);
	                    System.out.println("Your account has been deleted successfully!");
	                } else {
	                    System.out.println("Authentication failed. Unable to delete account.");
	                }
	                break;
	                
	            case 5: 
	                System.out.println("Go back to menu page...");
	                return;
	                
	            default: 
	                System.out.println("Please select the option within the range!");
	        }
	            
	        System.out.println("Go back to ATM page? Y/N ");
	        reply = sc.next();
	        sc.nextLine();
	    } while (reply.equalsIgnoreCase("y"));

	    sc.close();
	}
	public void viewaccount()	{
		CustomerMain tempobj = customerlist.get(customerindexno);
			System.out.println("User's ID: "+ tempobj.getcustomerid());
			System.out.println("User's Name: "+ tempobj.getcustomername());
			System.out.println("User's Balance: "+ tempobj.getbalance());
			System.out.println("User's Address: "+ tempobj.getaddress());
			System.out.println("User's Phone Number: "+ tempobj.getphoneno());
			System.out.println("User's Date Of Birth: "+ tempobj.getdob());
		
		}

	public void viewallaccount(String adminUsername, String adminPin) {
	
	    if (CustomerMain.isAdminAuthenticated(adminUsername, adminPin)) {
	        System.out.println("All User Data:");
	        for (CustomerMain customer : customerlist) {
	            System.out.println("Customer ID: " + customer.getcustomerid());
	            System.out.println("Customer Name: " + customer.getcustomername());
	            System.out.println("Customer PIN: " + customer.getpin());
	            System.out.println("Address: " + customer.getaddress());
	            System.out.println("Phone Number: " + customer.getphoneno());
	            System.out.println("Date of Birth: " + customer.getdob());
	            System.out.println("Balance: " + customer.getbalance());
	            System.out.println();
	        }
	    } 
	    
	    else 
	        System.out.println("Admin authentication failed. Access denied.");
	    	
		}
	}



