package test;

import junit.framework.TestCase;

import login.UserAccountManager;


public class UserAccountManagerTest extends TestCase {

	private UserAccountManager userAccountManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		userAccountManager = new UserAccountManager();
		userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
	}

	public void testExistingUserAccount() {
		assertTrue(userAccountManager.doesUserNameExist("admin"));
		assertTrue(userAccountManager.doesAccountExist("admin", "Ab123456"));
	}

	public void testNonExistentUserAccount() {
		assertFalse(userAccountManager.doesUserNameExist("BSU"));
		assertFalse(userAccountManager.doesAccountExist("admin", "A1hello"));
	}

	public void testLockedOut() {
		userAccountManager = new UserAccountManager();
		userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
		userAccountManager.setInvalidAttemptsNum(2);
		assertFalse(userAccountManager.isLockedOut());
		userAccountManager.setInvalidAttemptsNum();
		assertFalse(userAccountManager.isLockedOut());
		userAccountManager.setInvalidAttemptsNum(5);
		assertTrue(userAccountManager.isLockedOut());
		userAccountManager.setInvalidAttemptsNum();
		assertTrue(userAccountManager.isLockedOut());

	}
	public void testLogin(){
		userAccountManager = new UserAccountManager();
		userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");

	}
}
