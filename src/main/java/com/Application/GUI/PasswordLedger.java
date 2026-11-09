package com.Application.GUI;


import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.FeatureClasses.Password;
import com.Application.POJO.PasswordPOJO;


import javax.swing.JScrollPane;

@Component
public class PasswordLedger extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable showAllJtable;
	
	@Autowired
	Password password;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PasswordDirectory frame = new PasswordDirectory();
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
	public PasswordLedger() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		String[] columns = {"Date of Creation","Application Name", "Email ID", "Application Type", "Last Password", "Latest Password"};

		// 3. Create the model
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		
		for(PasswordPOJO record: password.showAllRecords()) {
			Object[] rowData = { record.getDateOfCreation(),record.getApplicationName(), record.getEmailAddress(),
					record.getApplicationType(),record.getLastPassword(),record.getPassword()};
		    model.addRow(rowData);
		}
		
		JScrollPane showAllScrollPane = new JScrollPane();
		showAllScrollPane.setBounds(10, 11, 414, 248);
		getContentPane().add(showAllScrollPane);
		
		showAllJtable = new JTable(model);
		showAllScrollPane.setViewportView(showAllJtable);

	}



}
