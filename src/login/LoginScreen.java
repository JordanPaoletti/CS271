package login;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class LoginScreen extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	public static final String SuccessfulLogin = "Successful Login";
	public static final String FailedLogin = "Failed Login";

	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField = new JTextField();
	private JPasswordField passwordTextField = new JPasswordField();
	private JButton enterButton = new JButton();
	private JButton exitButton = new JButton();
	private JButton createAccountButton = new JButton();
	private JLabel forgotUser = new JLabel();
	private JLabel forgotPass = new JLabel();

	private UserAccountManager accountManager;
	
	public LoginScreen(String title) {
		super(title);
		initUserAccounts();
		initGUI();
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(createMainPanel(), BorderLayout.CENTER,0);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	}
	  
	public LoginScreen() {
		this("Demo");
	}
	  
	private void initUserAccounts() {
		accountManager = new UserAccountManager();
		accountManager.addUserAccount("admin", "Ab123456", "admin@aol.com");
	}
	
	private void initGUI(){
		setName("loginScreen");
	    setBounds(300,300,300,300);
		Font labelFont = new java.awt.Font("Dialog", 0, 13);
		usernameLabel = createJLabel("User name:", labelFont);
	    passwordLabel = createJLabel("Password:", labelFont);
	    createEnterButton();
	    createExitButton();
	    createCreateAccountButton();
	    usernameTextField.setText("");
	    passwordTextField.setText("");
	    createForgotUserButton();
	    createForgotPassButton();
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
	
	private void createForgotUserButton() {
	    forgotUser.setText("Forgot Username?");
	    forgotUser.setForeground(Color.BLUE);
	    forgotUser.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		JLabel label = (JLabel) e.getSource();
	    		
	    		if (label == forgotUser) {
	    			ForgotUsernameScreen forgotUsername = new ForgotUsernameScreen("Forgot Username", accountManager);
	    			forgotUsername.setVisible(true);
	    		}
	    	}
	    });
	}
	
	private void createForgotPassButton() {
	    forgotPass.setText("Forgot Password?");
	    forgotPass.setForeground(Color.BLUE);
	    forgotPass.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		JLabel label = (JLabel) e.getSource();
	    		
	    		if (label == forgotPass) {
	    			ForgotPasswordScreen forgotPassword = new ForgotPasswordScreen("Forgot Password", accountManager);
	    			forgotPassword.setVisible(true);
	    		}
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

	private void createCreateAccountButton(){
		createAccountButton.setFont(new java.awt.Font("Dialog", 0, 12));
		createAccountButton.setText("Create Account");
		createAccountButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {CreateAccountButton_actionPerformed(e);}
		});
	}

	private JPanel createMainPanel(){
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridBagLayout());
	    panel.add(usernameLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
	    panel.add(usernameTextField,    new GridBagConstraints(0, 2, 2, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10, 50, 0, 73), 214, 0));
	    panel.add(passwordTextField,       new GridBagConstraints(0, 4, 2, 1, 1.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10, 50, 0, 73), 214, 0));
	    panel.add(passwordLabel,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(19, 49, 0, 7), 0, 0));
	    panel.add(exitButton,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 48, 20, 18), 12, 0));
	    panel.add(createAccountButton,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 50), 13, 0));
		panel.add(enterButton,   new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 70, 20, 74), 13, 0));
		panel.add(forgotUser,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 49, 0, 0), 0, 0));
		panel.add(forgotPass,   new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
	            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(19, 49, 0, 7), 0, 0));
		return panel;
	}
	
	private void setComponentNames(){
		exitButton.setName("ExitButton");
		enterButton.setName("EnterButton");
		createAccountButton.setName("CreateAccountButton");
		usernameTextField.setName("LoginNameTextField");
		passwordTextField.setName("PasswordTextField");
	}
	  
	void ExitButton_actionPerformed(ActionEvent e) {
	    System.exit(0);
	}

	//TODO: display new "logged-in" screen
	void EnterButton_actionPerformed(ActionEvent e) {
		String userName = usernameTextField.getText();
		char[] password = passwordTextField.getPassword();
		if (!accountManager.isLockedOut()) {
		    if(accountManager.doesAccountExist(userName, new String(password)))
				JOptionPane.showMessageDialog(this, "Login Succeeded!", "Successful Login", JOptionPane.INFORMATION_MESSAGE);
		    else {
		    	JOptionPane.showMessageDialog(this,"Your username or password was incorrect","Failed Login",JOptionPane.INFORMATION_MESSAGE);
		    	accountManager.setInvalidAttemptsNum();
		    }
		}
		else {
			JOptionPane.showMessageDialog(this, "You have made too many invalid login attempts.","Session Locked Out", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	void CreateAccountButton_actionPerformed(ActionEvent e){
		JFrame createAccount = new CreateAccountScreen("Create Account", accountManager);
		createAccount.setVisible(true);
	}

	public static void main(String args[]){
		JFrame dialog = new LoginScreen("Login");
		dialog.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}

