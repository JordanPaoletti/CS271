package test;

import login.ForgotPasswordScreen;
import login.UserAccountManager;
import java.util.regex.Pattern;
import junit.framework.TestCase;

public class FogotPasswordScreenTest extends TestCase {

    UserAccountManager userAccountManager = new UserAccountManager();

    public void testForgotPassEmailSent(){
        userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
        ForgotPasswordScreen testScreen = new ForgotPasswordScreen("test", userAccountManager);
        assertFalse(testScreen.getEmailSent());
        testScreen.forgotPassMailer("admin@aol.com", "admin");
        assertTrue(testScreen.getEmailSent());
    }

    public void testResetCode(){
        userAccountManager = new UserAccountManager();
        userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
        String regex = "^[\\p{IsLetter}\\p{Digit}_]+$";
        ForgotPasswordScreen testScreen = new ForgotPasswordScreen("test", userAccountManager);

        //test emailed code and given code match
        String code = testScreen.forgotPassMailer("admin@aol.com", "admin");
        assertTrue(Pattern.compile(regex).matcher(code).matches());
        //yeilds error message stating reset code is incorrect
        assertFalse(testScreen.codeMatcher("5FE83DWF7"));
        //test code has only letters or digits and is 9 letters
        assertTrue(code.length()==9);
        assertTrue(testScreen.getEmailSent());
        assertTrue(testScreen.codeMatcher(code));


    }

}
