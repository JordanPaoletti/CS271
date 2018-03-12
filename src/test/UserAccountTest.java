package test;

import junit.framework.TestCase;

import login.UserAccount;
import login.UserAccountManager;

public class UserAccountTest extends TestCase {

	private UserAccount userAccount;
	
	public void testNewAccount() {
		userAccount = new UserAccount("admin", "Ab123456", "admin@aol.com");
		assertNotNull(userAccount);
		assertTrue(userAccount.matchUserName("admin"));
		assertTrue(userAccount.getEmail().equals("admin@aol.com"));
		assertTrue(userAccount.isValidCredential("admin", "Ab123456"));
	}

	public void testBadAccount() {
	    boolean thrown = false;
	    try {
	        new UserAccount("", "Ab123456", "a@a.com");
        } catch (IllegalArgumentException e) {
	        thrown = true;
        }
        assertTrue("invalid username", thrown);

        thrown = false;
        try {
            new UserAccount("admin", "123456", "a@a.com");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("invalid password", thrown);

        thrown = false;
        try {
            new UserAccount("admin", "Ab123456", "@a.com");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue("invalid email", thrown);
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

    public void testValidityMessage() {
	    //get valid messages in easy use form
        String u = UserAccount.USERNAME_MESSAGE;
        String p = UserAccount.PASSWORD_MESSAGE;
        String e = UserAccount.EMAIL_MESSAGE;
        String n = UserAccount.NEWLINE_PADDING;

        assertTrue(UserAccount.getValidityMessage(
                "aValidName", "ValidPass1", "valid@email.com") == null);
        assertEquals(UserAccount.getValidityMessage("","",""),
                u+n+p+n+e+n);
        assertEquals(UserAccount.getValidityMessage(
                "ValidName", "", ""),
                p+n+e+n
        );
        assertEquals(UserAccount.getValidityMessage(
                "", "ValidPass1", ""),
                u+n+e+n
        );
        assertEquals(UserAccount.getValidityMessage(
                "", "", "a@a.com"),
                u+n+p+n
        );
        assertEquals(UserAccount.getValidityMessage(
                "ValidName", "ValidPass1", ""),
                e+n
        );
        assertEquals(UserAccount.getValidityMessage(
                "ValidName", "", "a@a.com"),
                p+n
        );
        assertEquals(UserAccount.getValidityMessage(
                "", "ValidPass1", "a@a.com"),
                u+n
        );
    }
}
