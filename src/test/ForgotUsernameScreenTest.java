package test;

import login.ForgotUsernameScreen;
import login.UserAccountManager;
import junit.framework.TestCase;


public class ForgotUsernameScreenTest extends TestCase {

    UserAccountManager userAccountManager = new UserAccountManager();

    public void testForgotUsernameEmail(){
        userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
        assertTrue(userAccountManager.doesUserNameExist("admin"));
        assertFalse(userAccountManager.doesUserNameExist("Billy Bob"));
        ForgotUsernameScreen screen = new ForgotUsernameScreen("test", userAccountManager);
        screen.makeEmail("admin@aol.com", "admin");
        assertTrue(screen.getSent());
    }
}
