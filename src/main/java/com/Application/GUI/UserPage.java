package com.Application.GUI;
 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


@Component
public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	@Autowired
	UserDialog userDialogObj;
	
	@Autowired
	PasswordLedger passDirectoryObj;
	
	@Autowired
	EditPage updPageObj;
	
	@Autowired 
	DeletePage delPageObj;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserPage frame = new UserPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UserPage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDialogObj.setVisible(true);
			}
		});
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		contentPane.add(btnAddNew);
		
		JButton btnEdit = new JButton("Edit Entry");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updPageObj.initialize();
				updPageObj.setVisible(true);
			}
		});
		contentPane.add(btnEdit);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passDirectoryObj.initialize();
				passDirectoryObj.setVisible(true);
			}
		});
		
		JButton btnDelete = new JButton("Delete Entry");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delPageObj.initialize();
				delPageObj.setVisible(true);
			}
		});
		contentPane.add(btnDelete);
		contentPane.add(btnShowAll);
		
		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPage.this.dispose();
			}
		});
		contentPane.add(btnSignOut);

	}
}
