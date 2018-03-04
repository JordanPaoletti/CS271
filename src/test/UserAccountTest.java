package test;

import junit.framework.TestCase;

import login.UserAccount;

public class UserAccountTest extends TestCase {

	private UserAccount userAccount;
	
	public void testNewAccount() {
		userAccount = new UserAccount("admin", "123456", "admin@aol.com");
		assertNotNull(userAccount);
		assertTrue(userAccount.matchUserName("admin"));
		assertTrue(userAccount.isValidCredential("admin", "123456"));
	}

	public void testValidUsername() {
	    assertFalse("Username too short", UserAccount.isValidUsername("a"));
        assertFalse("Username too long", UserAccount.isValidUsername(new String(new char[500])));
        assertFalse("Invalid characters in username", UserAccount.isValidUsername("hello world"));
        assertFalse("Invalid characters in username", UserAccount.isValidUsername("hello\nworld"));
        assertFalse("Invalid characters in username", UserAccount.isValidUsername("!helloworld"));
        assertTrue(UserAccount.isValidUsername("helloworld"));
        assertTrue(UserAccount.isValidUsername("helloworld92"));
        assertTrue(UserAccount.isValidUsername("hello_world"));
    }

    public void testValidEmail() {
	    assertFalse(UserAccount.isValidEmail("a"));
        assertFalse(UserAccount.isValidEmail("a.com"));
        assertFalse(UserAccount.isValidEmail("@a.com"));
        assertFalse(UserAccount.isValidEmail("acom@"));
        assertTrue(UserAccount.isValidEmail("apple@a.com"));
        assertTrue(UserAccount.isValidEmail("john.doe@u.boisestate.edu"));
    }

    public void testValidPassWord() {
	    assertFalse(UserAccount.isValidPassword(""));
        assertFalse(UserAccount.isValidPassword(new String(new char[500])));
        assertFalse(UserAccount.isValidPassword("helloworld"));
        assertFalse(UserAccount.isValidPassword("Helloworld"));
        assertFalse(UserAccount.isValidPassword("helloworld1"));
        assertFalse(UserAccount.isValidPassword("!helloworld1"));

        assertTrue(UserAccount.isValidPassword("Helloworld1"));
        assertTrue(UserAccount.isValidPassword("HelloW_^^##@orld1"));
    }

}
