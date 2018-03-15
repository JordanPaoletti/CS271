package test;

import login.ForgotPasswordScreen;
import login.UserAccountManager;
import java.util.regex.Pattern;
import junit.framework.TestCase;

public class FogotPasswordScreenTest extends TestCase {

    UserAccountManager userAccountManager = new UserAccountManager();

    /*tests the emailer feature*/
    public void testForgotPassEmailSent(){
        userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
        ForgotPasswordScreen testScreen = new ForgotPasswordScreen("test", userAccountManager);
        assertFalse(testScreen.getEmailSent());
        testScreen.forgotPassMailer("admin@aol.com", "admin");
        assertTrue(testScreen.getEmailSent());
    }

    /*Tests code emailed to users to during a forgot password session*/
    public void testResetCode(){
        userAccountManager = new UserAccountManager();
        userAccountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
        String regex = "^[\\p{IsLetter}\\p{Digit}_]+$";
        ForgotPasswordScreen testScreen = new ForgotPasswordScreen("test", userAccountManager);

        //test emailed code and given code match
        String code = testScreen.forgotPassMailer("admin@aol.com", "admin");
        assertTrue(Pattern.compile(regex).matcher(code).matches());

        //tests invalid coxe
        //yeilds error message stating "reset code is incorrect"
        assertFalse(testScreen.codeMatchCheck("5FE83DWF7"));

        //test code has only letters or digits and is length 9
        assertTrue(code.length()==9);
        assertTrue(testScreen.getEmailSent());
        assertTrue(testScreen.codeMatchCheck(code));


    }

}
