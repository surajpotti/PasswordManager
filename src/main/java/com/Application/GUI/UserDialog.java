package com.Application.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.FormLayout;
import com.Application.FeatureClasses.Password;
import com.Application.artifacts.ApplicationType;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JComboBox;

@Component
public class UserDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField applicationNameField;
	private JTextField emailAddressField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JComboBox<ApplicationType> comboBox;
	
	@Autowired
	Password passwordObj;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			UserDialog dialog = new UserDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public UserDialog() {
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblApplicationName = new JLabel("Application Name");
			contentPanel.add(lblApplicationName, "8, 2, 1, 2");
		}
		{
			applicationNameField = new JTextField();
			contentPanel.add(applicationNameField, "12, 2, fill, default");
			applicationNameField.setColumns(10);
		}
		{
			JLabel lblEmailAddress = new JLabel("Email Address");
			contentPanel.add(lblEmailAddress, "8, 4, 1, 2");
		}
		{
			emailAddressField = new JTextField();
			contentPanel.add(emailAddressField, "12, 4, fill, default");
			emailAddressField.setColumns(10);
		}
		{
			JLabel lblApplicationType = new JLabel("Application Type");
			contentPanel.add(lblApplicationType, "8, 6, 1, 2");
		}
		{
			comboBox = new JComboBox<>(ApplicationType.values());
			contentPanel.add(comboBox, "12, 6, fill, default");
			
		}
		{
			JLabel lblPassword = new JLabel("Password");
			contentPanel.add(lblPassword, "8, 8, 1, 2");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "12, 8, fill, default");
			///passwordFieldStr = new String(passwordField.getPassword());
			
		}
		{
			JLabel lblCnfPassword = new JLabel("Confirm Password");
			contentPanel.add(lblCnfPassword, "8, 10");
			
		}
		{
			confirmPasswordField = new JPasswordField();
			contentPanel.add(confirmPasswordField, "12, 10, fill, default");
			//cnfPasswordFieldStr = new String(confirmPasswordField.getPassword());
		}
		{
			JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
			chckbxNewCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					passwordField.getEchoChar();
				}
			});
			contentPanel.add(chckbxNewCheckBox, "12, 12");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JButton okCreate = new JButton("Create");
				okCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String passwordFieldStr = new String(passwordField.getPassword());
						String cnfPasswordFieldStr =  new String(confirmPasswordField.getPassword());
						if(passwordFieldStr.equals(cnfPasswordFieldStr)) {
							
							JOptionPane.showMessageDialog(null, passwordObj.addPasswordToDB(applicationNameField.getText(), emailAddressField.getText(), comboBox.getSelectedItem().toString(), passwordFieldStr));
						}else {
							JOptionPane.showMessageDialog(null, "The password doesn't match. Please check it again.");
						}
					}
				});
				okCreate.setActionCommand("Create");
				buttonPane.add(okCreate);
				getRootPane().setDefaultButton(okCreate);
				{
					JButton btnNewButton = new JButton("Reset");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							applicationNameField.setText("");
							emailAddressField.setText("");
							passwordField.setText("");
							confirmPasswordField.setText("");
						}
					});
					buttonPane.add(btnNewButton);
				}
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
