package com.Application.FeatureClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.regex.Pattern;

import javax.management.MXBean;


import com.Application.POJO.PasswordPOJO;
import com.Application.interfaces.PasswordCSVInterface;

import java.io.FileWriter;


@MXBean
public class PasswordDirectory implements PasswordCSVInterface{
	
	private static final String passwordDirectory = "passwordDirectory.csv";
	private File file;
	private ArrayList<PasswordPOJO> passwordEntries;
	
	
	
	private static final String EMAIL_REGEX;
	private static final Pattern EMAIL_PATTERN;


	static {
		EMAIL_REGEX = 
		        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
		        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    // Pre-compile the pattern for optimal performance
		EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	}
	
	public PasswordDirectory() {
		super();
		this.passwordEntries = new ArrayList<PasswordPOJO>();
		createCSV();
		initalizeListFromCSV();
	
	}
	
	

	/**
	 * @return the passwordEntries
	 */
	public ArrayList<PasswordPOJO> getPasswordEntries() {
		return passwordEntries;
	}

	/**
	 * @param passwordEntries the passwordEntries to set
	 */
	public void setPasswordEntries(ArrayList<PasswordPOJO> passwordEntries) {
		this.passwordEntries = passwordEntries;
	}

	
	public boolean createCSV() {
		this.file = new File(passwordDirectory);
		try {
            // Checks existence and creates a blank file atomically
            if (file.createNewFile()) {
                System.out.println("File created successfully!");
                
                // Write data to the newly created file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("ID,Date of Creation,Application Name, Email ID, Application Type, Last Passwrod, "
                    		+ "Latest Password\r\n");
                }
            } else {
                System.out.println("File already exists. Skipping creation.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
		return true;
	}

	@Override
	public boolean addNewEntry(PasswordPOJO passwordEntry) {
		
		try (FileWriter fw = new FileWriter(passwordDirectory, true);
				 BufferedWriter bw = new BufferedWriter(fw)) {
	            // Join array elements with commas and write
				String line = String.format("%s,%s,%s,%s,%s",
						passwordEntry.getDateOfCreation(),
	                    passwordEntry.getApplicationName(),
	                    passwordEntry.getEmailAddress(),
	                    passwordEntry.getApplicationType(),
	                    passwordEntry.getPassword());

	           
	            bw.write(line);
	            bw.newLine();
	            
	            System.out.println("Data appended successfully!");
	            return true;

        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
            return false;
        }
	}

	@Override
	public boolean updateAnEntry(String applicationName, String emailAddress,String updatedPassword) {
		 try {
			 
		     Iterator<PasswordPOJO> itr = getPasswordEntries().iterator();
		 
		     while(itr.hasNext()) {
		    	 PasswordPOJO current = itr.next();
		    	
				 if(escapeSpecialCharacters(current.getApplicationName()).equals(applicationName) &&
					escapeSpecialCharacters(current.getEmailAddress()).equals(emailAddress)) {
					 System.out.println("Found the entry.");
					 
					 return true;
				 }
			 }
		 }catch(Exception e) {e.printStackTrace();}
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

	

	
	private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\"", "\"\"");
        if (data.contains(",") || data.contains("\"") || data.contains("\n")) {
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }

	public boolean initalizeListFromCSV() {
		
		String line = "";
        String cvsSplitBy = ",";
        System.out.println("Entering the getListFromCSV method.");
        // Step 1: Initialize your ArrayList of objects
        
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Step 2: Use try-with-resources to safely read the file line-by-line
        try (BufferedReader br = new BufferedReader(new FileReader(passwordDirectory))) {
            
        	br.readLine();

            // Step 4: Parse each row data
            while ((line = br.readLine()) != null) {
                // Split the row by commas
                String[] rowData = line.split(cvsSplitBy);
                
                
                // Ensure the row has the expected number of columns to prevent Errors
                
                if (rowData.length >= 5) {
                    try {
                        // Extract fields and convert types manually
                    	String dateOfCreation = rowData[0];
                        String applicationName = rowData[1].trim();
                        String emailAddress = rowData[2].trim();
                        String applicationType = rowData[3].trim();
                        String password = rowData[4].trim();
                        
                        // Create the object and add it directly to the ArrayList
                        PasswordPOJO emp = new PasswordPOJO(dateOfCreation,applicationName,emailAddress,applicationType,password);
                        this.passwordEntries.add(emp);
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping malformed row due to ID conversion error: " + line);
                    }
                }
            }
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
        return true;
	}
	
	
	public boolean checkifValidEmailAddress(String emailAddress) {
		if(!emailAddress.isEmpty()) {
			 return EMAIL_PATTERN.matcher(emailAddress).matches();
		}
		return false;
		
	}


}
