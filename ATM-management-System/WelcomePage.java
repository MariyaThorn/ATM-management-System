import java.util.Scanner;

public class WelcomePage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String reply;
        CustomerAuthentication obj = new CustomerAuthentication();

        do {
            System.out.println("===============================");
            System.out.println("         ATM SYSTEM");
            System.out.println("===============================");
            System.out.println("Press 1 -> Admin Page");
            System.out.println("Press 2 -> Customer Page");
            System.out.println("Press 3 -> Exit");
            System.out.println("Please select the option: ");
            
            int pageoption = sc.nextInt();
            switch (pageoption) {
                case 1:
                    System.out.println("Enter admin username: ");
                    String adminUsername = sc.next();
                    System.out.println("Enter admin PIN: ");
                    String adminPin = sc.next();
                    obj.viewallaccount(adminUsername, adminPin);
                    break;
                    
                case 2:
                	System.out.println("===============================");
                	System.out.println("    Welcome to Customer Page");
                	System.out.println("===============================");
                    System.out.println("Press 1 -> Sign Up");
                    System.out.println("Press 2 -> Sign In \n");
                    int customerOption = sc.nextInt();
                    switch (customerOption) {
                        case 1:
                            obj.signup();
                            break;
                            
                        case 2:
                            String signInResult = obj.signin();
                            if (signInResult.equals("Y")) {
                                System.out.println("Invalid username or PIN. Please try again.");
                            } else {
                                System.out.println("========================");
                                System.out.println("    Login successful!");
                                System.out.println("========================");
                                String replys;
                                do {
                                    System.out.println("Press 1 -> Withdraw Money ");
                                    System.out.println("Press 2 -> Deposit Money ");
                                    System.out.println("Press 3 -> Fast Cash ");
                                    System.out.println("Press 4 -> ATM Help ");
                                    System.out.println("Press 5 -> View Account ");
                                    System.out.println("Press 6 -> Go back to Page Option ");
                                    System.out.print("Please select the option from 1-6: \n");
                                    int choice = sc.nextInt();
                                    switch (choice) {
                                        case 1:
                                            obj.withdraw();
                                            break;
                                        case 2:
                                            obj.deposit();
                                            break;
                                        case 3:
                                            obj.fastcash();
                                            break;
                                        case 4:
                                            obj.ATM();
                                            break;
                                        case 5:
                                            obj.viewaccount();
                                            break;
                                        case 6:
                                            System.out.println("Returning to Customer Page...");
                                            break;
                                        default:
                                            System.out.println("Invalid option!");
                                    }
                                    if (choice == 6) {
                                        break;
                                    }
                                    System.out.println("Want to Continue? type y = continue\n" + 
													   "                  type n = stop ");
                                    replys = sc.next();
                                } while (replys.equalsIgnoreCase("y"));
                            }
                            break;
                            
                        default:
                            System.out.println("Invalid option!");
                    }
                    break;
                    
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid option!");
            }
            System.out.println("Do you want to continue? Y/N");
            reply = sc.next();
        } while (reply.equalsIgnoreCase("y"));
        sc.close();
    }
}