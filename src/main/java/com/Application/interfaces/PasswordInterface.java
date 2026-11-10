package com.Application.interfaces;


import java.util.List;

import javax.management.MXBean;

import com.Application.POJO.PasswordPOJO;


@MXBean
public interface PasswordInterface {
	
	public String addPasswordToDB(String applicationName, String emailAdress, String type, String password);
	public String updatePasswordEntry(String applicationName, String emailAddress,String updatedPassword);
	public List<PasswordPOJO> showAllRecords();
	public List<PasswordPOJO> searchForRecords(String applicationName, String emailAddress);
}
