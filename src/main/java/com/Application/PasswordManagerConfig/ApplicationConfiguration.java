package com.Application.PasswordManagerConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;


@Configuration
@ConfigurationProperties(prefix = "password-manager")
@PropertySource("classpath:password-manager.properties") 
public class ApplicationConfiguration {

	private int profileCount;
	private String userName;
	private String password;
	
	
	private static final String FILE_PATH = "src/main/resources/password-manager.properties";
    private static final String PROPERTY_SOURCE_NAME = "URL [file:" + FILE_PATH + "]";

    private  ConfigurableEnvironment environment = new StandardEnvironment();
	
	public ApplicationConfiguration() {
		super();
	}
	
	
	/**
	 * @return the runCounter
	 */
	public int getProfileCount() {
		return profileCount;
	}
	/**
	 * @param runCounter the runCounter to set
	 */
	public void setProfieCount(int profileCount) {
		this.profileCount = profileCount;
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
	

	
	// CREATE or UPDATE
    public synchronized void updatepProfileCounter(){
    	try {
	        Properties props = loadFromDisk();
	        int count = getProfileCount() + 1;
	        props.setProperty("password-manager.profileCount", Integer.toString(count));
	        saveToDisk(props);
	
	        // Sync Spring's @PropertySource in ConfigurableEnvironment
	        refreshEnvironment(props);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void refreshEnvironment(Properties updatedProps) {
        PropertiesPropertySource newSource = new PropertiesPropertySource(PROPERTY_SOURCE_NAME, updatedProps);
        if (environment.getPropertySources().contains(PROPERTY_SOURCE_NAME)) {
            environment.getPropertySources().replace(PROPERTY_SOURCE_NAME, newSource);
        } else {
            environment.getPropertySources().addFirst(newSource);
        }
    }
    
    
    private Properties loadFromDisk() throws IOException {
        Properties props = new Properties();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream in = new FileInputStream(file)) {
                props.load(in);
            }
        }
        return props;
    }

    private void saveToDisk(Properties props) throws IOException {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
        try (FileOutputStream out = new FileOutputStream(file)) {
            props.store(out, "Updated via Spring Boot");
        }
    }
	
	
}
