package com.Application.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.SessionManager.SignInManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
public class NewUser extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField yourNameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField cnfPasswordField;
	
	@Autowired
	SignInManager signInManagerObj;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			NewUser dialog = new NewUser();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public NewUser() {
		super();
	}
	
	public void initialize(JFrame previousFrame) {
		clearAll();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblYourName = new JLabel("Your Name");
		lblYourName.setBounds(37, 40, 87, 14);
		contentPanel.add(lblYourName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(37, 89, 87, 14);
		contentPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 138, 87, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblCnfPassword = new JLabel("Confirm Password");
		lblCnfPassword.setBounds(37, 187, 87, 14);
		contentPanel.add(lblCnfPassword);
		
		yourNameField = new JTextField();
		lblYourName.setLabelFor(yourNameField);
		yourNameField.setBounds(143, 35, 96, 20);
		contentPanel.add(yourNameField);
		yourNameField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setBounds(143, 85, 96, 20);
		contentPanel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(143, 135, 96, 20);
		contentPanel.add(passwordField);
		char defaultPassword = passwordField.getEchoChar();
		
		 
		cnfPasswordField = new JPasswordField();
		cnfPasswordField.setColumns(10);
		cnfPasswordField.setBounds(143, 185, 96, 20);
		contentPanel.add(cnfPasswordField);
		char defaultCnfPassword = cnfPasswordField.getEchoChar();
		
		JLabel lblNewLabel_4 = new JLabel("Enter your details here");
		lblNewLabel_4.setBounds(37, 10, 124, 14);
		contentPanel.add(lblNewLabel_4);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(37, 24, 113, 5);
		contentPanel.add(separator);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show");
		Timer hideTimer = new Timer(1000, e -> {
			passwordField.setEchoChar(defaultPassword);
		 chckbxNewCheckBox.setSelected(false);
        });
        hideTimer.setRepeats(false);
		
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0); // Show plain text
	                hideTimer.restart();                 // Start 3-second countdown
	            } else {
	                hideTimer.stop();                    // Cancel timer if unchecked early
	                passwordField.setEchoChar(defaultPassword);
	            }
			}
		});
		chckbxNewCheckBox.setBounds(251, 134, 99, 23);
		contentPanel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxShow = new JCheckBox("Show");
		Timer hideTimer_2 = new Timer(1000, e -> {
			cnfPasswordField.setEchoChar(defaultCnfPassword);
		 chckbxShow.setSelected(false);
        });
        hideTimer_2.setRepeats(false);
			
		chckbxShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShow.isSelected()) {
					cnfPasswordField.setEchoChar((char) 0); // Show plain text
	                hideTimer_2.restart();                 // Start 3-second countdown
	            } else {
	                hideTimer_2.stop();                    // Cancel timer if unchecked early
	                passwordField.setEchoChar(defaultCnfPassword);
	            }
			}
		});
		chckbxShow.setBounds(251, 183, 99, 23);
		contentPanel.add(chckbxShow);
		
	
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Create");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(yourNameField.getText()!= null && usernameField.getText()!= null
								&& passwordField.getPassword()!=null && cnfPasswordField.getPassword()!=null) {
							if(new String(passwordField.getPassword()).equals(new String(cnfPasswordField.getPassword()))){
								JOptionPane.showMessageDialog(null, signInManagerObj.createNewUser(yourNameField.getText(), usernameField.getText(), new String(passwordField.getPassword())));
							}else {
								JOptionPane.showMessageDialog(null,"Password do not match, please check again.");
							}
						}else {
							JOptionPane.showMessageDialog(null,"Please do not leave any field empty.");
						}
						clearAll();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnNewButton = new JButton("Reset");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearAll();
					}
				});
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(btnNewButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearAll();
						dispose();
						previousFrame.setVisible(true);
					}
				});
				cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void clearAll() {
		if(yourNameField!= null && usernameField!= null
				&& passwordField!=null && cnfPasswordField!=null) {
			yourNameField.setText("");
			usernameField.setText("");
			passwordField.setText("");
			cnfPasswordField.setText("");
		}
		return;
	}
}
