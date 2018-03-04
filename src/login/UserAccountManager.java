package login;
import java.util.ArrayList;


public class UserAccountManager {
	
    private ArrayList<UserAccount> userAccounts;
    
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
}
