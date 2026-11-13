package com.Application.GUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.FeatureClasses.Password;
import com.Application.POJO.PasswordPOJO;


@Component
public class DeletePage extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField appNameField;
	private JTextField emailAddField;

	@Autowired
	Password passObj;
	
	private JTable table;
	private JTextField updAppNameField;
	private JTextField updEmailAddField;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DeleteEntry frame = new DeleteEntry();
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
	public DeletePage() {
		initialize();
	}
	
	public void initialize() {
		
		setTitle("Edit Entry");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, -11, 547, 368);
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
					tableModel.setColumnCount(0);
					String[] columns = {"Date of Creation","Application Name", "Email ID", "Application Type", "Last Password", "Latest Password"};
					tableModel.setColumnIdentifiers(columns);
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
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       
        
        getContentPane().add(table);
		getContentPane().add(lblEmailAdd);
		getContentPane().add(emailAddField);
		getContentPane().add(lblNewLabel);
		getContentPane().add(btnNewButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(11, 162, 591, 2);
		getContentPane().add(separator_1);
		
		JLabel lblNewLabel_3 = new JLabel("Delete Entry");
		lblNewLabel_3.setBounds(11, 170, 100, 14);
		getContentPane().add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(11, 190, 100, 2);
		getContentPane().add(separator);
		
		JLabel lblUpdateAppName = new JLabel("Application Name");
		lblUpdateAppName.setBounds(11, 201, 85, 14);
		getContentPane().add(lblUpdateAppName);
		
		updAppNameField = new JTextField();
		updAppNameField.setBounds(122, 198, 167, 20);
		getContentPane().add(updAppNameField);
		updAppNameField.setColumns(10);
		
		JLabel lblUpdateEmailAdd = new JLabel("Email Address");
		lblUpdateEmailAdd.setBounds(11, 227, 68, 14);
		getContentPane().add(lblUpdateEmailAdd);
		
		updEmailAddField = new JTextField();
		updEmailAddField.setBounds(122, 224, 167, 20);
		getContentPane().add(updEmailAddField);
		updEmailAddField.setColumns(10);
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(18, 266, 120, 23);
		getContentPane().add(btnReset);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(((!updAppNameField.getText().isEmpty() && !updEmailAddField.getText().isEmpty()))){
					JOptionPane.showMessageDialog(null, passObj.deletePasswordEntry(updAppNameField.getText(),updEmailAddField.getText()));
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect input please check and update.");
				}
		
			}
		});
		btnDelete.setBounds(148, 266, 148, 23);
		getContentPane().add(btnDelete);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(18, 27, 85, 2);
		getContentPane().add(separator_2);
		
		
	}
}
