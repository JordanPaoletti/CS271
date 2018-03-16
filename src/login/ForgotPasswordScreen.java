package login;

import email.Emailer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import static login.UserAccount.isValidPassword;

public class ForgotPasswordScreen extends JFrame {

    private JLabel emailLabel, passwordLabel, infoLabel, passLabel;
    private JButton cancelButton = new JButton();
    private JButton submitButton = new JButton();
    private JTextField emailField = new JTextField();
    private JPanel mainPanel;
    private UserAccountManager accountManager;
    private int resetSwitch = 0;
    private String resetCode, userName;
    private JPanel panel = new JPanel();
    private JPasswordField passField = new JPasswordField();
    private JPasswordField confirmPassField = new JPasswordField();
    private Boolean emailSent=false;

    public ForgotPasswordScreen(String title, UserAccountManager manager){
        super(title);
        initGUI();
        getContentPane().setLayout(new BorderLayout());
        mainPanel = createMainPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER, 0);
        pack();
        accountManager = manager;
    }

    private void initGUI(){
        setName("ForgotPasswordScreen");
        setBounds(300, 300, 300, 100);
        Font labelFont = new java.awt.Font("Dialog", 0, 13);
        emailLabel = createJLabel("Email Address: ", labelFont);
        infoLabel = createJLabel("Please enter the email address associated with your account: ", new Font("Dialog", Font.BOLD, 13));
        passLabel = createJLabel("New Password", labelFont);
        createSubmitButton();
        createCancelButton();
        setComponentNames();
    }

    private void createSubmitButton(){
        submitButton.setFont(new java.awt.Font("Dialog", 0, 12));
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SubmitButton_actionPerformed(e);
            }
        });
    }

    private void createCancelButton(){
        cancelButton.setFont(new java.awt.Font("Dialog", 0, 12));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CancelButton_actionPerformed(e);
            }
        });
    }

    private JLabel createJLabel(String text, Font font) {
        JLabel label = new JLabel();
        label.setFont(font);
        label.setText(text);
        return label;
    }

    private JPanel createMainPanel() {
        panel.setLayout(new GridBagLayout());
        
        panel.add(infoLabel, new GridBagConstraints(0, 0, 0, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(40, 49, 0, 0), 0, 0));
       
        panel.add(emailLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
        panel.add(emailField, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(15, 7, 0, 0), 214, 0));
        
        panel.add(submitButton, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(15, 7, 0, 73), 12, 0));
        panel.add(cancelButton, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(38, 71, 62, 53), 13, 0));
        return panel;
    }

    private void setComponentNames() {
        cancelButton.setName("CancelButton");
        submitButton.setName("SubmitButton");
        emailField.setName("EmailField");
        confirmPassField.setName("ConfirmPassField");
    }

    void CancelButton_actionPerformed(ActionEvent e) {
        dispose(); // close only this frame, not entire program
    }

    void SubmitButton_actionPerformed(ActionEvent e){

    	if (resetSwitch == 0) {
	        String emailAddress = emailField.getText();
	        String username = accountManager.findUsername(emailAddress);
	        
	        if (accountManager.doesUserNameExist(username)){
	        	userName = username;
	        	String rstCode = getResetCode();
	            String body = "Your password reset code is: " + rstCode;
	            resetCode = rstCode;
	            Emailer.sendEmail(emailAddress, "Password Recovery", body);
	            JOptionPane.showMessageDialog(this, "A password reset code has been sent to your email!", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
	            emailLabel.setText("Password Reset Code: ");
	            infoLabel.setText("Please enter the reset code sent to your email: ");
	            emailField.setText("");
	            resetSwitch = 1;
	        }
	        else{
	            passwordLabel = createJLabel(username, new java.awt.Font("Dialog", 0, 13));
	            JOptionPane.showMessageDialog(this, passwordLabel, "Error", JOptionPane.INFORMATION_MESSAGE);
	        }
	        
    	}
    	else if (resetSwitch == 1) {
    		String rstCode = emailField.getText();
    		
    		if (rstCode.equals(resetCode)) {
    			JOptionPane.showMessageDialog(this, "Reset code is correct. Please enter a new password.", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
    			emailLabel.setText("New Password: ");
	            infoLabel.setText("Please enter a new password: ");
	            emailField.setText("");
	            panel.remove(emailLabel);
	            panel.remove(emailField);
	            panel.remove(submitButton);
	            panel.add(passLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
	                    GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
	            panel.add(passField, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
	                    GridBagConstraints.HORIZONTAL, new Insets(15, 7, 0, 49), 214, 0));
	            panel.add(createJLabel("Confirm Password: ", new Font("Dialog", 0, 13)), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
	                    GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
	            panel.add(confirmPassField, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
	                    GridBagConstraints.HORIZONTAL, new Insets(15, 7, 0, 49), 214, 0));
                panel.add(submitButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.NONE, new Insets(38, 49, 62, 0), 12, 0));
    			resetSwitch = 2;
    		}
    		else {
    			JOptionPane.showMessageDialog(this, "Reset code was incorrect.", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	else if (resetSwitch == 2) {
    		String newPass = new String(passField.getPassword());
    		String confPass = new String(confirmPassField.getPassword());
    		
    	    if (!(newPass.equals(confPass))) { //non matching passwords
    	            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Failed Account Creation",
    	                    JOptionPane.INFORMATION_MESSAGE);
    	    }
    	    else {
    	        if (isValidPassword(newPass)) {
                    accountManager.setPassword(userName, newPass);
                    JOptionPane.showMessageDialog(this, "Password has been successfully reset.", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
    	        }
                else {
                    JOptionPane.showMessageDialog(this, "Invalid password", "Password reset error", JOptionPane.INFORMATION_MESSAGE);
                }
    	    }
    		
    	}
	}

    public String getResetCode() {
        String alnumChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder strBuilder = new StringBuilder();
        Random rand = new Random();

        while (strBuilder.length() < 9) {
            int index = (int) (rand.nextFloat() * alnumChars.length());
            strBuilder.append(alnumChars.charAt(index));
        }

        String resetCode = strBuilder.toString();
        return resetCode;
    }


    /*Helpers and overloaded methods for testing*/

    public Boolean getEmailSent(){return emailSent;}

    public String forgotPassMailer(String emailAddress, String username) {

        String rstCode=null;

        if (resetSwitch == 0) {
            if (accountManager.doesUserNameExist(username)) {
                userName = username;
                String body = "Your password reset code is: " + rstCode;
                resetCode = getResetCode();
                Emailer.sendEmail(emailAddress, "Password Recovery", body);
                JOptionPane.showMessageDialog(this, "A password reset code has been sent to your email!", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
                emailLabel.setText("Password Reset Code: ");
                infoLabel.setText("Please enter the reset code sent to your email: ");
                emailField.setText("");
                resetSwitch = 1;
                emailSent = true;
            } else {
                passwordLabel = createJLabel(username, new java.awt.Font("Dialog", 0, 13));
                JOptionPane.showMessageDialog(this, passwordLabel, "Error", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        return resetCode;
    }

    public Boolean codeMatchCheck(String rstCode){
        Boolean matched=false;
        if (resetSwitch == 1) {

            if (rstCode.equals(resetCode)) {
                JOptionPane.showMessageDialog(this, "Reset code is correct. Please enter a new password.", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
                emailLabel.setText("New Password: ");
                infoLabel.setText("Please enter a new password: ");
                emailField.setText("");
                panel.remove(emailLabel);
                panel.remove(emailField);
                panel.remove(submitButton);
                panel.add(passLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
                panel.add(passField, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(15, 7, 0, 49), 214, 0));
                panel.add(createJLabel("Confirm Password: ", new Font("Dialog", 0, 13)), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
                panel.add(confirmPassField, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.HORIZONTAL, new Insets(15, 7, 0, 49), 214, 0));
                panel.add(submitButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                        GridBagConstraints.NONE, new Insets(38, 49, 62, 0), 12, 0));
                resetSwitch = 2;
                matched = true;

            }
            else {
                JOptionPane.showMessageDialog(this, "Reset code was incorrect.", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (resetSwitch == 2) {
            String newPass = new String(passField.getPassword());
            String confPass = new String(confirmPassField.getPassword());

            if (!(newPass.equals(confPass))) { //non matching passwords
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Failed Account Creation",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                accountManager.setPassword(userName, newPass);
                JOptionPane.showMessageDialog(this, "Password has been successfully reset.", "Password recovery", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }

        }
        return matched;
    }

}

