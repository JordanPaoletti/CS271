package login;
import java.util.ArrayList;

public class UserAccountManager {
	
    private ArrayList<UserAccount> userAccounts;
    private int invalidAttemptsNum;
    private boolean locked;
    
    public UserAccountManager() {
        userAccounts = new ArrayList<UserAccount>();
    }
    
    public void addUserAccount(String userName, String password, String email){
    	if (!doesUserNameExist(userName))
    		userAccounts.add(new UserAccount(userName,password, email));
    }
    
    public boolean doesAccountExist(String userName, String password) {
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.isValidCredential(userName, password))    
    			return true;   
       return false;
    }
    
    public boolean doesUserNameExist(String userName){
    	for (UserAccount userAccount: userAccounts)
    		if(userAccount.matchUserName(userName))   
    			return true;   
       return false;
    }

    public String findUsername(String email){
        for (UserAccount userAccount: userAccounts){
            if (userAccount.getEmail().equals(email)){
                return userAccount.getUserName();
            }
        }
        return "No account is associated with this email address";
    }

    public void setInvalidAttemptsNum(){
        invalidAttemptsNum++;
        if (invalidAttemptsNum >= 5) {
            locked = true;
        }
    }

    public void setInvalidAttemptsNum(int nums){
        for(int i = 0; i < nums; i++) {
            this.invalidAttemptsNum++;
        }
        if (invalidAttemptsNum >= 5){
            locked = true;
        }
    }

    public int getInvalidAttemptsNum(){
        return invalidAttemptsNum;
    }

    public boolean isLockedOut(){
        return locked;
    }
}
