package com.Application.GUI;
 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnAddNew = new JButton("Add New");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnAddNew, 49, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnAddNew, 152, SpringLayout.WEST, contentPane);
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userDialogObj.setVisible(true);
			}
		});
		
		contentPane.add(btnAddNew);
		
		JButton btnEdit = new JButton("Edit Entry");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updPageObj.initialize();
				updPageObj.setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnEdit, 23, SpringLayout.SOUTH, btnAddNew);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnEdit, 152, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEdit, 0, SpringLayout.EAST, btnAddNew);
		contentPane.add(btnEdit);
		
		JButton btnShowAll = new JButton("Show All");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passDirectoryObj.initialize();
				passDirectoryObj.setVisible(true);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnShowAll, 19, SpringLayout.SOUTH, btnEdit);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnShowAll, 0, SpringLayout.WEST, btnAddNew);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnShowAll, 0, SpringLayout.EAST, btnAddNew);
		contentPane.add(btnShowAll);

	}
}
