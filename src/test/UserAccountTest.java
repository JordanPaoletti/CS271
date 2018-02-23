package test;

import junit.framework.TestCase;

import login.UserAccount;

public class UserAccountTest extends TestCase {

	private UserAccount userAccount;
	
	public void testNewAccount() {
		userAccount = new UserAccount("admin", "123456");
		assertNotNull(userAccount);
		assertTrue(userAccount.matchUserName("admin"));
		assertTrue(userAccount.isValidCredential("admin", "123456"));
	}

}
