package com.Application.GUI;


import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Application.FeatureClasses.Password;
import com.Application.POJO.PasswordPOJO;


import javax.swing.JScrollPane;

@Component
public class PasswordLedger extends JFrame {

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
		super();
	}
	
	
	public void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane showAllScrollPane = new JScrollPane();
		showAllScrollPane.setBounds(10, 11, 414, 248);
		getContentPane().add(showAllScrollPane);
		String[] columns = {"Date of Creation","Application Name", "Email ID", "Application Type", "Last Password", "Latest Password"};

		// 3. Create the model
		DefaultTableModel model = new DefaultTableModel(columns, 0);
		
		for(PasswordPOJO record: password.showAllRecords()) {
			Object[] rowData = { record.getDateOfCreation(),record.getApplicationName(), record.getEmailAddress(),
					record.getApplicationType(),record.getLastPassword(),record.getPassword()};
		    model.addRow(rowData);
		}
		showAllJtable = new JTable(model);
		showAllJtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		showAllScrollPane.setViewportView(showAllJtable);
	}

}
