package com.Application.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@Component
public class NewUser extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Name");
		lblNewLabel.setBounds(37, 40, 87, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(37, 89, 87, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(37, 138, 87, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Confirm Password");
		lblNewLabel_3.setBounds(37, 187, 87, 14);
		contentPanel.add(lblNewLabel_3);
		
		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setBounds(143, 35, 96, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 85, 96, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(143, 135, 96, 20);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(143, 185, 96, 20);
		contentPanel.add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("Enter your details here");
		lblNewLabel_4.setBounds(37, 10, 124, 14);
		contentPanel.add(lblNewLabel_4);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(37, 24, 113, 5);
		contentPanel.add(separator);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show");
		chckbxNewCheckBox.setBounds(251, 134, 99, 23);
		contentPanel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxShow = new JCheckBox("Show");
		chckbxShow.setBounds(251, 183, 99, 23);
		contentPanel.add(chckbxShow);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Create");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnNewButton = new JButton("Reset");
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(btnNewButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
