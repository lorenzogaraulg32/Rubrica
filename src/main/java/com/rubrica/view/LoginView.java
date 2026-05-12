package com.rubrica.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.rubrica.style.AppStyle.*;

public class LoginView extends JFrame {
    JLabel usernameLabel;
    JTextField usernameField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton loginButton;


    public LoginView() {
        setTitle("Login");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        styleLoginPanel(mainPanel);
        styleLoginLabel(usernameLabel);
        styleLoginLabel(passwordLabel);
        styleLoginButton(loginButton);
        styleLoginField(usernameField);
        styleLoginField(passwordField);

        mainPanel.add(usernameLabel);
        mainPanel.add(Box.createVerticalStrut(6));
        mainPanel.add(usernameField);

        mainPanel.add(Box.createVerticalStrut(18));

        mainPanel.add(passwordLabel);
        mainPanel.add(Box.createVerticalStrut(6));
        mainPanel.add(passwordField);

        mainPanel.add(Box.createVerticalStrut(24));

        mainPanel.add(loginButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public String getUsernameInput() {
        return usernameField.getText().trim();
    }

    public String getPasswordInput() {
        return new String(passwordField.getPassword()).trim();
    }


    public void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Errore",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
