package com.Application.GUI;


import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;

@Component
public class PasswordManagerApp {

	
	private JFrame frmPasswordManager;
	
	@Autowired
	private SignInPage signInPageObj;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PasswordManagerApp window = new PasswordManagerApp();
//					window.frmPasswordManager.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public PasswordManagerApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordManager = new JFrame();
		frmPasswordManager.setTitle("Password Manager");
		frmPasswordManager.setBounds(100, 100, 450, 300);
		frmPasswordManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Welcome to the Password Manager");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		
		JButton createBtn = new JButton("Create");
		createBtn.setBackground(UIManager.getColor("Button.darkShadow"));
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton signInBtn = new JButton("Sign In");
		signInBtn.setBackground(UIManager.getColor("Button.darkShadow"));
		signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmPasswordManager.setVisible(false);
				signInPageObj.intialize(frmPasswordManager);
				signInPageObj.setVisible(true);
			}
		});
		
		JButton helpBtn = new JButton("Help");
		helpBtn.setBackground(UIManager.getColor("Button.darkShadow"));
		GroupLayout groupLayout = new GroupLayout(frmPasswordManager.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(75)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(createBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(signInBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(helpBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(createBtn)
						.addComponent(signInBtn)
						.addComponent(helpBtn))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		frmPasswordManager.getContentPane().setLayout(groupLayout);
		frmPasswordManager.setVisible(true);
	}
}
