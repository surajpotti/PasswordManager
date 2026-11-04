package com.Application.interfaces;


import javax.management.MXBean;

import com.Application.POJO.PasswordPOJO;

@MXBean
public interface PasswordCSVInterface {
	
	public boolean createCSV();
	public boolean addNewEntry(PasswordPOJO passwordEntry);
	public boolean updateAnEntry(String applicatioName, String emailAddress, String updatedPassword);
	public boolean checkforexistingEntry(PasswordPOJO passwordEntry);
	public boolean initalizeListFromCSV();
	public boolean checkifValidEmailAddress(String emailAddress);

}
