package login;

public class UserAccount {
	
	private String userName;
	private String password;
	
	public UserAccount(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassworde(String password) {
		this.password = password;
	}	

    public boolean isValidCredential(String userName, String password) {
         return matchUserName(userName) && matchPassword(password);
    }
    
    public boolean matchUserName(String userName) {
         return userName != null && userName.equals(this.userName);
    }
    
    private boolean matchPassword(String password) {
        return password != null && password.equals(this.password);
   }

}
