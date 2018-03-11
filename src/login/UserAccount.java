package login;

import java.util.regex.*;

import javax.swing.JOptionPane;

public class UserAccount {

    private String userName;
    private String password;
    private String email;


    public UserAccount(String userName, String password, String email) {
        if (!isValidUsername(userName) ||
            !isValidPassword(password) ||
            !isValidEmail(email)) {

            //this exception should not be used for determining whether
            //a particular credential is valid.
            //Use the public static methods for that
            throw new IllegalArgumentException("invalid account credentials");
        }

        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (!isValidUsername(userName))
            throw new IllegalArgumentException("Invalid username");
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!isValidPassword(password))
            throw new IllegalArgumentException("Invalid Password");
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email))
            throw new IllegalArgumentException("Invalid Email");
        this.email = email;
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

    //class related fields and methods
    //username related
    private static final byte USERNAME_MIN_LENGTH = 5;
    private static final byte USERNAME_MAX_LENGTH = 21;
    private static final String USERNAME_REGEX = "^[\\p{IsLetter}\\p{Digit}_]+$";

    //email regex
    private static final String EMAIL_REGEX = "^[\\p{Alpha}.]+@[\\p{Alpha}.]+";

    //password related fields
    private static final byte PASSWORD_MIN_LENGTH = 8;
    private static final short PASSWORD_MAX_LENGTH = 256;
    private static final byte PASSWORD_MIN_CAPITALS = 1;
    private static final byte PASSWORD_MIN_NUMBERS = 1;
    private static final String PASSWORD_VALID_CHARS_REGEX = "[\\p{Graph}]";
    

    /**
     * Determines whether a user name is valid or not.
     * @param name username to check
     * @return true if username is valid, false otherwise
     */
    public static boolean isValidUsername(String name) {
        if (name.length() > USERNAME_MAX_LENGTH || name.length() < USERNAME_MIN_LENGTH) {
            return false;
        }

        return Pattern.compile(USERNAME_REGEX).matcher(name).matches();
    }

    /**
     * Determines whether an email is valid or not
     * @param em email to check
     * @return true if email is valid, false otherwise
     */
    public static boolean isValidEmail(String em) {   	
        return Pattern.compile(EMAIL_REGEX).matcher(em).matches();
    }

    /**
     * Determines whether a password is valid or not
     * @param pass password to check
     * @return true if password is valid, false otherwise
     */    
    public static boolean isValidPassword(String pass) {
        if (pass.length() > PASSWORD_MAX_LENGTH || pass.length() < PASSWORD_MIN_LENGTH) {
        	return false;
        }

        int numCapitals = 0;
        int numNumbers = 0;
        int i = 0;
        Pattern p = Pattern.compile(PASSWORD_VALID_CHARS_REGEX);
        boolean hasCaps = false;
        boolean hasNums = false;
        boolean validChars = true;

        while (validChars && !(hasCaps && hasNums) && i < pass.length()) {
            char c = pass.charAt(i);

            //check for valid character
            if (!p.matcher(""+c).matches()) {
                validChars = false;
            }
            else { //check if num or digit
                if (Character.isUpperCase(c)) {
                    numCapitals++;
                }
                else if (Character.isDigit(c)) {
                    numNumbers++;
                }
                hasCaps = numCapitals >= PASSWORD_MIN_CAPITALS;
                hasNums = numNumbers >= PASSWORD_MIN_NUMBERS;
            }
            //goto next letter
            i++;
        }
        return validChars && hasCaps && hasNums;
    }



}
