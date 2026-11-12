package com.Application.GUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.FeatureClasses.Password;
import com.Application.POJO.PasswordPOJO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;


@Component
public class EditPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField appNameField;
	private JTextField emailAddField;
	private List<PasswordPOJO> searchedEntries;

	@Autowired
	Password passObj;
	
	private JTable table;
	private JPasswordField passwordField;
	private JPasswordField cnfPasswordField;
	private JTextField textField;
	private JTextField textField_1;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditPage frame = new EditPage();
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
	public EditPage() {
		super();
		initialize();
	}
	
	public void initialize() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 441);
		setLocationRelativeTo(null);
		JLabel lblAppName = new JLabel("Application Name");
		lblAppName.setBounds(18, 40, 85, 14);
		
		appNameField = new JTextField();
		appNameField.setBounds(141, 37, 96, 20);
		appNameField.setColumns(10);
		
		JLabel lblEmailAdd = new JLabel("Email Address");
		lblEmailAdd.setBounds(27, 102, 68, 14);
		
		emailAddField = new JTextField();
		emailAddField.setBounds(141, 99, 96, 20);
		emailAddField.setColumns(10);
		
		
		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Status"}, 0);
        
        // 2. Add the DEFAULT placeholder row
        tableModel.addRow(new Object[]{"No records to show"});
       
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(209, 133, 65, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				if(!passObj.searchForRecords(appNameField.getText(), emailAddField.getText()).isEmpty()) {
					//tableModel.setColumnCount(0);
					String[] columns = {"Date of Creation","Application Name", "Email ID", "Application Type", "Last Password", "Latest Password"};
					tableModel.setColumnIdentifiers(columns);
					if(!passObj.searchForRecords(appNameField.getText(), emailAddField.getText()).isEmpty())
					for(PasswordPOJO record : passObj.searchForRecords(appNameField.getText(), emailAddField.getText())) {
						Object[] rowData = { record.getDateOfCreation(),record.getApplicationName(), record.getEmailAddress(),
								record.getApplicationType(),record.getLastPassword(),record.getPassword()};
						//searchedEntries.add(record);
						tableModel.addRow(rowData);
					}
				}
			}
		});
		JLabel lblNewLabel = new JLabel("Or");
		lblNewLabel.setBounds(55, 74, 12, 14);
		getContentPane().setLayout(null);
		getContentPane().add(lblAppName);
		getContentPane().add(appNameField);
		
        table = new JTable(tableModel);
        table.setBounds(247, 40, 274, 79);
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        getContentPane().add(table);
		getContentPane().add(lblEmailAdd);
		getContentPane().add(emailAddField);
		getContentPane().add(lblNewLabel);
		getContentPane().add(btnNewButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(11, 162, 591, 2);
		getContentPane().add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("Update Password");
		lblNewLabel_3.setBounds(11, 170, 100, 14);
		getContentPane().add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(11, 190, 100, 2);
		getContentPane().add(separator);
		
		JLabel lblUpdateAppName = new JLabel("Application Name");
		lblUpdateAppName.setBounds(11, 201, 85, 14);
		getContentPane().add(lblUpdateAppName);
		
		textField = new JTextField();
		textField.setBounds(122, 198, 167, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		
		JLabel lblUpdateEmailAdd = new JLabel("Email Address");
		lblUpdateEmailAdd.setBounds(11, 227, 68, 14);
		getContentPane().add(lblUpdateEmailAdd);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 224, 167, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(11, 253, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 250, 167, 20);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setBounds(11, 279, 87, 14);
		getContentPane().add(lblNewLabel_2);
		
		cnfPasswordField = new JPasswordField();
		cnfPasswordField.setBounds(122, 276, 167, 20);
		getContentPane().add(cnfPasswordField);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(11, 346, 120, 23);
		getContentPane().add(btnReset);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(141, 346, 148, 23);
		getContentPane().add(btnUpdate);
		
		
	}
	
	
	
}
