package test;

import login.ForgotUsernameScreen;
import login.UserAccountManager;
import junit.framework.TestCase;


public class ForgotUsernameScreenTest extends TestCase {

    UserAccountManager userAccountManager = new UserAccountManager();

    public void testForgotUsernameEmail(){
        userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
        ForgotUsernameScreen testScreen = new ForgotUsernameScreen("test", userAccountManager);

        assertTrue(userAccountManager.doesUserNameExist("admin"));
        assertFalse(userAccountManager.doesUserNameExist("Billy Bob"));
        assertFalse(testScreen.getSent());
        testScreen.makeEmail("admin@aol.com", "admin");
        assertTrue(testScreen.getSent());
    }
}
