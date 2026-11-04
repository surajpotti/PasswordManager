package com.Application.FeatureClasses;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;

import com.Application.POJO.PasswordPOJO;
import com.Application.interfaces.PasswordInterface;


@ManagedResource
public class Password implements PasswordInterface{
	
	

	
	Scanner scanner = new Scanner(System.in);
	
	
	@Autowired
	PasswordDirectory passwordDir;
	

	
	public Password() {
		passwordDir = new PasswordDirectory();
		System.out.println("The app is up and running.");
	}


	@ManagedOperation
	public boolean addPasswordToDB(String applicationName, String emailAddress, String type, String password) {
		
		passwordDir.checkifValidEmailAddress(emailAddress);
		PasswordPOJO newObj = new PasswordPOJO(new Date().toString(),applicationName,emailAddress,type,password);
		if(!passwordDir.checkforexistingEntry(newObj)) {
			passwordDir.addNewEntry(newObj);
			return true;
		}
		return false;
		
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
			return "Found the entry and made the change";
		}
		return "";
	}

	

}
