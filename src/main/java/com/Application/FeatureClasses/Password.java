package com.Application.FeatureClasses;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Service;

import com.Application.POJO.PasswordPOJO;
import com.Application.SessionManager.SignInManager;
import com.Application.interfaces.PasswordInterface;

@Service
public class Password implements PasswordInterface{

	

	@Autowired
	PasswordDirectory passwordDir;
	
	@Autowired
	SignInManager signIn;
	
	
	public Password() {
		System.out.println("The app is up and running.");
	}


	@ManagedOperation
	public String addPasswordToDB(String applicationName, String emailAddress, String type, String password) {
		
		if(!passwordDir.checkIfValidEmailAddress(emailAddress)) {
			return "Not a valid email address signature.";
		}
		PasswordPOJO newObj = new PasswordPOJO(new Date().toString(),applicationName,emailAddress,type,password,"");
		if(!passwordDir.checkForExistingEntry(newObj)) {
			passwordDir.addNewEntry(newObj);
			return "New entry created successfully.";
		}
		return "The entry already exists. Please check.";
		
	}
	

	@ManagedOperation
	@ManagedOperationParameter(name = "applicationName", description = "Name of the application.")
    @ManagedOperationParameter(name = "emailAddress", description = "The email address used.")
	public String updatePasswordEntry(String applicationName, String emailAddress, String updatedPassword) {
		if(applicationName.equals("String") || emailAddress.equals("String") ||
				applicationName.isEmpty() || emailAddress.isEmpty()) {
			return "Incorrect input, please check and enter again.";
		}
		if(passwordDir.updateAnEntry(applicationName, emailAddress, updatedPassword)) {
			return "Found the entry and updated the same.";
		}
		return "The entry not found.";
	}
	
    @ManagedOperation(description = "View all current CSV records inside JConsole")
    public List<PasswordPOJO> showAllRecords() {
    	return passwordDir.getPasswordEntries();
    	
//        try {
//            List<PasswordPOJO> records = passwordDir.getPasswordEntries();
//            StringBuilder sb = new StringBuilder("--- PASSWORD MANAGER MASTER RECORDS ---\n");
//            for (PasswordPOJO r : records) {
//                sb.append(String.format("Date of Creation: %s | Application Name: %s | Email: %s"
//                		+ "|ApplicationType: %s | Last Password: %s| Latest Password: %s\n", 
//                		r.getDateOfCreation(), r.getApplicationName(), r.getEmailAddress(), r.getApplicationType(), r.getLastPassword(),r.getPassword()));
//            }
//            return sb.toString();
//        } catch (Exception e) {
//            return "Error reading file: " + e.getMessage();
//        }
	}

	@Override
	public List<PasswordPOJO> searchForRecords(String applicationName, String emailAddress) {
		System.out.println( passwordDir.userSearchForRecords(applicationName, emailAddress));
		return passwordDir.userSearchForRecords(applicationName, emailAddress);
	}


	@Override
	public String deletePasswordEntry(String applicationName, String emailAddress) {
		if(applicationName.equals("String") || emailAddress.equals("String") ||
				applicationName.isEmpty() || emailAddress.isEmpty()) {
			return "Incorrect input, please check and enter again.";
		}
		if(passwordDir.deleteAnEntry(applicationName, emailAddress)) {
			return "Found the entry and updated the same.";
		}
		return "The entry not found.";
	}
    
	
	

}
