package com.Application.GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jgoodies.forms.layout.FormLayout;
import com.Application.SessionManager.SignInManager;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@Component
public class SignInPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxtField;
	private JButton btnCancel;
	private JPasswordField passwordTxtField;
	
	@Autowired
	private SignInManager signInObj;
	
	@Autowired
	private UserPage userPage;
	

	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SignInPage frame = new SignInPage();
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
	public SignInPage() {
		super();
	}
	
	public void intialize(JFrame previousFrame) {
		setTitle("Sign In");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("143px"),
				ColumnSpec.decode("67px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("132px"),},
			new RowSpec[] {
				RowSpec.decode("55px"),
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				RowSpec.decode("50px"),
				RowSpec.decode("23px"),}));
		
		JLabel userNameLbl = new JLabel("Username");
		contentPane.add(userNameLbl, "2, 2, center, center");
		
		usernameTxtField = new JTextField();
		usernameTxtField.setToolTipText("Enter username");
		contentPane.add(usernameTxtField, "4, 2, fill, top");
		usernameTxtField.setColumns(10);
		
		JLabel passwordLbl = new JLabel("Password");
		contentPane.add(passwordLbl, "2, 4, center, center");
		
		JButton signInButton = new JButton("Sign In");
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(signInObj.signIn(usernameTxtField.getText(), new String(passwordTxtField.getPassword()))){
					System.out.print("Correct password");
					userPage.setVisible(true);
					//JOptionPane.showMessageDialog(null, "Correct username and password");
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect username and password");
				}
			}
		});
		
		passwordTxtField = new JPasswordField();
		contentPane.add(passwordTxtField, "4, 4, fill, default");
		contentPane.add(signInButton, "2, 6, center, center");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callCancelMethod(previousFrame);
			}
		});
		contentPane.add(btnCancel, "4, 6, center, center");
		
	}


	
	protected void callCancelMethod(JFrame previousFrame) {
		this.dispose();
		previousFrame.setVisible(true);
	}
	

}
