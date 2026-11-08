package com.Application.interfaces;


import javax.management.MXBean;


@MXBean
public interface PasswordInterface {
	
	public String addPasswordToDB(String applicationName, String emailAdress, String type, String password);
	public String updatePasswordEntry(String applicationName, String emailAddress,String updatedPassword);
	public String showAllRecords();
}
