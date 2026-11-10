package com.Application.GUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.FeatureClasses.Password;
import com.Application.POJO.PasswordPOJO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;


@Component
public class UpdatePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField appNameField;
	private JTextField emailAddField;
	private JLabel lblOr;

	@Autowired
	Password passObj;
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UpdatePage frame = new UpdatePage();
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
	public UpdatePage() {
		super();

	}
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JLabel lblAppName = new JLabel("Application Name");
		
		appNameField = new JTextField();
		appNameField.setColumns(10);
		
		JLabel lblEmailAdd = new JLabel("Email Address");
		
		emailAddField = new JTextField();
		emailAddField.setColumns(10);

			
		
		lblOr = new JLabel("Or");
		
		
		DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Status"}, 0) {
	            // Optional: Make the cells uneditable by the user
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        
        // Make it look more like a clean list (hide grid lines if desired)
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 2. Add the DEFAULT placeholder row
        tableModel.addRow(new Object[]{"No records to show"});
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setColumnCount(0);
				String[] columns = {"Date of Creation","Application Name", "Email ID", "Application Type", "Last Password", "Latest Password"};
				tableModel.setColumnIdentifiers(columns);
				for(PasswordPOJO record : passObj.searchForRecords(appNameField.getText(), emailAddField.getText())) {
					Object[] rowData = { record.getDateOfCreation(),record.getApplicationName(), record.getEmailAddress(),
							record.getApplicationType(),record.getLastPassword(),record.getPassword()};
					tableModel.addRow(rowData);
				}
			}
		});
		table.setShowGrid(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAppName, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addComponent(lblOr, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(appNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEmailAdd, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(emailAddField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)))
							.addGap(18)
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(195))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addComponent(lblOr)
					.addContainerGap(257, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAppName)
								.addComponent(appNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmailAdd)
								.addComponent(emailAddField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(table, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnNewButton)
					.addGap(208))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}
}
