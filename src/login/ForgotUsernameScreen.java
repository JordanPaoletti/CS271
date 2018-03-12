package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ForgotUsernameScreen extends JFrame {

    private JLabel emailLabel, usernameLabel;
    private JButton cancelButton = new JButton();
    private JButton submitButton = new JButton();
    private JTextField emailField = new JTextField();
    private JPanel mainPanel;
    private UserAccountManager accountManager;

    public ForgotUsernameScreen(String title, UserAccountManager manager){
        super(title);
        initGUI();
        getContentPane().setLayout(new BorderLayout());
        mainPanel = createMainPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER, 0);
        pack();
        accountManager = manager;
    }

    private void initGUI(){
        setName("ForgotUsernameScreen");
        setBounds(300, 300, 300, 100);
        Font labelFont = new java.awt.Font("Dialog", 0, 13);
        emailLabel = createJLabel("Email Address: ", labelFont);
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
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(emailLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.NONE, new Insets(52, 49, 0, 0), 0, 0));
        panel.add(emailField, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, new Insets(52, 7, 0, 0), 214, 0));
        panel.add(submitButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(52, 7, 0, 73), 12, 0));
        panel.add(cancelButton, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(38, 71, 62, 53), 13, 0));
        return panel;
    }

    private void setComponentNames() {
        cancelButton.setName("CancelButton");
        submitButton.setName("SubmitButton");
        emailField.setName("EmailField");
    }

    void CancelButton_actionPerformed(ActionEvent e) {
        dispose(); // close only this frame, not entire program
    }

    void SubmitButton_actionPerformed(ActionEvent e){

        String emailAddress = emailField.getText();
        String username = accountManager.findUsername(emailAddress);
        usernameLabel = createJLabel(username, new java.awt.Font("Dialog", 0, 13));
        JOptionPane.showMessageDialog(this, usernameLabel, "Your Username:", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

}
