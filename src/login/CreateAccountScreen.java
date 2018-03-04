package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateAccountScreen extends JFrame {

    private JLabel usernameLabel, passwordLabel, passwordConfirmationLabel, emailLabel;
    private JButton confirmButton = new JButton();
    private JButton cancelButton = new JButton();
    private JTextField usernameField = new JTextField();
    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField confirmPasswordField = new JPasswordField();

    public CreateAccountScreen(String title){
        super(title);
        initGUI();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createMainPanel(), BorderLayout.CENTER, 0);
        pack();
    }

    private void initGUI(){
        setName("CreateAccountScreen");
        setBounds(300, 300, 300, 300);
        Font labelFont = new java.awt.Font("Dialog",0,13);
        usernameLabel = createJLabel("Username: ", labelFont);
        passwordLabel = createJLabel("Password: ", labelFont);
        passwordConfirmationLabel = createJLabel("Confirm Password: ", labelFont);
        emailLabel = createJLabel("Email address: ", labelFont);
        usernameField.setText("");
        emailField.setText("");
        createConfirmButton();
        createCancelButton();
        setComponentNames();
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

    private void createConfirmButton(){
        confirmButton.setFont(new java.awt.Font("Dialog", 0, 12));
        confirmButton.setText("Create Account");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfirmButton_actionPerformed(e);
            }
        });
    }

    private JLabel createJLabel(String text, Font font){
        JLabel label = new JLabel();
        label.setFont(font);
        label.setText(text);
        return label;
    }

    private JPanel createMainPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(usernameLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(52, 49, 0, 0), 0, 0));
        panel.add(usernameField,    new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(52, 7, 0, 73), 214, 0));
        panel.add(passwordField,       new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(17, 7, 0, 73), 214, 0));
        panel.add(passwordLabel,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 49, 0, 0), 0, 0));
        panel.add(passwordConfirmationLabel, new GridBagConstraints(0,2,1,1,0.0,0.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 49, 0, 0), 0, 0));
        panel.add(confirmPasswordField, new GridBagConstraints(1, 2,2,1,1.0,0.0
                , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(17, 7, 0, 73), 214, 0));
        panel.add(emailLabel, new GridBagConstraints(0, 3, 1,1,0.0,0.0
                , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 49, 0, 0), 0, 0));
        panel.add(emailField, new GridBagConstraints(1,3,2,1,1.0,0.0
                ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(17, 7, 0, 73), 214,0));
        panel.add(confirmButton,   new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
                ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 70, 62, 18), 12, 0));
        panel.add(cancelButton,   new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
                ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(38, 71, 62, 53), 13, 0));
        return panel;
    }

    private void setComponentNames(){
        cancelButton.setName("CancelButton");
        confirmButton.setName("ConfirmButton");
        usernameField.setName("UsernameField");
        passwordField.setName("PasswordField");
    }

    void CancelButton_actionPerformed(ActionEvent e) {
        dispose();  //close only this frame, not entire program
    }

    //TODO: Check username/password/email validity, create account
    void ConfirmButton_actionPerformed(ActionEvent e) {

    }

}
