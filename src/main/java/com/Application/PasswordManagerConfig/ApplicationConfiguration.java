package com.Application.PasswordManagerConfig;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import jakarta.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "password-manager")
@PropertySource("classpath:password-manager.properties") 
public class ApplicationConfiguration {

	private int runCounter;
	private String userName;
	private String password;
	
	
	public ApplicationConfiguration() {
		super();
	}
	
	
	
	private void addCounter() {
		this.runCounter++;
		Properties props = new Properties();
		props.setProperty("password-manager.runCounter", Integer.toString(runCounter));
		
		// 2. Point target directly to the custom file path
		String filePath = "src/main/resources/password-manager.properties"; 
		try (FileOutputStream out = new FileOutputStream(filePath)) {
		    props.store(out, "Updated Custom Settings File");
		    System.out.println("💾 Custom properties successfully saved!");
		} catch (IOException e) {
		    System.err.println("❌ Failed to save custom properties: " + e.getMessage());
		}
	}
	
	/**
	 * @return the runCounter
	 */
	public int getRunCounter() {
		return runCounter;
	}
	/**
	 * @param runCounter the runCounter to set
	 */
	public void setRunCounter(int runCounter) {
		this.runCounter = runCounter;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	public void updateCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;

        Properties props = new Properties();
        props.setProperty("passwordManager.userName", String.valueOf(this.userName));
        props.setProperty("passwordManager.password", this.password);

        // 2. Point target directly to the custom file path
        String filePath = "src/main/resources/passwordManager.properties"; 
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            props.store(out, "Updated Custom Settings File");
            System.out.println("💾 Custom properties successfully saved!");
        } catch (IOException e) {
            System.err.println("❌ Failed to save custom properties: " + e.getMessage());
        }
    }
}
