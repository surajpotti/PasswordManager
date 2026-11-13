package com.Application.interfaces;


import java.util.List;

import javax.management.MXBean;

import com.Application.POJO.PasswordPOJO;

@MXBean
public interface PasswordCSVInterface {
	
	public String createCSV();
	public boolean addNewEntry(PasswordPOJO passwordEntry);
	public boolean updateAnEntry(String applicatioName, String emailAddress, String updatedPassword);
	public boolean checkForExistingEntry(PasswordPOJO passwordEntry);
	public List<PasswordPOJO> initalizeListFromCSV();
	public boolean checkIfValidEmailAddress(String emailAddress);
	public boolean writeBackToFile(PasswordPOJO passwrodEntry,String updatedPassword);
	public List<PasswordPOJO> userSearchForRecords(String applicationName, String emailAddress);
	public boolean deleteAnEntry(String applicationName, String emailAddress);
}
