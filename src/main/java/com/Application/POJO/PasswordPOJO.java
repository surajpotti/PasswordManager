package com.Application.POJO;



public class PasswordPOJO {

	private String dateOfCreation;
	private String applicationName;
	private String emailAddress;
	private String applicationType;
	
	private String password;
	
	public PasswordPOJO() {
		super();
	}

	public PasswordPOJO(String dateOfCreation, String applicationName, String emailAddress, String applicationType,
			String password) {
		super();
		this.dateOfCreation = dateOfCreation;
		this.applicationName = applicationName;
		this.emailAddress = emailAddress;
		this.applicationType = applicationType;
		this.password = password;
	}



	@Override
	public String toString() {
		return "Password [dateOfCreation=" + dateOfCreation + ", applicationName=" + applicationName + ", emailAddress="
				+ emailAddress + ", applicationType=" + applicationType + ", password=" + password + "]";
	}

	/**
	 * @return the dateOfCreation
	 */
	public String getDateOfCreation() {
		return dateOfCreation;
	}

	/**
	 * @param dateOfCreation the dateOfCreation to set
	 */
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the applicationType
	 */
	public String getApplicationType() {
		return applicationType;
	}

	/**
	 * @param applicationType the applicationType to set
	 */
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



}
