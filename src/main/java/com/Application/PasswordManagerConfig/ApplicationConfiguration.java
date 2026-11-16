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


@Configuration
@ConfigurationProperties(prefix = "password-manager")
@PropertySource("classpath:password-manager.properties") 
public class ApplicationConfiguration {

	private int profileCount;
	private String userName;
	private String password;
	
	
	private static final String FILE_PATH = "config/users.properties";
    private static final String PROPERTY_SOURCE_NAME = "URL [file:" + FILE_PATH + "]";

    private ConfigurableEnvironment environment;
	
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
	
//	public void updateCredentials(String userName, String password) {
//        this.userName = userName;
//        this.password = password;
//
//        Properties props = new Properties();
//        props.setProperty("passwordManager.userName", String.valueOf(this.userName));
//        props.setProperty("passwordManager.password", this.password);
//
//        // 2. Point target directly to the custom file path
//        String filePath = "src/main/resources/passwordManager.properties"; 
//        try (FileOutputStream out = new FileOutputStream(filePath)) {
//            props.store(out, "Updated Custom Settings File");
//            System.out.println("💾 Custom properties successfully saved!");
//        } catch (IOException e) {
//            System.err.println("❌ Failed to save custom properties: " + e.getMessage());
//        }
//    }
	
	// CREATE or UPDATE
    public synchronized void updatepProfileCounter() throws IOException {
        Properties props = loadFromDisk();
        int count = getProfileCount() + 1;
        props.setProperty("password-manager.profileCount", Integer.toString(count));
        saveToDisk(props);

        // Sync Spring's @PropertySource in ConfigurableEnvironment
        refreshEnvironment(props);
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
