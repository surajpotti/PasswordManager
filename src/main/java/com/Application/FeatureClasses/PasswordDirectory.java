package com.Application.FeatureClasses;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.management.MXBean;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.Application.POJO.PasswordPOJO;
import com.Application.interfaces.PasswordCSVInterface;


@MXBean
public class PasswordDirectory implements PasswordCSVInterface{
	
	private static final String passwordDirectory = "passwordDirectory.csv";
	private List<PasswordPOJO> passwordEntries;
	private static boolean APP_RUNNING_STATUS = false;
	
	private static final String EMAIL_REGEX;
	private static final Pattern EMAIL_PATTERN;
	private static final String[] HEADERS = {"Date of Creation","Application Name", "Email ID", "Application Type", "Last Password", "Latest Password"};
	
	
	static {
		EMAIL_REGEX = 
		        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
		        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    // Pre-compile the pattern for optimal performance
		EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
		// Centralised Header Definition
		
		
	}
	
	public PasswordDirectory() {
		super();
		this.passwordEntries = new ArrayList<PasswordPOJO>();
		createCSV();
		initalizeListFromCSV();
		System.out.println("Password Entries: " + this.passwordEntries.toString());
	}
	
	
	
	/**
	 * @return the passwordEntries
	 */
	public List<PasswordPOJO> getPasswordEntries() {
		return passwordEntries;
	}



	/**
	 * @param passwordEntries the passwordEntries to set
	 */
	public void setPasswordEntries(List<PasswordPOJO> passwordEntries) {
		this.passwordEntries = passwordEntries;
	}



	public String createCSV() {
		
		if(!checkIfFileExists(passwordDirectory)) {
			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
	                .setHeader(HEADERS)
	                .build();

	        // Writing headers to the brand-new file
	        try (FileWriter out = new FileWriter(passwordDirectory);
	             CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
	            printer.flush();
	            return "New CSV is created.";
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return "File already exists, skipping file creation.";
		
	}
	
	
	public boolean checkIfFileExists(String filePath) {
		File file = new File(filePath);
		 // 1. Guard rail: Check if the file already exists
		if (file.exists()) {
		    System.out.println("File already exists at: " + filePath + ". Skipping creation.");
		    APP_RUNNING_STATUS = true;
		    return true; // Or throw a FileAlreadyExistsException depending on your business logic
		}
		return false;

	}
	
	 public List<PasswordPOJO> initalizeListFromCSV(){
		 
		 CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
	                .setHeader(HEADERS)
	                .setSkipHeaderRecord(true) // Skips reading the first row as data
	                .setTrim(true)
	                .setIgnoreHeaderCase(true)
	                .build();

	        try (FileReader reader = new FileReader(passwordDirectory);
	             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

	            for (CSVRecord csvRecord : csvParser) {
	            	PasswordPOJO record = new PasswordPOJO();
	                record.setDateOfCreation(csvRecord.get("Date Of Creation"));
	                record.setApplicationName(csvRecord.get("Application Name"));
	                record.setEmailAddress(csvRecord.get("Email ID"));
	                record.setApplicationType(csvRecord.get("Application Type"));
	                record.setPassword(csvRecord.get("Latest Password"));
	                record.setLastPassword(csvRecord.get("Last Password"));
	                
	                passwordEntries.add(record);
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return passwordEntries;
	    }

	 @Override
	 public boolean addNewEntry(PasswordPOJO passwordEntry) {
		 	if (passwordEntry == null) {
	            throw new IllegalArgumentException("Record cannot be null");
		 	}
		 	if(APP_RUNNING_STATUS) {

		        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
		                .setHeader(HEADERS)
		                .setSkipHeaderRecord(checkIfFileExists(passwordDirectory)) // Native switch: if file exists with data, skip printing headers
		                .build();
	
		        // Note the 'true' argument to enable standard file appending
		        try (FileWriter out = new FileWriter(passwordDirectory, true);
		             CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
		            
		            // Map object attributes cleanly into a single row
		            printer.printRecord(passwordEntry.getDateOfCreation(),passwordEntry.getApplicationName(),
		            		passwordEntry.getEmailAddress(),passwordEntry.getApplicationType(),
		            		passwordEntry.getLastPassword(),passwordEntry.getPassword());
		            printer.flush();
		            passwordEntries.add(passwordEntry);
		            return true;
		        } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	}
		return false;
	 }
	 
	 

	 @Override
	 public boolean checkforexistingEntry(PasswordPOJO passwordEntry) {
		 try {
		     Iterator<PasswordPOJO> itr = getPasswordEntries().iterator();
		     
		     while(itr.hasNext()) {
		    	 PasswordPOJO current = itr.next();   	 
				 if(escapeSpecialCharacters(current.getApplicationName()).equals(passwordEntry.getApplicationName()) &&
					escapeSpecialCharacters(current.getEmailAddress()).equals(passwordEntry.getEmailAddress()) &&
					escapeSpecialCharacters(current.getApplicationType()).equals(passwordEntry.getApplicationType()) &&
					escapeSpecialCharacters(current.getPassword()).equals(passwordEntry.getPassword())) {
					 System.out.println("The entry is a duplicate");
					 return true;
				 }
			 }
		 }catch(Exception e) {e.printStackTrace();}
		return false;
	 }

	 @Override
	 public boolean checkIfValidEmailAddress(String emailAddress) {
		 if(!emailAddress.isEmpty()) {
			 return EMAIL_PATTERN.matcher(emailAddress).matches();
			 }
		return false;
	 }
	 

	 @Override
	 public boolean updateAnEntry(String applicatioName, String emailAddress, String updatedPassword) {
		// TODO Auto-generated method stub
		return false;
	 }


	 @Override
	 public boolean writeBackToFile(PasswordPOJO passwrodEntry, String updatedPassword) {
		// TODO Auto-generated method stub
		return false;
	 }
	 
	private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\"", "\"\"");
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
	 
}
