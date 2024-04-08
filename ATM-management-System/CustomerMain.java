import java.io.Serializable;
import java.util.Objects;
public class CustomerMain implements Serializable {
    private int customerid;
    private int balance;  
    private String customername;
    private String address;
    private String pin;
    private String phoneno;
    private String dob;
    
    public int getcustomerid() {
        return customerid;
    }
    public void setcustomerid(int customerid) {
        this.customerid = customerid;
    }
    
    public String getcustomername() {
        return customername;
    }
    public void setcustomername(String customername) {
        this.customername = customername;
    }
    
    public String getaddress() {
        return address;
    }
    public void setaddress(String address) {
        this.address = address;
    }
    
    public String getpin() {
        return pin;
    }
    public void setpin(String pin) {
        this.pin = pin;
    }
    
    public String getphoneno() {
        return phoneno;
    }

    public void setphoneno(String phoneno) {
        if (isValidPhoneNumber(phoneno)) {
            this.phoneno = phoneno;
        } else {
            throw new IllegalArgumentException("Invalid phone number format");
        }
    }
    
    public String getdob() {
        return dob;
    }
    public void setdob(String dob) {
        
        if (isValidDateOfBirth(dob)) {
            this.dob = dob;
        } else {
            
            throw new IllegalArgumentException("Invalid date of birth format");
        }
    }
    public int getbalance() {
        return balance;
    }
    public void setbalance(int balance) {
        this.balance = balance;
    }
    
    
    private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{3}-\\d{3}-\\d{3}$";
        return phoneNumber.matches(phoneRegex);
    }
    
    
    private boolean isValidDateOfBirth(String dob) {
        String dobRegex = "^(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])/(19|20)\\d{2}$";
        return dob.matches(dobRegex);
      
    }
    
    public static boolean isAdminAuthenticated(String adminusername, String adminpin) {
        String adminUsername = "admin";
        String adminPin = "admin123";

        return Objects.equals(adminusername, adminUsername) && Objects.equals(adminpin, adminPin);
    }
}
