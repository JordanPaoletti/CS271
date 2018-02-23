package login;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class LoginScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static final String SuccessfulLogin = "Successful Login";
	public static final String FailedLogin = "Failed Login";
	
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField = new JTextField();
	private JPasswordField passwordTextField = new JPasswordField();
	private JButton enterButton = new JButton();
	private JButton exitButton = new JButton();

	private UserAccountManager accountManager;
	
	public LoginScreen(String title) {
		super(title);
		initUserAccounts();
		initGUI();
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(createMainPanel(), BorderLayout.CENTER,0);
	    pack();
	}
	  
	public LoginScreen() {
		this("Demo");
	}
	  
	private void initUserAccounts() {
		accountManager = new UserAccountManager();
		accountManager.addUserAccount("admin", "123456");		
	}
	
	private void initGUI(){
		setName("loginScreen");
	    setBounds(300,300,300,300);
		Font labelFont = new java.awt.Font("Dialog", 0, 13);
		usernameLabel = createJLabel("User name:", labelFont);
	    passwordLabel = createJLabel("Password:", labelFont);
	    createEnterButton();
	    createExitButton();
	    usernameTextField.setText("");
	    passwordTextField.setText("");
		setComponentNames();
	  }

	private JLabel createJLabel(String text, Font font){
		JLabel label = new JLabel();
		label.setFont(font);
		label.setText(text);
		return label;
	}
	
	private void createEnterButton(){
	    enterButton.setFont(new java.awt.Font("Dialog", 0, 12));
	    enterButton.setText("Confirm");
	    enterButton.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        EnterButton_actionPerformed(e);
	      }
	    });
	} 
	
	private void createExitButton(){
	    exitButton.setFont(new java.awt.Font("Dialog", 0, 12));
	    exitButton.setText("Cancel");
	    exitButton.addActionListener(new java.awt.event.ActionListener(){
	      public void actionPerformed(ActionEvent e) {
	        ExitButton_actionPerformed(e);
	      }
	    });
	}

	private JPanel createMainPanel(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridBagLayout());
	    panel.add(usernameLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(52, 49, 0, 0), 0, 0));
	    panel.add(usernameTextField,    new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(52, 7, 0, 73), 214, 0));
	    panel.add(passwordTextField,       new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(17, 7, 0, 73), 214, 0));
	    panel.add(passwordLabel,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(19, 49, 0, 7), 0, 0));
	    panel.add(enterButton,   new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 88, 62, 0), 12, 0));
	    panel.add(exitButton,   new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(38, 91, 62, 73), 13, 0));
	    return panel;
	}
	
	private void setComponentNames(){
		exitButton.setName("ExitButton");
		enterButton.setName("EnterButton");
		usernameTextField.setName("LoginNameTextField");
		passwordTextField.setName("PasswordTextField");
	}
	  
	void ExitButton_actionPerformed(ActionEvent e) {
	    System.exit(0);
	}

	void EnterButton_actionPerformed(ActionEvent e) {
		String userName = usernameTextField.getText();
		char[] password = passwordTextField.getPassword();
	    if(accountManager.doesAccountExist(userName, new String(password)))
			JOptionPane.showMessageDialog(this, "Login Succeeded!", "Successful Login", JOptionPane.INFORMATION_MESSAGE);
	    else
	    	JOptionPane.showConfirmDialog(this,"Login Failed","Failed Login",JOptionPane.YES_NO_OPTION);
	}

	public static void main(String args[]){
		JFrame dialog = new LoginScreen("loginScreen");
		dialog.setVisible(true);
	}

}

