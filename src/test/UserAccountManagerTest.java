package test;

import junit.framework.TestCase;

import login.UserAccountManager;


public class UserAccountManagerTest extends TestCase {

	private UserAccountManager userAccountManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.addUserAccount("admin", "123456");
	}

	public void testExistingUserAccount() {
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertTrue(userAccountManager.doesAccountExist("admin", "123456"));
	}

	public void testNonExistantUserAccount() {
		assertFalse(userAccountManager.doesUserNameExist("BSU"));
		assertFalse(userAccountManager.doesAccountExist("admin", "hello"));
	}

}
